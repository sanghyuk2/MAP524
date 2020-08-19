package com.example.w5p2_gridview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    GridView simpleList;
    private Menu menu;
    ArrayList countryList=new ArrayList<>();
    static int language = 1;// 1 for english 2 for arabic
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            language = savedInstanceState.getInt("lang");
            if (language == 2){
                Locale locale = new Locale("ar");
                Locale.setDefault(locale);

                Configuration config = new Configuration();
                config.locale = locale;

                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getApplicationContext(), "Arabic", Toast.LENGTH_SHORT).show();
                language = 2;//app language is Arabic

                finish();
                startActivity(getIntent());

            }
        }


        setContentView(R.layout.activity_main);
        updateMenuTitles();
        simpleList = (GridView) findViewById(R.id.simpleGridView);

        countryList.add(new Country(R.drawable.ar,R.string.argentina));
       countryList.add(new Country(R.drawable.br,R.string.Brazil));
        countryList.add(new Country(R.drawable.ca,R.string.Canada));
        countryList.add(new Country(R.drawable.cn,R.string.China));
        countryList.add(new Country(R.drawable.de,R.string.Germany));
        countryList.add(new Country(R.drawable.eg,R.string.Egyte));
        countryList.add(new Country(R.drawable.es,R.string.Spain));
        countryList.add(new Country(R.drawable.eu,R.string.EuropeanUnion));
        countryList.add(new Country(R.drawable.fr,R.string.France));
        countryList.add(new Country(R.drawable.gb,R.string.England));
        countryList.add(new Country(R.drawable.it,R.string.Italy));


        MyAdapter myAdapter=new MyAdapter(this,R.layout.grid_view_items,countryList);
        simpleList.setAdapter(myAdapter);
    }

    private void updateMenuTitles() {
        if (menu != null) {
            MenuItem langMenuItem = menu.findItem(R.id.change_id);
            if (language == 1) {// if app language is 1 then menu to be Arabic
                langMenuItem.setTitle(R.string.Arabic);
            } else {// if app languege is 2 then menu to be english
                langMenuItem.setTitle(R.string.English);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        this.menu = menu;
        updateMenuTitles();
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_id:{
                if (item.getTitle().equals("Arabic")) {
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);

                    Configuration config = new Configuration();
                    config.locale = locale;

                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(), "Arabic", Toast.LENGTH_SHORT).show();
                    language = 2;//app language is Arabic

                    finish();
                    startActivity(getIntent());

                }else {
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;

                    language = 1;// App language is English
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lang",language);
    }
}
