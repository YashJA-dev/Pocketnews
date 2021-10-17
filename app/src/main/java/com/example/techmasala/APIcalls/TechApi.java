package com.example.techmasala.APIcalls;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.techmasala.APIcalls.Contents.Tech.Articles.Articles;
import com.example.techmasala.APIcalls.Contents.Tech.Tech;
import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.Adapters.CustomLinearLayoutManager;
import com.example.techmasala.Adapters.RecyclerView_Adapter;
import com.example.techmasala.News_web_view;
import com.example.techmasala.R;

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
               Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String modifiedContent(StringBuilder contentMod) {
        int i=0;
        for (i = contentMod.length()-1; i >=0 ; i--) {
            if(contentMod.charAt(i)=='[')break;
            if((contentMod.length()-i)>9)return contentMod.toString();
        }
        return contentMod.substring(0,i);
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
                articles.getContent()==null||
                articles.getContent().equals("")||
                articles.getContent().contains("https")||
                articles.getContent().contains("<")||
                articles.getSource().getName().equals("Cricbuzz");
    }
}
