/*
package com.example.midterm_sanghyuklee;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddYearAndModelDialog1 {
    NumberPicker year_picker;
    Spinner modelSpinner;
    Button save_btn;
    Dialog dlg;
    Context app_context;

    ArrayList<Car> item = new ArrayList<>();


    String owner;
    int image;
    String model;

    public AddYearAndModelDialog1(Context context, String owner, int image) {
        this.app_context = context;
        this.owner = owner;
        this.image = image;
    }




    public void callDialog() {

        dlg = new Dialog(app_context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.add_year_level);

        dlg.show();

        save_btn = (Button) dlg.findViewById(R.id.savebutton);
        modelSpinner = (Spinner) dlg.findViewById(R.id.carnamespinner);

        year_picker = (NumberPicker) dlg.findViewById(R.id.numberPicker);
        year_picker.setMinValue(2010);
        year_picker.setMaxValue(2020);
        year_picker.setValue(2019);

        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.add(new Car(owner, image, year_picker.getValue(), model));
                saveClickListener.onSaveClicked(item);
                dlg.dismiss();
            }
        });

    }
}*/
