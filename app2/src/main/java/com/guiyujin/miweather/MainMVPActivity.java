package com.guiyujin.miweather;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.guiyujin.android_lib_base.base.mvp.BaseMVPActivity;
import com.guiyujin.android_lib_base.http.bean.weatherbean.aqi.DataAqi;
import com.guiyujin.android_lib_base.http.bean.weatherbean.condition.DataCondition;
import com.guiyujin.android_lib_base.http.bean.weatherbean.longforecast.DataLongForecast;
import com.guiyujin.android_lib_base.http.bean.weatherbean.hourlyforecast.DataHourlyForecast;
import com.guiyujin.android_lib_base.http.bean.weatherbean.hourlyforecast.Hourly;
import com.guiyujin.android_lib_base.http.bean.weatherbean.shortforecast.DataShortForecast;
import com.guiyujin.android_lib_base.http.exception.NetworkException;
import com.guiyujin.android_lib_base.utils.LocationUtils;
import com.guiyujin.miweather.main.MainModelConstract;
import com.guiyujin.miweather.main.MainModelImpl;
import com.guiyujin.miweather.main.MainPresenter;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;


public class MainMVPActivity extends BaseMVPActivity<MainPresenter, MainModelImpl> implements MainModelConstract.View {
    private ConstraintLayout constraintLayout;
    private TextView tv_title, tv_temp, tv_label, tv_condition, tv_aqi, tv_short_forecast, tv_cl;
    private TextView tv_sun_rise, tv_sun_set, tv_wind_level, tv_wind_dir, tv_humidity_number, tv_humidity, tv_feel_temp, tv_feel, tv_pressure_number, tv_pressure;
    private TextView tv_today, tv_tomorrow, tv_tat, tv_today_temp, tv_tomorrow_temp, tv_tat_temp;
    private Button button;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private List<Hourly> hourlies;
    private Location location;

    Map<String, String> bodys = new HashMap<>();
    public static final String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
    private String[] path = new String[]{"condition", "aqi", "shortforecast", "forecast15days", "forecast24hours"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this, model);

    }

    @Override
    protected void initParams(Bundle params) {

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected MainModelImpl initModel() {
        return new MainModelImpl();
    }


    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected int bindLayout() {

        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        tv_title = find(R.id.tv_title);
        tv_temp = find(R.id.tv_temp);
        tv_label = find(R.id.label);
        tv_condition = find(R.id.tv_condition);
        tv_aqi = find(R.id.tv_aqi);
        tv_short_forecast = find(R.id.tv_short_forecast);
        tv_cl = find(R.id.tv_cl);

        tv_sun_rise = find(R.id.tv_sun_rise);
        tv_sun_set = find(R.id.tv_sun_set);
        tv_wind_level = find(R.id.tv_wind_level);
        tv_wind_dir = find(R.id.tv_wind_dir);
        tv_humidity_number = find(R.id.tv_humidity_number);
        tv_humidity = find(R.id.tv_humidity);
        tv_feel_temp = find(R.id.tv_feel_temp);
        tv_feel = find(R.id.tv_feel);
        tv_pressure_number = find(R.id.tv_pressure_number);
        tv_pressure = find(R.id.tv_pressure);

        tv_today = find(R.id.tv_today);
        tv_tomorrow = find(R.id.tv_tomorrow);
        tv_tat = find(R.id.tv_tat);
        tv_today_temp = find(R.id.tv_today_temp);
        tv_tomorrow_temp = find(R.id.tv_tomorrow_temp);
        tv_tat_temp = find(R.id.tv_tat_temp);

        button =find(R.id.btn_longforecast_detail);

        swipeRefreshLayout = find(R.id.weather_refresh);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#FFFFFF"));
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#99CCFF"));

        myAdapter = new MyAdapter();
        recyclerView = find(R.id.rv);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView.setLayoutManager(linearLayoutManager);

        initToolBar(R.id.toolbar_main, "");
        setMenuRes(R.menu.menu_main);

    }

    @Override
    public void initListener() {
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWeather();
                    }
                }, 500);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

    }

    @Override
    protected void initData() {
        onRefreshListener.onRefresh();
    }

    @Override
    public void widgetClick(int id) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }


    public void getWeather() {
        RxPermissions rxPermissions = new RxPermissions(MainMVPActivity.this);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                if (aBoolean) {
                    location = LocationUtils.getInstance(MainMVPActivity.this).showLocation();
                    if (location != null) {
                        bodys.put("lat", location.getLatitude() + "");
                        bodys.put("lon", location.getLongitude() + "");

                        presenter.getCondition(path, bodys);
                    }
                } else {
                    Toast.makeText(MainMVPActivity.this,
                            "拒绝权限 ", Toast.LENGTH_SHORT)
                            .show();
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });

    }

    @Override
    public void onLoading() {
        tv_title.setText("更新中");
        tv_title.setTextColor(ContextCompat.getColor(this, R.color.white));
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void disLoading() {
        swipeRefreshLayout.setRefreshing(false);
        showToast("刷新成功");
    }

    @Override
    public void showCondition(Object response) {


        DataCondition condition = (DataCondition) response;
        tv_title.setText(condition.getCity().getPname() + " " + condition.getCity().getName());
        String cond = condition.getCondition().getCondition();
        int time = condition.getCondition().getUpdatetime().getHours();
        tv_condition.setText(cond);
        switchBg(cond, time);
        tv_temp.setText(condition.getCondition().getTemp());
        tv_label.setText("℃");

        tv_sun_rise.setText("日出 " + condition.getCondition().getSunRise().getHours() + ":"
                + condition.getCondition().getSunRise().getMinutes());
        tv_sun_set.setText("日落 " + condition.getCondition().getSunSet().getHours() + ":"
                + condition.getCondition().getSunSet().getMinutes());
        tv_wind_level.setText(condition.getCondition().getWindLevel() + "级");
        tv_wind_dir.setText(condition.getCondition().getWindDir());
        tv_humidity_number.setText(condition.getCondition().getHumidity() + "%");
        tv_humidity.setText("湿度");
        tv_feel_temp.setText(condition.getCondition().getRealFeel() + "℃");
        tv_feel.setText("体感");
        tv_pressure_number.setText(condition.getCondition().getPressure() + "hPa");
        tv_pressure.setText("气压");
    }

    private void switchBg(String cond, int time) {
        constraintLayout = findViewById(R.id.layout_main);
        int status = 0;
        boolean isDay = false;
        if (cond.contains("雨"))
            status = 1;
        if (cond.contains("云"))
            status = 2;
        if (cond.contains("阴"))
            status = 3;
        if (time > 6 && time < 18)
            isDay = true;
        switch (status){
            case 1:
                constraintLayout.setBackgroundResource(R.drawable.raindrop_bg);
                break;
            case 2:
                if (isDay){
                    constraintLayout.setBackgroundResource(R.drawable.cloudy_day);
                }else {
                    constraintLayout.setBackgroundResource(R.drawable.main_night_bg2);
                }
                break;
            case 3:
                constraintLayout.setBackgroundResource(R.drawable.overcast);
                break;
            case 0:
            default:
                if (isDay){
                    constraintLayout.setBackgroundResource(R.drawable.main_bg);
                }else {
                    constraintLayout.setBackgroundResource(R.drawable.main_night_bg);
                }

                break;
        }
    }

    @Override
    public void showAqi(Object response) {
        tv_aqi.setVisibility(View.VISIBLE);
        DataAqi aqi = (DataAqi) response;
        tv_aqi.setText(aqiJudgment(Integer.parseInt(aqi.getAqi().getValue())));
    }

    @Override
    public void showShortForecast(Object response) {
        tv_short_forecast.setVisibility(View.VISIBLE);
        DataShortForecast shortForecast = (DataShortForecast) response;
        tv_short_forecast.setText(shortForecast.getSfc().getBanner());
    }

    @Override
    public void showLongForecast(Object response) {
        DataLongForecast longForecast = (DataLongForecast) response;
        tv_today.setText("今天 · " + longForecast.getForecast().get(1).getConditionDay());
        tv_today_temp.setText(longForecast.getForecast().get(1).getTempDay() + "℃ / " + longForecast.getForecast().get(1).getTempNight() + "℃");
        tv_tomorrow.setText("明天 · " + longForecast.getForecast().get(2).getConditionDay());
        tv_tomorrow_temp.setText(longForecast.getForecast().get(2).getTempDay() + "℃ / " + longForecast.getForecast().get(1).getTempNight() + "℃");
        tv_tat.setText("后天 · " + longForecast.getForecast().get(3).getConditionDay());
        tv_tat_temp.setText(longForecast.getForecast().get(3).getTempDay() + "℃ / " + longForecast.getForecast().get(1).getTempNight() + "℃");
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHourlyForecast(Object response) {
        DataHourlyForecast dataHourlyForecast = (DataHourlyForecast) response;
        hourlies = dataHourlyForecast.getHourly();
        myAdapter.setHourly(hourlies);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void getNull() {
        showToast("数据为空");
    }

    @Override
    public void onFailed(Exception exception) {
        tv_title.setText("网络连接出错");
        tv_title.setTextColor(ContextCompat.getColor(this, R.color.red));
        showToast(((NetworkException) exception).getmErrorMsg());
    }

    public String aqiJudgment(int value) {
        if (value <= 50) {
            return "空气优 " + value;
        } else if (value <= 100) {
            return "空气良 " + value;
        } else if (value <= 150) {
            return "空气中等 " + value;
        } else if (value <= 200) {
            return "空气不健康 " + value;
        } else {
            return "空气有毒害 " + value;
        }
    }
}