package com.littleox.common.app.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lingbeijing on 2020/9/1.
 */
public class WebApi {

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 30000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 30000;
    private static volatile WebApi mInstance;

    private Retrofit mRetrofit;
    private final String BASE_URL = "https://api.github.com/";


    private WebApi() {
        init();
    }

    public static WebApi getInstance() {
        if (mInstance == null) {
            synchronized (WebApi.class) {
                if (mInstance == null) {
                    mInstance = new WebApi();
                }
            }
        }
        return mInstance;
    }

    private void init(){
        if(mRetrofit == null){
            OkHttpClient.Builder builder= new OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(new HttpLoggingInterceptor());
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .baseUrl(BASE_URL)
                    .build();
        }
    }

    public ApiService getService() {
        return mRetrofit.create(ApiService.class);
    }
}
