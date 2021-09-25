package com.guiyujin.android_lib_base.http.bean.weatherbean.shortforecast;

import java.util.List;

/**
 * Auto-generated: 2021-09-03 15:11:31
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Sfc {

    private String banner;
    private List<Percent> percent;
    private int sfCondition;
    private long timestamp;
    private int useLbs;
    public void setBanner(String banner) {
        this.banner = banner;
    }
    public String getBanner() {
        return banner;
    }

    public void setPercent(List<Percent> percent) {
        this.percent = percent;
    }
    public List<Percent> getPercent() {
        return percent;
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

    public void setUseLbs(int useLbs) {
        this.useLbs = useLbs;
    }
    public int getUseLbs() {
        return useLbs;
    }

}
