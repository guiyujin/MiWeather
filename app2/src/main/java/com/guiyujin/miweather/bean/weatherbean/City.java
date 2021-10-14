package com.guiyujin.miweather.bean.weatherbean;


public class City {

    private long cityId;
    private String counname;
    private String ianatimezone;
    private String name;
    private String pname;
    private String secondaryname;
    private String timezone;
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

    public void setIanatimezone(String ianatimezone) {
        this.ianatimezone = ianatimezone;
    }
    public String getIanatimezone() {
        return ianatimezone;
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

    public void setSecondaryname(String secondaryname) {
        this.secondaryname = secondaryname;
    }
    public String getSecondaryname() {
        return secondaryname;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getTimezone() {
        return timezone;
    }

}