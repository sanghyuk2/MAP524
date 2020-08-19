package com.example.donationappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllDonations extends AppCompatActivity {

    ListView myList;
    ArrayList<Donations> donationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donations);

        donationList =  getIntent().getParcelableArrayListExtra("donationList");
        myList = (ListView) findViewById(R.id.alldonationlist);
        DonationAdapter adapter = new DonationAdapter(getApplicationContext(),donationList);
        myList.setAdapter(adapter);

    }
}
