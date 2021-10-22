package com.example.techmasala.APIcalls;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmasala.APIcalls.Contents.Tech.Articles.Articles;
import com.example.techmasala.APIcalls.Contents.Tech.Tech;
import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.Adapters.CustomLinearLayoutManager;
import com.example.techmasala.Adapters.RecyclerView_Adapter;
import com.example.techmasala.News_web_view;
import com.example.techmasala.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class TechApi {
    private TextView main;
    private Context context;
    private Activity activity;
    public TechApi(Context context, Activity activity){
        this.context=context;
        this.activity=activity;
    }
    public void getData(String url,String secondary, RecyclerView recyclerView){
        List<ContentList> contents=new ArrayList<>();
        ShimmerFrameLayout shimme=activity.findViewById(R.id.simmer_for_news);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderForApi placeHolderForApi=retrofit.create(PlaceHolderForApi.class);
        Call<Tech> call=placeHolderForApi.getTechNews(secondary);
        call.enqueue(new Callback<Tech>() {
            @Override
            public void onResponse(Call<Tech> call, Response<Tech> response) {
                if (!response.isSuccessful()) {

                } else {

                    Tech tech = response.body();
                    List<Articles> articlesList=tech.getArticles();

                    for(Articles articles:articlesList){

                        if(notSupportedNews(articles))continue;
                        StringBuilder contentMod=new StringBuilder(articles.getContent());

//                        String content=modifiedContent(contentMod);
                        ContentList contentList=new ContentList(

                                articles.getImgUrl(),
                                articles.getUrl(),
                                articles.getDescription(),
                                articles.getTitle(),
                                articles.getSource().getName()
                        );
                        contents.add(contentList);
                    }
                    if (contents.size()==0)getData(url,secondary,recyclerView);
                    shimme.stopShimmer();
                    shimme.setVisibility(View.GONE);
                    RecyclerView_Adapter adapter=new RecyclerView_Adapter(contents,context);
                    recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);
                    adapter.lister(new RecyclerView_Adapter.ObjectClickListener() {
                        @Override
                        public void onClick(ContentList content) {
                            openNewsDepth(content.getUrl());
//                            Toast.makeText(context,,Toast.LENGTH_SHORT).show();
                        }
                    });
//                    Toast.makeText(context, "succ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Tech> call, Throwable t) {
//               Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Pls Check Your Internet connection and retry :)");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Retry",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                activity.finish();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
    private void openNewsDepth(String url){
        Bundle urlBundle=new Bundle();
        urlBundle.putString("url",url);
        News_web_view webView=new News_web_view();
        webView.setArguments(urlBundle);
        activity.getFragmentManager().beginTransaction().
                replace(R.id.fragment_container,webView).
                addToBackStack("frags").commit();

    }
    private boolean notSupportedNews(Articles articles) {
        Log.d("cricbuzz",articles.getSource().getName());
        return articles.getSource().getName().equals("YouTube")||
                articles.getSource().getName().equals("TechRadar")||
                articles.getSource().getName().equals("Space.com")||
                articles.getContent()==null||
                articles.getContent().equals("")||
                articles.getSource().getName().equals("Cricbuzz");
    }

}
