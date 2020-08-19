package com.example.midterm_sanghyuklee;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    ArrayList<Car> carList;
    ArrayList<Bitmap> imgList;
    ArrayList<Car> items;
    ListView allcarlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        allcarlist = findViewById(R.id.allcarlist);
        CarAdapter adapter = new CarAdapter();

        imgList = getIntent().getParcelableArrayListExtra("img");
        carList = (ArrayList<Car>) getIntent().getSerializableExtra("selectedCarList");

        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println(car.getModel());
            adapter.addItem(new Car(car.getOwnerName(), imgList.get(i), car.getYear(), car.getModel()));
        }

        allcarlist.setAdapter(adapter);

    }
}

