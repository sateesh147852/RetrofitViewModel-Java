package com.network;

import com.model.ResponsePojo;
import com.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

    @GET("provider/city")
    Call<ResponsePojo> getAllCity();
}
