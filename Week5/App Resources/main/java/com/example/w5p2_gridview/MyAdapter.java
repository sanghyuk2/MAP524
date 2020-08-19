package com.example.w5p2_gridview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList countryList;
    Context context;
    LayoutInflater inflter;

        public MyAdapter(Context context, int textViewResourceId, ArrayList<Country> objects) {
            countryList = objects;
            this.context = context;
            inflter = (LayoutInflater.from(context));
        }

        @Override
        public int getCount() {
            return countryList.size();
        }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            v = inflter.inflate(R.layout.grid_view_items, null);
            TextView textView = (TextView) v.findViewById(R.id.textView);
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

            textView.setText(((Country)(countryList.get(position))).getName());
            imageView.setImageResource(((Country)(countryList.get(position))).getImage());
            return v;

        }

    }
