package com.example.midterm1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
ImageView sImage;
ListView simpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
      // ArrayList<Student> list =  getIntent().getParcelableArrayListExtra("students");

         DatabaseClient.databaseWriteExecutor.execute(()->{
            Student[] list =  DatabaseClient.getDatabase().StudentDoa().loadAllStudents();
             simpleList = (ListView) findViewById(R.id.simpleList);

             StudentAdapter adapter= new StudentAdapter(this,list);
             simpleList.setAdapter(adapter);

        });



    }


}
