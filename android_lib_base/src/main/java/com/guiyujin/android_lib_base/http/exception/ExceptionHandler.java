package com.guiyujin.android_lib_base.http.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;


public class ExceptionHandler {

    public static NetworkException handleException(Throwable e) {
        NetworkException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new NetworkException(httpException.code(), httpException.message());
            switch (httpException.code()) {
                case SYSTEM_ERROR.UNAUTHORIZED:
                    ex.setmErrorMsg("操作未授权");
                    break;
                case SYSTEM_ERROR.FORBIDDEN:
                    ex.setmErrorMsg("请求被拒绝");
                    break;
                case SYSTEM_ERROR.NOT_FOUND:
                    ex.setmErrorMsg("资源不存在");
                    break;
                case SYSTEM_ERROR.REQUEST_TIMEOUT:
                    ex.setmErrorMsg("服务器执行超时");
                    break;
                case SYSTEM_ERROR.INTERNAL_SERVER_ERROR:
                    ex.setmErrorMsg("服务器内部错误");
                    break;
                case SYSTEM_ERROR.SERVICE_UNAVAILABLE:
                    ex.setmErrorMsg("服务器不可用");
                    break;
                default:
                    ex.setmErrorMsg("网络错误");
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException || e instanceof MalformedJsonException) {
            ex = new NetworkException(SYSTEM_ERROR.PARSE_ERROR, e.getMessage());
            ex.setmErrorMsg("解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new NetworkException(SYSTEM_ERROR.NETWORD_ERROR, e.getMessage());
            ex.setmErrorMsg("连接失败");
            return ex;
        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new NetworkException(SYSTEM_ERROR.SSL_ERROR, e.getMessage());
            ex.setmErrorMsg("证书验证失败");
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new NetworkException(SYSTEM_ERROR.TIMEOUT_ERROR, e.getMessage());
            ex.setmErrorMsg("连接超时");
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new NetworkException(SYSTEM_ERROR.TIMEOUT_ERROR, e.getMessage());
            ex.setmErrorMsg("连接超时");
            return ex;
        } else if (e instanceof java.net.UnknownHostException) {
            ex = new NetworkException(SYSTEM_ERROR.TIMEOUT_ERROR, e.getMessage());
            ex.setmErrorMsg("主机地址未知");
            return ex;
        } else {
            ex = new NetworkException(SYSTEM_ERROR.UNKNOWN, e.getMessage());
            ex.setmErrorMsg("未知错误");
            return ex;
        }

    }

    public class SYSTEM_ERROR {
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int REQUEST_TIMEOUT = 408;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int SERVICE_UNAVAILABLE = 503;

        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         SSL_ERROR         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;

    }

}
