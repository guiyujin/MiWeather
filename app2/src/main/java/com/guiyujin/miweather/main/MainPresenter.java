package com.guiyujin.miweather.main;

import com.guiyujin.android_lib_base.base.mvp.BasePresenter;

import java.util.Map;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.main
 * @ClassName: MainPresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 14:24
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 14:24
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainPresenter  extends BasePresenter<MainModelContract.View, MainModelContract.Model> implements MainModelContract.presenter{


    @Override
    public void getCondition(String[] path, Map<String, String> bodys) throws Exception {
        mView.onLoading();
        mModel.getCondition(path[0], bodys, new MainModelContract.MainCallBack() {
            @Override
            public void onSuccess(Object response) {
//                mView.disLoading();
                if (response != null){
                    mView.showCondition(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception e) {
//                mView.disLoading();
                mView.onFailed(e);
            }
        });
        mModel.getAqi(path[1], bodys, new MainModelContract.MainCallBack() {
            @Override
            public void onSuccess(Object response) {
//                mView.disLoading();
                if (response != null){
                    mView.showAqi(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception e) {
                mView.disLoading();
                mView.onFailed(e);
            }
        });
        mModel.getShortForecast(path[2], bodys, new MainModelContract.MainCallBack() {
            @Override
            public void onSuccess(Object response) {
//                mView.disLoading();
                if (response != null){
                    mView.showShortForecast(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception e) {
//                mView.disLoading();
                mView.onFailed(e);
            }
        });
        mModel.getLongForecast(path[3], bodys, new MainModelContract.MainCallBack() {
            @Override
            public void onSuccess(Object response) {
//                mView.disLoading();
                if (response != null){
                    mView.showLongForecast(response);
                }else {
                    mView.getNull();
                }
            }

            @Override
            public void onFailed(Exception e) {
//                mView.disLoading();
                mView.onFailed(e);
            }
        });
        mModel.getHourlyForecast(path[4], bodys, new MainModelContract.MainCallBack() {
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
            public void onFailed(Exception e) {
                mView.disLoading();
                mView.onFailed(e);
            }
        });
    }
}
