package com.sizey.cardata;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkingClass.APIDataListner, CarAdapter.AlertDialogListner {

    NetworkingClass networkingClass;
    JsonManager jsonManager;
    RecyclerView recyclerView;
    CarAdapter carAdapter;
    ArrayList<Car> items = new ArrayList<>();
    AppDatabase carDB = null;
    Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkingClass = new NetworkingClass(this, getApplicationContext());
        jsonManager = new JsonManager();
        carDB = AppDatabase.getInstance(this);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_car);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get Data
        // By using livedata, whenever the data changes, it apply changes
        // https://developer.android.com/topic/libraries/architecture/livedata
        carDB.carDao().getAllCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                // If there is no stored data, then it parse the json
                if (cars.isEmpty()) networkingClass.getCars();

                carAdapter = new CarAdapter(mContext, (ArrayList<Car>) cars);
                recyclerView.setAdapter(carAdapter);
            }
        });
    }


    @Override
    public void alertDialogCar(Car car) {
        setCarFavorite(car);
        carAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnAPIData(String data) {
        Log.d("data", "returnAPIData: " + data);
        ArrayList<Car> cars = jsonManager.getCarData(data);
        items = new ArrayList<Car>();
        items.addAll(cars);
        recyclerView.setAdapter(new CarAdapter(this, items));
        recyclerView.invalidate();
        setCarDB(items);
    }

    private void setCarDB(final ArrayList<Car> items) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "run: setCarDB" + items.get(0));
                for (int i = 0; i < items.size(); i++) {
                    carDB.carDao().insertCar(items.get(i));
                }
            }
        }).start();
    }
    private void setCarFavorite(final Car car) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    carDB.carDao().insertCar(car);
            }
        }).start();
    }
}

