package com.littleox.common.app.network;

import com.littleox.common.commonlib.BuildConfig;
import com.littleox.common.commonlib.utils.LogUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void init() throws Exception{
        if(mRetrofit == null){
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
            {
                @Override
                public void log(String message)
                {
                    //Log.i("OkHttp", message);
                    LogUtils.logi(message);
                }
            });
            if (BuildConfig.DEBUG)
            {
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            //增加头部信息
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request build = chain.request().newBuilder().addHeader("Accept", "application/vnd.github.v3+json").build();
                    return chain.proceed(build);
                }
            };

            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder= new OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(logInterceptor)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0])
                    .addInterceptor(headerInterceptor);
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
