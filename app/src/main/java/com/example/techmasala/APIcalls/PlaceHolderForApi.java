package com.example.techmasala.APIcalls;

import com.example.techmasala.APIcalls.Contents.Tech.Tech;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PlaceHolderForApi {
    @GET
    Call<Tech> getTechNews(@Url String url);


}
