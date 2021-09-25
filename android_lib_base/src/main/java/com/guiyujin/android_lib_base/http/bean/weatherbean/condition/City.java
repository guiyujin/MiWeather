package com.guiyujin.android_lib_base.http.bean.weatherbean.condition;


public class City {

    private long cityId;
    private String counname;
    private String name;
    private String pname;
    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
    public long getCityId() {
        return cityId;
    }

    public void setCounname(String counname) {
        this.counname = counname;
    }
    public String getCounname() {
        return counname;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getPname() {
        return pname;
    }



}