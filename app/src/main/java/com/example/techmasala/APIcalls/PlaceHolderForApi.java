package com.example.techmasala.APIcalls;

import com.example.techmasala.APIcalls.Contents.Tech.Tech;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderForApi {
    @GET("top-headlines?country=in&apiKey=e613af58839749aab8f66bba967ab5a8")
    Call<Tech> getTechNews();

}
