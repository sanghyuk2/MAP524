package com.example.midterm2_skeletonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView myImage;
    Car myCar;
    ArrayList<Car> carsArrayList;
    EditText myName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageView) findViewById(R.id.personalImageID);
        myName = (EditText) findViewById(R.id.studentName);
        myCar = new Car();
        carsArrayList = new ArrayList<Car>(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void uploade(View view) {
        //open camera app and take a photo of the car. (use an emulator fixed camera instead if you don't have physical device)

    }



    public void addNewCarModelAndYear(View view) {
        //open dialog fragment to get the car model and year from the owner
    }


    public void saveCar(View view) {
        //Save car object and navigate to report activity to show the list of all cars
        Intent reportIntent = new Intent(this,ReportActivity.class);
        startActivity(reportIntent);


    }
}
