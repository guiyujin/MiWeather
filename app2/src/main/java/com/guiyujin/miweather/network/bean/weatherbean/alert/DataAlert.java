package com.guiyujin.miweather.network.bean.weatherbean.alert;


import com.guiyujin.miweather.network.bean.weatherbean.City;

import java.util.List;


public class DataAlert {

    private List<Alert> alert;
    private City city;

    public void setAlert(List<Alert> alert) {
        this.alert = alert;
    }

    public List<Alert> getAlert() {
        return alert;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

}
