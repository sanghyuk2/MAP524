package com.example.midterm_sanghyuklee;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Car implements Serializable {

    String OwnerName;
    Bitmap imageData;
    int year;
    String model;

    public Car(String OwnerName, Bitmap imageData, int year, String model) {
        this.OwnerName = OwnerName;
        this.imageData = imageData;
        this.year = year;
        this.model = model;
    }

    public Car(String ownerName, int year, String model) {
        OwnerName = ownerName;
        this.year = year;
        this.model = model;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public Bitmap getImageData() {
        return imageData;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }
}
