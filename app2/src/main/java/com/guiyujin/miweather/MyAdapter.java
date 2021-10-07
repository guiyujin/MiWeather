package com.guiyujin.miweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guiyujin.android_lib_base.http.bean.weatherbean.hourlyforecast.Hourly;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Weather
 * @Package: com.guiyujin.weather
 * @ClassName: MyAdapter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/24 12:32
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/24 12:32
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<Hourly> hourly = new ArrayList<>();

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    @NonNull
    @io.reactivex.annotations.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @io.reactivex.annotations.NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_hourly_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @io.reactivex.annotations.NonNull MyViewHolder holder, int position) {
        holder.tv_item_time.setText(hourly.get(position).getHour() + ":00");
        holder.tv_item_temp.setText(hourly.get(position).getTemp() + "℃");
        holder.tv_item_condition.setText(hourly.get(position).getCondition());
        holder.tv_item_wind.setText(hourly.get(position).getWindlevel() + "级");
    }

    @Override
    public int getItemCount() {
        return hourly.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_item_time, tv_item_temp, tv_item_condition, tv_item_wind;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_time = itemView.findViewById(R.id.tv_item_time);
            tv_item_temp = itemView.findViewById(R.id.tv_item_temp);
            tv_item_condition = itemView.findViewById(R.id.tv_item_condition);
            tv_item_wind = itemView.findViewById(R.id.tv_item_wind);
        }
    }
}
