package com.guiyujin.android_lib_base.http.bean;


import com.guiyujin.android_lib_base.http.NetworkConstants;

public class BaseResponse<T> {

    private int code;
    private T data;
    private String msg;
    private Rc rc;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setRc(Rc rc) {
        this.rc = rc;
    }
    public Rc getRc() {
        return rc;
    }

    public boolean isSuccess() {
        return code == NetworkConstants.WEB_RESP_CODE_SUCCESS;
    }
}