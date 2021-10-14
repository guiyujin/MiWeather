package com.guiyujin.android_lib_base.http;





import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guiyujin.android_lib_base.http.converterfactory.CustomGsonConverterFactory;
import com.guiyujin.android_lib_base.http.interceptor.CommonRequestInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private StringBuffer baseUrl;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public void setBaseUrl(String url) {
        baseUrl.setLength(0);
        baseUrl.append(url);
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Inject
    public NetworkModule() {
        baseUrl = new StringBuffer();
    }

    public <T> T provide(Class<T> apiClass){
        if (retrofit == null){
            return provideRetrofit().create(apiClass);
        }
        return retrofit.create(apiClass);
    }



    public Retrofit provideRetrofit(){
        if (baseUrl == null){
            throw new IllegalArgumentException("需要设置BaseUrl");
        }
        if (okHttpClient == null){
            okHttpClient = provideOKHttpClient();
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl.toString())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


    public OkHttpClient provideOKHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(new CommonRequestInterceptor());
        return builder.build();
    }


}
