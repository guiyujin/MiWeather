package com.guiyujin.miweather.bean.weatherbean.shortforecast;


import com.guiyujin.miweather.bean.weatherbean.City;

public class DataShortForecast {

    private City city;
    private Sfc sfc;
    public void setCity(City city) {
        this.city = city;
    }
    public City getCity() {
        return city;
    }

    public void setSfc(Sfc sfc) {
        this.sfc = sfc;
    }
    public Sfc getSfc() {
        return sfc;
    }

}
