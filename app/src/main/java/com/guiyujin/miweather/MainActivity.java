package com.guiyujin.miweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.guiyujin.android_lib_base.base.BaseActivity;

public class MainActivity extends BaseActivity implements ConditionFragment.onConditionResult, DetailFragment.onDetailResult{
    private TextView tv_title;
    private ConstraintLayout constraintLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    public static final String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
    private ConditionFragment conditionFragment;
    private DetailFragment detailFragment;
    private FragmentTransaction fragmentTransaction;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void initParms(Bundle parms) {

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
        conditionFragment = new ConditionFragment();
        detailFragment = new DetailFragment();

        swipeRefreshLayout = find(R.id.weather_refresh);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#FFFFFF"));
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#99CCFF"));

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.add(R.id.fl_condition, conditionFragment, null)
                .add(R.id.fl_detail, detailFragment, null).commit();

        initToolBar(R.id.toolbar_main, "");
        setMenuRes(R.menu.menu_main);
    }

    @Override
    public void initListener() {
        conditionFragment.setOnResult(this);
        detailFragment.setOnDetailResult(this);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                        tv_title.setText("更新中");
                        tv_title.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        detailFragment.initData();
                        conditionFragment.initData();

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

    @Override
    public void checkPermission() {

    }

    @Override
    public void onLoading(Boolean b) {
        if (!b){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onSuccess(Boolean b) {
        if (!b){
            tv_title.setText("网络连接出错");
            tv_title.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    @Override
    public void getTitle(String title) {
        tv_title.setText(title);
        tv_title.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    public void switchBg(String cond, int time) {
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
}