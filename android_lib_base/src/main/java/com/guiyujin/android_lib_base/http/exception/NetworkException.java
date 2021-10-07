package com.guiyujin.android_lib_base.http.exception;

import com.guiyujin.android_lib_base.base.BaseException;
import com.guiyujin.android_lib_base.http.NetworkConstants;

public class NetworkException extends BaseException {
    private int mErrorCode;

    private String mErrorMsg;

    public NetworkException(Throwable throwable, String mErrorMsg){
        super(throwable, mErrorMsg);

    }

    public NetworkException(int mErrorCode, String mErrorMsg) {
        super(mErrorCode, mErrorMsg);
    }


    public int getmErrorCode() {
        return this.mErrorCode;
    }

    public String getmErrorMsg() {
        return this.mErrorMsg;
    }

    public void setmErrorMsg(String mErrorMsg) {
        this.mErrorMsg = mErrorMsg;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == NetworkConstants.TOKEN_EXPRIED;
    }
}