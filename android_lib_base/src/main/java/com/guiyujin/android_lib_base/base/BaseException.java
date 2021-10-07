package com.guiyujin.android_lib_base.base;

/**
 * @ProjectName: Weather
 * @Package: com.guiyujin.android_lib_base.base
 * @ClassName: BaseException
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/21 10:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/21 10:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class BaseException extends Exception{

    private int mErrorCode;
    private String mErrorMsg;

    public BaseException(Throwable throwable, int mErrorCode, String mErrorMsg){
        super(throwable);
        this.mErrorCode = mErrorCode;
        this.mErrorMsg = mErrorMsg;
    }

    public BaseException(Throwable throwable, String mErrorMsg){
        super(throwable);
        this.mErrorMsg = mErrorMsg;
    }

    public BaseException(int mErrorCode, String mErrorMsg) {
        this.mErrorCode = mErrorCode;
        this.mErrorMsg = mErrorMsg;
    }


}
