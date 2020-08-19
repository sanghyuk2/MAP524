package com.example.midterm_sanghyuklee;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddYearAndModelDialog extends DialogFragment {

    NumberPicker year_picker;
    Spinner modelSpinner;
    Button save_btn;

    private SaveClickListener saveClickListener;

    int year;
    String model;
    Bitmap image;
    String owner;

    public AddYearAndModelDialog(Bitmap image, String owner) {
        this.image = image;
        this.owner = owner;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                model = (String) parent.getItemAtPosition(0);
            }
        });

        year_picker.setMinValue(2010);
        year_picker.setMaxValue(2020);
        year_picker.setValue(2019);
        year = year_picker.getValue();

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveClickListener.onSaveClicked(new Car(owner, year_picker.getValue(), model), image);
                getDialog().dismiss();
            }
        });


    }

    interface SaveClickListener {
        void onSaveClicked(Car item, Bitmap img);
    }

    public void setSaveClickListener(SaveClickListener saveClickListener) {
        this.saveClickListener = saveClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_year_level, container);

        year_picker = view.findViewById(R.id.numberPicker);
        modelSpinner = view.findViewById(R.id.carnamespinner);
        save_btn = view.findViewById(R.id.savebutton);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        return view;
    }
}
