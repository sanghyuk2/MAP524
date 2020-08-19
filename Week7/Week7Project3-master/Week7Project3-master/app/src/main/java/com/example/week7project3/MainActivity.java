package com.example.week7project3;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText userName;
    EditText passWord;
    StorageManager manager;
    Button saveEPrivateButton;
    Button saveEPublicButton;
    private static int STORAGE_PERMISSION_CODE = 23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.data);
        manager = new StorageManager();
        saveEPrivateButton = (Button)findViewById(R.id.saveExternalPrivate);

        saveEPrivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataEPrivate();
            }
        });
        saveEPublicButton = (Button)findViewById(R.id.saveExternalPublic);
        saveEPublicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataEPublic();
            }
        });



    }
    public void saveDataEPrivate(){
        String info = editText.getText().toString();
        manager.saveExternalPrivate(this,"userData2.txt",info);
        editText.setText("");
    }

    public void saveDataEPublic(){
      //  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        String info = editText.getText().toString();
        manager.saveExternalPublic(this,info);
        editText.setText("");
    }


    public void  saveInternal(View view)  // SAVE
    {
        File file= null;
        String info = editText.getText().toString();

        manager.saveInternal(this,info);
        editText.setText("");
    }

    public void  loadInternal(View view)
    {
        String data = manager.loadFromInternal(this);
        Toast.makeText(this,"Internal Data :" + data, Toast.LENGTH_SHORT).show();

    }

    public void loadFromEPrivate(View view) {
        String data = manager.getExternalPrivate(this,"userData2.txt");
        Toast.makeText(this,"External Private data :" + data, Toast.LENGTH_SHORT).show();

    }

    public void loadFromEPublic(View view) {
        String data = manager.getExternalPublic();
        Toast.makeText(this,"External Public Data :" + data, Toast.LENGTH_SHORT).show();
    }}
