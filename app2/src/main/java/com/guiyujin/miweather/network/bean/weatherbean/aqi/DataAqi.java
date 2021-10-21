package com.guiyujin.miweather.network.bean.weatherbean.aqi;


import com.guiyujin.miweather.network.bean.weatherbean.City;

public class DataAqi {

    private Aqi aqi;
    private City city;
    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }
    public Aqi getAqi() {
        return aqi;
    }

    public void setCity(City city) {
        this.city = city;
    }
    public City getCity() {
        return city;
    }

}