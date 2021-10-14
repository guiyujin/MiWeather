package com.guiyujin.miweather.bean.weatherbean.shortforecast;

import java.util.List;

public class Sfc {

    private String banner;
    private ConfirmInfo confirmInfo;
    private int isCorrect;
    private int isFeedback;
    private String notice;
    private List<Percent> percent;
    private int rain;
    private int rainLastTime;
    private int sfCondition;
    private long timestamp;
    public void setBanner(String banner) {
        this.banner = banner;
    }
    public String getBanner() {
        return banner;
    }

    public void setConfirmInfo(ConfirmInfo confirmInfo) {
        this.confirmInfo = confirmInfo;
    }
    public ConfirmInfo getConfirmInfo() {
        return confirmInfo;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }
    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsFeedback(int isFeedback) {
        this.isFeedback = isFeedback;
    }
    public int getIsFeedback() {
        return isFeedback;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    public String getNotice() {
        return notice;
    }

    public void setPercent(List<Percent> percent) {
        this.percent = percent;
    }
    public List<Percent> getPercent() {
        return percent;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }
    public int getRain() {
        return rain;
    }

    public void setRainLastTime(int rainLastTime) {
        this.rainLastTime = rainLastTime;
    }
    public int getRainLastTime() {
        return rainLastTime;
    }

    public void setSfCondition(int sfCondition) {
        this.sfCondition = sfCondition;
    }
    public int getSfCondition() {
        return sfCondition;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public long getTimestamp() {
        return timestamp;
    }

}
