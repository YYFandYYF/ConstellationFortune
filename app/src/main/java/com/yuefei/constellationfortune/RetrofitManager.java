package com.yuefei.constellationfortune;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static String BASE_URL = "http://web.juhe.cn/";

    public final static String KEY = "7bd82b7ef7d5f94dd8a73f30771a3e24";//星座运势

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final ApiService service = retrofit.create(ApiService.class);

    public static ApiService getService() {
        return service;
    }
}
