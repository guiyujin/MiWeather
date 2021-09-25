package com.guiyujin.miweather.model.detail;

import com.guiyujin.android_lib_base.base.BaseModel;
import com.guiyujin.android_lib_base.base.BaseView;
import com.guiyujin.android_lib_base.base.ICallBack;
import com.guiyujin.android_lib_base.base.IPresenter;

import java.util.Map;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather.model
 * @ClassName: DetailModelContract
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 11:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 11:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface DetailModelContract {
    interface DetailCallBack<T> extends ICallBack {
        void onSuccess(T response);
    }

    public interface Model extends BaseModel {
        void getAqi(String path, Map<String, String> bodys, DetailCallBack detailCallBack);
        void getShortForecast(String path, Map<String, String> bodys, DetailCallBack detailCallBack);
        void getLongForecast(String path, Map<String, String> bodys, DetailCallBack detailCallBack);
        void getHourlyForecast(String path, Map<String, String> bodys, DetailCallBack detailCallBack);
    }

    public interface View<T> extends BaseView {
        void showAqi(T response);
        void showShortForecast(T response);
        void showLongForecast(T respons);
        void showHourlyForecast(T respons);
        void getNull();
        void onFailed(Exception exception);
    }

    interface presenter extends IPresenter<DetailModelContract.View, DetailModelContract.Model> {
        void getCondition(String[] path, Map<String, String> bodys);
    }
}
