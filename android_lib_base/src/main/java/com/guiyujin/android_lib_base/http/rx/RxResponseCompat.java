package com.guiyujin.android_lib_base.http.rx;


import com.guiyujin.android_lib_base.http.bean.BaseResponse;
import com.guiyujin.android_lib_base.http.exception.ExceptionHandler;
import com.guiyujin.android_lib_base.http.exception.NetworkException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: Weather
 * @Package: com.guiyujin.android_lib_base.http.rx
 * @ClassName: RxResponseCompat
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/21 10:04
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/21 10:04
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RxResponseCompat {

    public static <T> ObservableTransformer<BaseResponse<T>, T> compat(){
        return upstream -> upstream.flatMap(tBaseResponse -> {
            if (tBaseResponse.isSuccess()){
                return createData(tBaseResponse.getData());
            }else {
                return Observable.error(new NetworkException(tBaseResponse.getCode(), tBaseResponse.getMsg()));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static <T> Observable<T> createData(final T t) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(t);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    public static ObservableTransformer exceptionTransformer() {

        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable
                        .onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            NetworkException exception = ExceptionHandler.handleException(t);
            if(exception.getmErrorCode() ==  ExceptionHandler.SYSTEM_ERROR.TIMEOUT_ERROR ){

            }
            return Observable.error(exception);
        }
    }

}
