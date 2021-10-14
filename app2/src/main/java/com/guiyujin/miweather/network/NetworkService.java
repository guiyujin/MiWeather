package com.guiyujin.miweather.network;



import com.guiyujin.android_lib_base.http.bean.BaseResponse;
import com.guiyujin.miweather.bean.weatherbean.alert.DataAlert;
import com.guiyujin.miweather.bean.weatherbean.aqi.DataAqi;
import com.guiyujin.miweather.bean.weatherbean.condition.DataCondition;
import com.guiyujin.miweather.bean.weatherbean.hourlyforecast.DataHourlyForecast;
import com.guiyujin.miweather.bean.weatherbean.longforecast.DataLongForecast;
import com.guiyujin.miweather.bean.weatherbean.shortforecast.DataShortForecast;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface NetworkService {

    /**
     * 天气实况
     * @param bodys
     * @return
     */
    @POST("whapi/json/aliweather/{path}/")
    @FormUrlEncoded
    Observable<BaseResponse<DataCondition>> postCondition(@Path("path") String url, @FieldMap Map<String, String> bodys);

    /**
     * 空气质量指数
     * @param bodys
     * @return
     */
    @POST("whapi/json/aliweather/{path}/")
    @FormUrlEncoded
    Observable<BaseResponse<DataAqi>> postAqi(@Path("path") String url, @FieldMap Map<String, String> bodys);

    /**
     * 天气预警
     * @param bodys
     * @return
     */
    @POST("whapi/json/aliweather/{path}/")
    @FormUrlEncoded
    Observable<BaseResponse<DataAlert>> postAlert(@Path("path") String url, @FieldMap Map<String, String> bodys);

    /**
     * 天气预报24小时
     * @param bodys
     * @return
     */
    @POST("whapi/json/aliweather/{path}/")
    @FormUrlEncoded
    Observable<BaseResponse<DataHourlyForecast>> postForecast24Hours(@Path("path") String url, @FieldMap Map<String, String> bodys);

    /**
     * 短时预报
     * @param bodys
     * @return
     */
    @POST("whapi/json/aliweather/{path}/")
    @FormUrlEncoded
    Observable<BaseResponse<DataShortForecast>> postShortForecast(@Path("path") String url, @FieldMap Map<String, String> bodys);

    /**
     * 15天预报
     * @param bodys
     * @return
     */
    @POST("whapi/json/aliweather/{path}/")
    @FormUrlEncoded
    Observable<BaseResponse<DataLongForecast>> postLongForecast(@Path("path") String url, @FieldMap Map<String, String> bodys);
}
