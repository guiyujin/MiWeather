package com.guiyujin.miweather.bean.weatherbean.alert;

import java.util.Date;

public class Alert {

    private String content;
    private int infoid;
    private String level;
    private String name;
    private Date pub_time;
    private String title;
    private String type;
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setInfoid(int infoid) {
        this.infoid = infoid;
    }
    public int getInfoid() {
        return infoid;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPub_time(Date pub_time) {
        this.pub_time = pub_time;
    }
    public Date getPub_time() {
        return pub_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

}
