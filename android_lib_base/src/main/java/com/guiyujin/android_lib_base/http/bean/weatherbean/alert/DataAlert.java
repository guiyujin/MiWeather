package com.guiyujin.android_lib_base.http.bean.weatherbean.alert;




import com.guiyujin.android_lib_base.http.bean.weatherbean.City;

import java.util.List;

/**
 * Auto-generated: 2021-09-03 15:6:43
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
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
