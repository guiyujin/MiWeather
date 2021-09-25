package com.guiyujin.miweather.model.condition;

import androidx.annotation.NonNull;

import com.guiyujin.android_lib_base.http.NetworkModule;
import com.guiyujin.android_lib_base.http.rx.RxResponseCompat;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather.model
 * @ClassName: ConditionModelImpl
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 10:28
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 10:28
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ConditionModelImpl implements ConditionModelContract.Model{
    private NetworkModule networkModule = new NetworkModule();

    @Override
    public void getCondition(String path, Map<String, String> bodys, ConditionModelContract.ConditionCallBack conditionCallBack) {
        networkModule.providerNetworkService(networkModule.provideRetrofit()).postCondition(path, bodys)
                .compose(RxResponseCompat.exceptionTransformer())
                .compose(RxResponseCompat.compat())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        conditionCallBack.onSuccess(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        conditionCallBack.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
