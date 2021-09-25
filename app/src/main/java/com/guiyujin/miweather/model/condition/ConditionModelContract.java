package com.guiyujin.miweather.model.condition;

import com.guiyujin.android_lib_base.base.BaseModel;
import com.guiyujin.android_lib_base.base.BaseView;
import com.guiyujin.android_lib_base.base.ICallBack;
import com.guiyujin.android_lib_base.base.IPresenter;

import java.util.Map;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather.model
 * @ClassName: ConditionContract
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 10:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 10:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ConditionModelContract {
    interface ConditionCallBack<T> extends ICallBack {
        void onSuccess(T response);
    }

    public interface Model extends BaseModel {
        void getCondition(String path, Map<String, String> bodys, ConditionCallBack conditionCallBack);
    }

    public interface View<T> extends BaseView {
        void showCondition(T response);
        void getNull();
        void onFailed(Exception exception);
    }

    interface presenter extends IPresenter<View, Model> {
        void getCondition(String path, Map<String, String> bodys);
    }
}
