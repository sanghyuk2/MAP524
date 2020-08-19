package com.example.midterm_sanghyuklee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarAdapter extends BaseAdapter {

    ArrayList<Car> carArrayList = new ArrayList<>();
    View view;

    @Override
    public int getCount() {
        return carArrayList.size();
    }

    @Override
    public Car getItem(int position) {
        return carArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CarViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_listitem_layout, null, false);
            holder = new CarViewHolder();
            holder.imageview_id = (ImageView) convertView.findViewById(R.id.imageview_id);
            holder.studentname_id = (TextView) convertView.findViewById(R.id.studentname_id);
            holder.model_id = (TextView) convertView.findViewById(R.id.model_id);
            holder.year_id = (TextView) convertView.findViewById(R.id.year_id);
            convertView.setTag(holder);
        } else {
            holder = (CarViewHolder) convertView.getTag();
        }
        Car car = carArrayList.get(position);

        holder.studentname_id.setText(car.getOwnerName());
        holder.model_id.setText(car.getModel());
        holder.year_id.setText(car.getYear()+"");
        holder.imageview_id.setImageBitmap(car.getImageData());

        return convertView;
    }

    class CarViewHolder {
        ImageView imageview_id;
        TextView studentname_id;
        TextView model_id;
        TextView year_id;
    }

    public void addItem(Car item) {
        carArrayList.add(item);
    }
}
