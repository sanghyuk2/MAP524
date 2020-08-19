package com.example.midterm1;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Student implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    String studentName;

    @Ignore
    Bitmap imageData;


    int year;

    int level;

    protected Student(Parcel in) {
        studentName = in.readString();
        imageData = in.readParcelable(Bitmap.class.getClassLoader());
        year = in.readInt();
        level = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Student() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentName);
        dest.writeParcelable(imageData, flags);
        dest.writeInt(year);
        dest.writeInt(level);
    }
}
