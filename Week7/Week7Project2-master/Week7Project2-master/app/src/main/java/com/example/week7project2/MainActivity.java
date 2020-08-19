package com.example.week7project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    JsonManager jsonManager = new JsonManager();
    EditText newJsonEditText;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newJsonEditText = (EditText)findViewById(R.id.newJson);

        // get the reference of RecyclerView
        recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = jsonManager.buildAdapterFromJsonData(this,"users_list.json",1);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
    public void SaveNewJson(View view) {
        jsonManager.writeJsonToExternalPrivateStorage(this,"usersData.json",newJsonEditText.getText().toString());
        CustomAdapter customAdapter = jsonManager.buildAdapterFromJsonData(this,"usersData.json",2);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
}
