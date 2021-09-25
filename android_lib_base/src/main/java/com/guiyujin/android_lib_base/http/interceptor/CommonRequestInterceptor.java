package com.guiyujin.android_lib_base.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonRequestInterceptor implements Interceptor {
    private String appcode = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Authorization","APPCODE " + appcode);
        builder.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        return chain.proceed(builder.build());
    }
}
