package com.example.networkingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    JsonManager jsonManager = new JsonManager();
    FloatingActionButton buttonAddTask;
    NetworkingClass networkingClass;
    DatabaseClient databaseClient;
    RecyclerView recyclerView;
    DatabaseCitiesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Weather App");
        databaseClient = DatabaseClient.getInstance(this);
        recyclerView = findViewById(R.id.favoriteCitiesList);

        DatabaseClient.databaseWriteExecutor.execute(()->{
            List<City> dbCities = DatabaseClient.getsDatabase().getDao().getAllCities();
            adapter = new DatabaseCitiesAdapter(this,dbCities);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            recyclerView.setAdapter(adapter);
        });


        buttonAddTask = findViewById(R.id.floating_button_add);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchForCityActivity.class);
                startActivity(intent);
            }
        });


      //  networkingClass = new NetworkingClass(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseClient.databaseWriteExecutor.execute(()->{
            List<City> dbCities = DatabaseClient.getInstance(this).getsDatabase().getDao().getAllCities();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //do stuff in main activity
                    adapter = new DatabaseCitiesAdapter(getApplicationContext(),dbCities);
                    recyclerView.setAdapter(adapter);
                    recyclerView.invalidate();
                }
            });
        });

    }
    //
//    @Override
//    public void returnWeatherData(String data) {
//        Log.d("temp ", " "+ jsonManager.jsonParcer(data));
//    }



//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//
//        MenuItem searchViewMenuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
//        ImageView v = (ImageView) searchView.findViewById(androidx.appcompat.R.id.search_button);
//        //v.setImageResource(R.drawable.search_icon); //Changing the image
//
//        String searchFor = searchView.getQuery().toString();
//        if (!searchFor.isEmpty()) {
//            searchView.setIconified(false);
//            searchView.setQuery(searchFor, false);
//        }
//
//        searchView.setQueryHint("Search for cities");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //Do your search
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //if(newText.isEmpty())
//                    //clearSearch();
//                return false;
//            }
//        });
//        return false;
//    }
}
