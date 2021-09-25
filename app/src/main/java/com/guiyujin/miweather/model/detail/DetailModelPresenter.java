package com.guiyujin.miweather.model.detail;

import com.guiyujin.android_lib_base.base.BasePresenter;

import java.util.Map;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather
 * @ClassName: DetailModelPresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 11:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 11:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DetailModelPresenter extends BasePresenter<DetailModelContract.View, DetailModelContract.Model> implements DetailModelContract.presenter {

    @Override
    public void getCondition(String[] path, Map<String, String> bodys) {
        mView.onLoading();
        mModel.getAqi(path[0], bodys, new DetailModelContract.DetailCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.disLoading();
                if (response != null){
                    mView.showAqi(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mView.disLoading();
                mView.onFailed(exception);
            }
        });
        mModel.getShortForecast(path[1], bodys, new DetailModelContract.DetailCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.disLoading();
                if (response != null){
                    mView.showShortForecast(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mView.disLoading();
                mView.onFailed(exception);
            }
        });
        mModel.getLongForecast(path[2], bodys, new DetailModelContract.DetailCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.disLoading();
                if (response != null){
                    mView.showLongForecast(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mView.disLoading();
                mView.onFailed(exception);
            }
        });
        mModel.getHourlyForecast(path[3], bodys, new DetailModelContract.DetailCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.disLoading();
                if (response != null){
                    mView.showHourlyForecast(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception exception) {
                mView.disLoading();
                mView.onFailed(exception);
            }
        });


    }
}
