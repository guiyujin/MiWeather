package com.guiyujin.miweather;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guiyujin.android_lib_base.base.BaseMVPFragment;
import com.guiyujin.android_lib_base.http.bean.weatherbean.aqi.DataAqi;
import com.guiyujin.android_lib_base.http.bean.weatherbean.hourlyforecast.DataHourlyForecast;
import com.guiyujin.android_lib_base.http.bean.weatherbean.hourlyforecast.Hourly;
import com.guiyujin.android_lib_base.http.bean.weatherbean.longforecast.DataLongForecast;
import com.guiyujin.android_lib_base.http.bean.weatherbean.shortforecast.DataShortForecast;
import com.guiyujin.android_lib_base.utils.LocationUtils;
import com.guiyujin.miweather.model.detail.DetailModelContract;
import com.guiyujin.miweather.model.detail.DetailModelImpl;
import com.guiyujin.miweather.model.detail.DetailModelPresenter;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;

/**
 * @ProjectName: MiWeather
 * @Package: com.guiyujin.miweather
 * @ClassName: DetailFragment
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/25 11:46
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/25 11:46
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DetailFragment extends BaseMVPFragment<DetailModelPresenter, DetailModelImpl> implements DetailModelContract.View {
    private TextView tv_aqi, tv_short_forecast, tv_cl;
    private TextView tv_today, tv_tomorrow, tv_tat, tv_today_temp, tv_tomorrow_temp, tv_tat_temp;
    private Button button;
    private Location location;
    Map<String, String> bodys = new HashMap<>();
    private String[] path = new String[]{"aqi", "shortforecast", "forecast15days", "forecast24hours"};
    public void setOnDetailResult(DetailFragment.onDetailResult onDetailResult) {
        this.onDetailResult = onDetailResult;
    }

    private onDetailResult onDetailResult;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private List<Hourly> hourlies;

    @Override
    protected DetailModelPresenter initPresenter() {
        return new DetailModelPresenter();
    }

    @Override
    protected DetailModelImpl initModel() {
        return new DetailModelImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this, model);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override
    public void widgetClick(int id) {

    }

    @Override
    protected void initView(View view) {
        tv_aqi = view.findViewById(R.id.tv_aqi);
        tv_short_forecast = view.findViewById(R.id.tv_short_forecast);
        tv_cl = view.findViewById(R.id.tv_cl);


        tv_today = view.findViewById(R.id.tv_today);
        tv_tomorrow = view.findViewById(R.id.tv_tomorrow);
        tv_tat = view.findViewById(R.id.tv_tat);
        tv_today_temp = view.findViewById(R.id.tv_today_temp);
        tv_tomorrow_temp = view.findViewById(R.id.tv_tomorrow_temp);
        tv_tat_temp = view.findViewById(R.id.tv_tat_temp);

        button =view.findViewById(R.id.btn_longforecast_detail);

        myAdapter = new MyAdapter();
        recyclerView = view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void initData(){
        getWeather();
    }

    private void getWeather() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(MainActivity.permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                if (aBoolean) {
                    location = LocationUtils.getInstance(mContext).showLocation();
                    if (location != null) {
                        bodys.put("lat", location.getLatitude() + "");
                        bodys.put("lon", location.getLongitude() + "");

                        presenter.getCondition(path, bodys);
                    }
                } else {

                }

            }
        });
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
    public void showLongForecast(Object respons) {
        DataLongForecast longForecast = (DataLongForecast) respons;
        tv_today.setText("今天 · " + longForecast.getForecast().get(1).getConditionDay());
        tv_today_temp.setText(longForecast.getForecast().get(1).getTempDay() + "℃ / " + longForecast.getForecast().get(1).getTempNight() + "℃");
        tv_tomorrow.setText("明天 · " + longForecast.getForecast().get(2).getConditionDay());
        tv_tomorrow_temp.setText(longForecast.getForecast().get(2).getTempDay() + "℃ / " + longForecast.getForecast().get(1).getTempNight() + "℃");
        tv_tat.setText("后天 · " + longForecast.getForecast().get(3).getConditionDay());
        tv_tat_temp.setText(longForecast.getForecast().get(3).getTempDay() + "℃ / " + longForecast.getForecast().get(1).getTempNight() + "℃");
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHourlyForecast(Object respons) {
        DataHourlyForecast dataOneDayForecast = (DataHourlyForecast) respons;
        hourlies = dataOneDayForecast.getHourly();
        myAdapter.setHourly(hourlies);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void getNull() {

    }

    @Override
    public void onFailed(Exception exception) {
        onDetailResult.onLoading(false);
    }

    @Override
    public void onLoading() {
        onDetailResult.onLoading(true);
    }

    @Override
    public void disLoading() {
        onDetailResult.onLoading(false);
    }

    interface onDetailResult extends onResult{

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
