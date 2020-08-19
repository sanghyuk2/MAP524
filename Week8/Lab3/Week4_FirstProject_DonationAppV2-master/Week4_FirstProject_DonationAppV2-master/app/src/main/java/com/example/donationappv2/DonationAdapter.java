package com.example.donationappv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DonationAdapter extends BaseAdapter {

    Context context;
    ArrayList<Donations> donationsArrayList;
    LayoutInflater inflater;
    View view;
    public DonationAdapter(Context appContext, ArrayList<Donations> list){
        this.context = appContext;
        this.donationsArrayList = list;
        inflater = LayoutInflater.from(appContext);
    }

    @Override
    public int getCount() {
        return donationsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return donationsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = inflater.inflate(R.layout.donation_listitem_layout,null);
        TextView amountText = (TextView)view.findViewById(R.id.amount_row_id);
        TextView paymentText = (TextView) view.findViewById(R.id.payment_row_id);
        TextView sharingText = (TextView) view.findViewById(R.id.sharing_row_id);

        amountText.setText(String.valueOf(donationsArrayList.get(position).amount));
        if (donationsArrayList.get(position).paymentMethod == 1 )
            paymentText.setText("Credit Card");
        else
            paymentText.setText("PayPal");


        String sharing = "";
        if (donationsArrayList.get(position).sharingApps[0] == 1){
            sharing += " What's up";
        }
        if (donationsArrayList.get(position).sharingApps[1] == 1){
            sharing += " text masseges";

        }
        if (donationsArrayList.get(position).sharingApps[2] == 1){
            sharing += " massenger";

        }
        sharingText.setText(sharing);


        return view;
    }


}
