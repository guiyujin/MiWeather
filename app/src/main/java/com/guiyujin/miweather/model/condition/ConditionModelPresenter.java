package com.guiyujin.miweather.model.condition;

import com.guiyujin.android_lib_base.base.BasePresenter;

import java.util.Map;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather.model
 * @ClassName: ConditionModelPresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 10:29
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 10:29
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ConditionModelPresenter extends BasePresenter<ConditionModelContract.View, ConditionModelContract.Model> implements ConditionModelContract.presenter {
    @Override
    public void getCondition(String path, Map<String, String> bodys) {
        mView.onLoading();
        mModel.getCondition(path, bodys, new ConditionModelContract.ConditionCallBack() {
            @Override
            public void onSuccess(Object response) {
                mView.disLoading();
                if (response != null){
                    mView.showCondition(response);
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
