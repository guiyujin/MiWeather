package com.guiyujin.miweather.main;

import com.guiyujin.android_lib_base.base.mvp.BasePresenter;

import java.util.List;
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
        mModel.get(path, bodys, new MainModelContract.MainCallBack() {
            @Override
            public void onSuccessList(List response) {
                mView.disLoading();
                if (response != null){
                    mView.show(response);
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
