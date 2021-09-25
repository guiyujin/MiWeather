package com.guiyujin.android_lib_base.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.util.List;

public class LocationUtils {
    private static LocationUtils uniqueInstance;
    private LocationManager locationManager;
    private String locationProvider;
    private Location location;
    private Context mContext;

    private LocationUtils(Context context) {
        mContext = context;
        getLocation();
    }

    //采用Double CheckLock(DCL)实现单例
    public static LocationUtils getInstance(Context context) {
        if (uniqueInstance == null) {
            synchronized (LocationUtils.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new LocationUtils( context );
                }
            }
        }
        return uniqueInstance;
    }


    private void getLocation(){
        //1.获取位置管理器
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //2.获取位置提供器，GPS或是NetWork
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.NETWORK_PROVIDER)){
            //如果是网络定位
            Log.d("ONRESULT", "如果是网络定位");
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else if (providers.contains(LocationManager.GPS_PROVIDER)){
            //如果是GPS定位
            Log.d("ONRESULT", "如果是GPS定位");
            locationProvider = LocationManager.GPS_PROVIDER;
        }else {
            Log.d("ONRESULT", "没有可用的位置提供器");
            return;
        }
        // 需要检查权限,否则编译报错,想抽取成方法都不行,还是会报错。只能这样重复 code 了。
        if ( Build.VERSION.SDK_INT >= 23 &&
                ActivityCompat.checkSelfPermission( mContext, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission( mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //3.获取上次的位置，一般第一次运行，此值为null
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location!=null){
            setLocation(location);
        }
    }

    private void setLocation(Location location){
        this.location =location;
        String address = "纬度："+location.getLatitude()+"经度："+location.getLongitude();
        Log.d("ONRESULT",address);
    }

    //获取经纬度
    public Location showLocation(){
        return location;
    }

    public boolean isMoved(Location location, Context context){
        Location locationNow = LocationUtils.getInstance(context).showLocation();
        locationListener.onLocationChanged(locationNow);
        int movement = 0;
        if(movement >= 100){
            return true;
        }else {
            return false;
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
