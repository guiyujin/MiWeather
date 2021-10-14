package com.guiyujin.miweather.main;


import com.guiyujin.android_lib_base.base.ICallBack;
import com.guiyujin.android_lib_base.base.mvp.BaseModel;
import com.guiyujin.android_lib_base.base.mvp.BaseView;
import com.guiyujin.android_lib_base.base.mvp.IPresenter;

import java.util.Map;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.main
 * @ClassName: MainModelConstract
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 14:23
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 14:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface MainModelContract {
    interface MainCallBack<T> extends ICallBack {
        void onSuccess(T response);
    }

    interface Model extends BaseModel {
        void getCondition(String path, Map<String, String> bodys, MainCallBack mainCallBack) throws Exception;
        void getAqi(String path, Map<String, String> bodys, MainCallBack mainCallBack) throws Exception;
        void getShortForecast(String path, Map<String, String> bodys, MainCallBack mainCallBack) throws Exception;
        void getLongForecast(String path, Map<String, String> bodys, MainCallBack mainCallBack) throws Exception;
        void getHourlyForecast(String path, Map<String, String> bodys, MainCallBack mainCallBack) throws Exception;
    }

    interface View<T> extends BaseView {
        void showCondition(T response);
        void showAqi(T response);
        void showShortForecast(T response);
        void showLongForecast(T response);
        void showHourlyForecast(T response);
        void getNull();
        void onFailed(Exception exception);
    }

    interface presenter{
        void getCondition(String[] path, Map<String, String> bodys) throws Exception;
    }
}
