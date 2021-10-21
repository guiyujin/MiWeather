package com.guiyujin.android_lib_base.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonRequestInterceptor implements Interceptor {
    private StringBuffer appcode;

    public void setAppcode(String code) {
        appcode.setLength(0);
        appcode.append(code);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (appcode == null){
            appcode = new StringBuffer();
            setAppcode("ab11b8e3aa2c4920ad02c0b66fe9b908");
        }
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Authorization","APPCODE " + appcode);
        builder.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        return chain.proceed(builder.build());
    }
}


