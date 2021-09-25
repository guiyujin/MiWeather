package com.guiyujin.miweather.model.detail;

import androidx.annotation.NonNull;

import com.guiyujin.android_lib_base.http.NetworkModule;
import com.guiyujin.android_lib_base.http.rx.RxResponseCompat;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather.model
 * @ClassName: DetailModelImpl
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 11:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 11:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DetailModelImpl implements DetailModelContract.Model{
    private NetworkModule networkModule = new NetworkModule();

    @Override
    public void getAqi(String path, Map<String, String> bodys, DetailModelContract.DetailCallBack detailCallBack) {
        networkModule.providerNetworkService(networkModule.provideRetrofit()).postAqi(path, bodys)
                .compose(RxResponseCompat.exceptionTransformer())
                .compose(RxResponseCompat.compat())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        detailCallBack.onSuccess(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailCallBack.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getShortForecast(String path, Map<String, String> bodys, DetailModelContract.DetailCallBack detailCallBack) {
        networkModule.providerNetworkService(networkModule.provideRetrofit()).postShortForecast(path, bodys)
                .compose(RxResponseCompat.exceptionTransformer())
                .compose(RxResponseCompat.compat())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        detailCallBack.onSuccess(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailCallBack.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getLongForecast(String path, Map<String, String> bodys, DetailModelContract.DetailCallBack detailCallBack) {
        networkModule.providerNetworkService(networkModule.provideRetrofit()).postLongForecast(path, bodys)
                .compose(RxResponseCompat.exceptionTransformer())
                .compose(RxResponseCompat.compat())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        detailCallBack.onSuccess(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailCallBack.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHourlyForecast(String path, Map<String, String> bodys, DetailModelContract.DetailCallBack detailCallBack) {
        networkModule.providerNetworkService(networkModule.provideRetrofit()).postForecast24Hours(path, bodys)
                .compose(RxResponseCompat.exceptionTransformer())
                .compose(RxResponseCompat.compat())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        detailCallBack.onSuccess(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        detailCallBack.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
