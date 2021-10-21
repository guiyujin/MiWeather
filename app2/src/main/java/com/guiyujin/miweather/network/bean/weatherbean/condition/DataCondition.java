package com.guiyujin.miweather.network.bean.weatherbean.condition;


public class DataCondition {

    private City city;
    private Condition condition;
    public void setCity(City city) {
        this.city = city;
    }
    public City getCity() {
        return city;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public Condition getCondition() {
        return condition;
    }

}