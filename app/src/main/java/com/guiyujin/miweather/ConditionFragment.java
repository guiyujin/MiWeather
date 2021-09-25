package com.guiyujin.miweather;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guiyujin.android_lib_base.base.BaseMVPFragment;
import com.guiyujin.android_lib_base.http.bean.weatherbean.condition.DataCondition;
import com.guiyujin.android_lib_base.utils.LocationUtils;
import com.guiyujin.miweather.model.condition.ConditionModelContract;
import com.guiyujin.miweather.model.condition.ConditionModelImpl;
import com.guiyujin.miweather.model.condition.ConditionModelPresenter;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather
 * @ClassName: ConditionFragment
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 10:18
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 10:18
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ConditionFragment extends BaseMVPFragment<ConditionModelPresenter, ConditionModelImpl>implements ConditionModelContract.View {
    public void setOnResult(ConditionFragment.onConditionResult onResult) {
        this.onResult = onResult;
    }

    private onConditionResult onResult;
    private TextView tv_temp, tv_label, tv_condition;
    private Location location;
    Map<String, String> bodys = new HashMap<>();

    @Override
    protected ConditionModelPresenter initPresenter() {
        return new ConditionModelPresenter();
    }

    @Override
    protected ConditionModelImpl initModel() {
        return new ConditionModelImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this, model);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_condition;
    }

    @Override
    protected void initView(View view) {
        tv_temp = view.findViewById(R.id.tv_temp);
        tv_label = view.findViewById(R.id.tv_label);
        tv_condition = view.findViewById(R.id.tv_condition);

    }

    @Override
    public void widgetClick(int id) {

    }

    @Override
    public void showCondition(Object response) {
        DataCondition condition = (DataCondition) response;

        onResult.getTitle(condition.getCity().getPname() + " " + condition.getCity().getName());
        String cond = condition.getCondition().getCondition();
        int time = condition.getCondition().getUpdatetime().getHours();
        tv_condition.setText(cond);
        onResult.switchBg(cond, time);
        tv_temp.setText(condition.getCondition().getTemp());
        tv_label.setText("℃");

    }

    public void initData(){
        getWeather();
    }

    @Override
    public void getNull() {

    }

    @Override
    public void onFailed(Exception exception) {
        onResult.onLoading(false);
    }

    @Override
    public void onLoading() {
        onResult.onLoading(true);
    }

    @Override
    public void disLoading() {
        onResult.onLoading(false);
    }

    public void getWeather() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(MainActivity.permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                if (aBoolean) {
                    location = LocationUtils.getInstance(mContext).showLocation();
                    if (location != null) {
                        bodys.put("lat", location.getLatitude() + "");
                        bodys.put("lon", location.getLongitude() + "");

                        presenter.getCondition("condition", bodys);
                    }
                } else {

                }

            }
        });

    }

    interface onConditionResult extends onResult{
        void getTitle(String  title);
        void switchBg(String cond, int time);
    }
}
