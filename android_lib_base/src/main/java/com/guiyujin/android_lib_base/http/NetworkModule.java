package com.guiyujin.android_lib_base.http;





import com.guiyujin.android_lib_base.http.converterfactory.CustomGsonConverterFactory;
import com.guiyujin.android_lib_base.http.interceptor.CommonRequestInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class NetworkModule {
    public static final String BaseUrl = "https://aliv8.mojicb.com/";

    @Inject
    public NetworkModule() {
    }

    @Provides
//    @Singleton
    public NetworkService providerNetworkService(Retrofit retrofit){
        return retrofit.create(NetworkService.class);
    }

    @Provides
    public Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(provideOKHttpClient())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    public OkHttpClient provideOKHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(new CommonRequestInterceptor());
        return builder.build();
    }
}
