package com.example.techmasala.APIcalls;

import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techmasala.APIcalls.Contents.Tech.Articles.Articles;
import com.example.techmasala.APIcalls.Contents.Tech.Tech;
import com.example.techmasala.R;

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
    public void getData(String url){
//        main=activity.findViewById(R.id.main_text);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderForApi placeHolderForApi=retrofit.create(PlaceHolderForApi.class);
        Call<Tech> call=placeHolderForApi.getTechNews();
        call.enqueue(new Callback<Tech>() {
            @Override
            public void onResponse(Call<Tech> call, Response<Tech> response) {
                if (!response.isSuccessful()) {

                } else {
                    Tech tech = response.body();
                    List<Articles> articlesList=tech.getArticles();
                    StringBuilder sb=new StringBuilder();
                    for (Articles articles:articlesList){

                        sb.append(articles.getDescription()+"\n");
                        sb.append("\n");
                        sb.append("\n");
                    }
                    main.setText(sb.toString());
                    main.setMovementMethod(new ScrollingMovementMethod());
                    Toast.makeText(context, "succ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Tech> call, Throwable t) {
                main.setText(t.getMessage());

            }
        });
    }
}
