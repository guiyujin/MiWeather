package com.guiyujin.miweather.main;

import com.guiyujin.android_lib_base.http.NetworkManager;
import com.guiyujin.android_lib_base.http.NetworkModule;
import com.guiyujin.android_lib_base.http.bean.BaseResponse;
import com.guiyujin.android_lib_base.http.rx.RxResponseCompat;
import com.guiyujin.miweather.network.bean.weatherbean.alert.DataAlert;
import com.guiyujin.miweather.network.bean.weatherbean.aqi.Aqi;
import com.guiyujin.miweather.network.bean.weatherbean.aqi.DataAqi;
import com.guiyujin.miweather.network.bean.weatherbean.condition.DataCondition;
import com.guiyujin.miweather.network.NetworkService;
import com.guiyujin.miweather.network.bean.weatherbean.hourlyforecast.DataHourlyForecast;
import com.guiyujin.miweather.network.bean.weatherbean.longforecast.DataLongForecast;
import com.guiyujin.miweather.network.bean.weatherbean.shortforecast.DataShortForecast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.main
 * @ClassName: MainModelImpl
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 14:24
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 14:24
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainModelImpl implements MainModelContract.Model{
    private NetworkModule networkModule;
    private NetworkManager networkManager = NetworkManager.getInstance();

    // 可重试次数
    private int maxConnectCount = 3;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;
    private List<Object> response;

    public MainModelImpl(){
        response = new ArrayList<>();
        networkManager = NetworkManager.getInstance();
        networkManager.setBaseUrl("https://aliv8.mojicb.com/");
        networkModule = new NetworkModule();
        networkModule.setBaseUrl("https://aliv8.mojicb.com/");
    }

    @Override
    public void get(String[] path, Map<String, String> bodys, MainModelContract.MainCallBack mainCallBack) {
        Observable<BaseResponse<DataCondition>> condition = networkManager.provide(NetworkService.class).postCondition(path[0], bodys);
        Observable<BaseResponse<DataAqi>> aqi = networkModule.provide(NetworkService.class).postAqi(path[1], bodys);
        Observable<BaseResponse<DataShortForecast>> shortForecast = networkModule.provide(NetworkService.class).postShortForecast(path[2], bodys);
        Observable<BaseResponse<DataLongForecast>> longForecast = networkModule.provide(NetworkService.class).postLongForecast(path[3], bodys);
        Observable<BaseResponse<DataHourlyForecast>> hours = networkModule.provide(NetworkService.class).postForecast24Hours(path[4], bodys);
        Observable<Object> merge = Observable.concat(condition, aqi, shortForecast, longForecast);
        Observable<Object> contact = Observable.concat(merge, hours);
        contact.compose(RxResponseCompat.exceptionTransformer())
                .compose(RxResponseCompat.compat())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@androidx.annotation.NonNull Object o) {
                        if (o instanceof DataCondition){
                            response.add((DataCondition) o);
                        }else if (o instanceof DataAqi){
                            response.add((DataAqi) o);
                        }else if (o instanceof DataShortForecast){
                            response.add((DataShortForecast) o);
                        }else if (o instanceof DataLongForecast){
                            response.add((DataLongForecast) o);
                        }else if (o instanceof DataHourlyForecast){
                            response.add((DataHourlyForecast) o);
                        }
                    }

                    @Override
                    public void onError(@androidx.annotation.NonNull Throwable e) {
                        mainCallBack.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {
                        mainCallBack.onSuccessList(response);
                    }
                });
    }


    //                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
//                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                                if (throwable instanceof IOException){
//                                    if (currentRetryCount < maxConnectCount){
//                                        // 记录重试次数
//                                        currentRetryCount++;
//                                        /**
//                                         * 需求2：实现重试
//                                         * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
//                                         *
//                                         * 需求3：延迟1段时间再重试
//                                         * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
//                                         *
//                                         * 需求4：遇到的异常越多，时间越长
//                                         * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
//                                         */
//                                        // 设置等待时间
//                                        waitRetryTime = 500 + currentRetryCount* 500;
//                                        return Observable.just(1).delay(waitRetryTime, TimeUnit.MILLISECONDS);
//                                    }else{
//                                        // 若重试次数已 > 设置重试次数，则不重试
//                                        // 通过发送error来停止重试（可在观察者的onError（）中获取信息）
////                                        "重试次数已超过设置次数 = " + currentRetryCount  + "，即 不再重试"
//                                        return Observable.error(new NetworkException(throwable, "请检查网络连接"));
//                                    }
//                                }else{
//                                    return Observable.error(new Throwable("发生了非网络异常（非I/O异常）"));
//                                }
//                            }
//                        });
//                    }
//                })
}
