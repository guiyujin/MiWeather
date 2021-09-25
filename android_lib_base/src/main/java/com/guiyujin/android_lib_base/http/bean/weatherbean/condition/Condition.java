package com.guiyujin.android_lib_base.http.bean.weatherbean.condition;
import java.util.Date;


public class Condition<T> {

    private String condition;
    private String conditionId;
    private String humidity;
    private String icon;
    private String pressure;
    private String realFeel;
    private Date sunRise;
    private Date sunSet;
    private String temp;
    private String tips;
    private Date updatetime;
    private String uvi;
    private String vis;
    private String windDegrees;
    private String windDir;
    private String windLevel;
    private String windSpeed;
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition() {
        return condition;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }
    public String getConditionId() {
        return conditionId;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return humidity;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
    public String getPressure() {
        return pressure;
    }

    public void setRealFeel(String realFeel) {
        this.realFeel = realFeel;
    }
    public String getRealFeel() {
        return realFeel;
    }

    public void setSunRise(Date sunRise) {
        this.sunRise = sunRise;
    }
    public Date getSunRise() {
        return sunRise;
    }

    public void setSunSet(Date sunSet) {
        this.sunSet = sunSet;
    }
    public Date getSunSet() {
        return sunSet;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTemp() {
        return temp;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
    public String getTips() {
        return tips;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
    }
    public String getUvi() {
        return uvi;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }
    public String getVis() {
        return vis;
    }

    public void setWindDegrees(String windDegrees) {
        this.windDegrees = windDegrees;
    }
    public String getWindDegrees() {
        return windDegrees;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }
    public String getWindDir() {
        return windDir;
    }

    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel;
    }
    public String getWindLevel() {
        return windLevel;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
    public String getWindSpeed() {
        return windSpeed;
    }

}