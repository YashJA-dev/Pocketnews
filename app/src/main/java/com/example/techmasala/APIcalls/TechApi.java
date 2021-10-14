package com.example.techmasala.APIcalls;

import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.techmasala.APIcalls.Contents.Tech.Articles.Articles;
import com.example.techmasala.APIcalls.Contents.Tech.Tech;
import com.example.techmasala.Adapters.Constants.ContentList;
import com.example.techmasala.Adapters.CustomLinearLayoutManager;
import com.example.techmasala.Adapters.RecyclerView_Adapter;
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

                        if(articles.getSource().getName().equals("YouTube")||
                        articles.getContent()==null||
                        articles.getContent().equals("")||
                                articles.getContent().contains("https")||
                        articles.getContent().contains("<"))continue;
                        ContentList contentList=new ContentList(

                                articles.getImgUrl(),
                                articles.getContent(),
                                articles.getTitle(),
                                articles.getSource().getName()
                        );
                        contents.add(contentList);
                    }
                    if (contents.size()==0)getData(url,secondary,recyclerView);

                    RecyclerView_Adapter adapter=new RecyclerView_Adapter(contents,context);
                    recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);
//                    Toast.makeText(context, "succ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Tech> call, Throwable t) {
               Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
