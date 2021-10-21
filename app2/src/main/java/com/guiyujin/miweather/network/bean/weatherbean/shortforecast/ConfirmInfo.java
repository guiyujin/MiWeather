package com.guiyujin.miweather.network.bean.weatherbean.shortforecast;

/**
 * @ProjectName: Weather
 * @Package: com.guiyujin.android_lib_base.http.bean.weatherbean.shortforecast
 * @ClassName: ConfirmInfo
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/29 9:49
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/29 9:49
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ConfirmInfo {

    private int comfirmIcon;
    private String comfirmIconDesc;
    private int isConfirm;
    public void setComfirmIcon(int comfirmIcon) {
        this.comfirmIcon = comfirmIcon;
    }
    public int getComfirmIcon() {
        return comfirmIcon;
    }

    public void setComfirmIconDesc(String comfirmIconDesc) {
        this.comfirmIconDesc = comfirmIconDesc;
    }
    public String getComfirmIconDesc() {
        return comfirmIconDesc;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }
    public int getIsConfirm() {
        return isConfirm;
    }

}
