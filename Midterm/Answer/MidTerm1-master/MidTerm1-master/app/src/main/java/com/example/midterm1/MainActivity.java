package com.example.midterm1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddYearAndLevelDialog.saveYearandLevelEventListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView myImage;
    Student myself;
    ArrayList<Student> studentArrayList;
    EditText myName;
    DatabaseClient dbClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageView) findViewById(R.id.personalImageID);
        myName = (EditText) findViewById(R.id.studentName);
        myself = new Student();
        studentArrayList = new ArrayList<Student>(0);
        dbClient = DatabaseClient.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void uploade(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            myImage.setImageBitmap(imageBitmap);
            myself.imageData = imageBitmap;
        }
    }

    @Override
    public void saveYearAndLevelFun(int year,int level) {
        myself.year = year;
        myself.level = level;
        Toast.makeText(getApplicationContext(),"Year "+myself.year+" level: " + myself.level,Toast.LENGTH_LONG).show();
    }


    public void addNewCourse(View view) {
        AddYearAndLevelDialog newDialog = new AddYearAndLevelDialog();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        newDialog.show(transaction,"fragment");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.report_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.report:
                Intent reportIntent = new Intent(this,ReportActivity.class);
                startActivity(reportIntent);
                break;
        }
        return true;
    }


    public void saveStudent(View view) {
        myself.studentName = myName.getText().toString();
        DatabaseClient.insertToDB(this,myself);


        //studentArrayList.add(myself);
        //Toast.makeText(getApplicationContext()," Student " + myself.studentName + " is saved correcttly ",Toast.LENGTH_LONG).show();
        Intent reportIntent = new Intent(this,ReportActivity.class);
      //  reportIntent.putParcelableArrayListExtra("students",studentArrayList);
        startActivity(reportIntent);
        myself = new Student();

    }
}
