package com.example.midterm_sanghyuklee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";
    ImageView myImage;
    Button takeAPhotoButton;
    Button addCarInfo; //alert dialog fragment
    Button saveCarInfo;
    EditText myName;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ArrayList<Car> cItem = new ArrayList<>();
    ArrayList<Bitmap> cImg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myName = (EditText) findViewById(R.id.studentName);
        myImage = (ImageView) findViewById(R.id.personalImageID);
        takeAPhotoButton = (Button) findViewById(R.id.button1);
        addCarInfo = (Button) findViewById(R.id.button2);
        saveCarInfo = (Button) findViewById(R.id.button3);


    }

    public void upload(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void addNewCarModelAndYear(View view) {
        Drawable tmp = myImage.getDrawable();
        Bitmap img = ((BitmapDrawable) tmp).getBitmap();
        AddYearAndModelDialog dialog = new AddYearAndModelDialog(img, myName.getText().toString());
        dialog.show(getSupportFragmentManager(), "");
        dialog.setSaveClickListener(new AddYearAndModelDialog.SaveClickListener() {
            @Override
            public void onSaveClicked(Car item, Bitmap img) {
                cImg.add(img);
                cItem.add(item);
            }

        });

    }

    public void saveCar(View view) {
        Intent intent = new Intent(MainActivity.this, ReportActivity.class);
        intent.putExtra("selectedCarList", cItem);
        intent.putExtra("img", cImg);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            myImage.setImageBitmap(imageBitmap);
        }
    }
}
