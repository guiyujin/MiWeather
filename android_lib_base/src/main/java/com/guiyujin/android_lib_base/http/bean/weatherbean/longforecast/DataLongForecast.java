package com.guiyujin.android_lib_base.http.bean.weatherbean.longforecast;


import com.guiyujin.android_lib_base.http.bean.weatherbean.City;

import java.util.List;

public class DataLongForecast {

    private City city;
    private List<Forecast> forecast;
    public void setCity(City city) {
        this.city = city;
    }
    public City getCity() {
        return city;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
    public List<Forecast> getForecast() {
        return forecast;
    }

}