package com.guiyujin.miweather.network.bean.weatherbean.shortforecast;


public class Percent {

    private int dbz;
    private String desc;
    private int icon;
    private double percent;
    public void setDbz(int dbz) {
        this.dbz = dbz;
    }
    public int getDbz() {
        return dbz;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
    public int getIcon() {
        return icon;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    public double getPercent() {
        return percent;
    }

}