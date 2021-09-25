package com.guiyujin.android_lib_base.http.bean.weatherbean.hourlyforecast;

import com.guiyujin.android_lib_base.http.bean.weatherbean.City;

import java.util.List;

/**
 * @ProjectName: Weather
 * @Package: com.guiyujin.android_lib_base.http.bean.weatherbean.onedayforecast
 * @ClassName: DataOneDayForecast
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/23 14:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/23 14:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DataHourlyForecast {
    private City city;
    private List<Hourly> hourly;
    public void setCity(City city) {
        this.city = city;
    }
    public City getCity() {
        return city;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }
    public List<Hourly> getHourly() {
        return hourly;
    }
}
