package com.yuefei.constellationfortune;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("constellation/getAll")
    Call<Constellation> getConstellation(@Query("consName")String name, @Query("type")String type, @Query("key")String key);

    @GET("constellation/getAll")
    Call<ResponseBody> getYearFortune(@Query("consName")String name, @Query("type")String type, @Query("key")String key);
}
