package com.example.midterm1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import android.app.DialogFragment;

public class AddYearAndLevelDialog extends DialogFragment implements View.OnClickListener {
    private saveYearandLevelEventListener saveYearAndLevel;

    public interface saveYearandLevelEventListener {
        public void saveYearAndLevelFun(int year,int level);
    }

    EditText text;
    NumberPicker year_picker;
    NumberPicker level_picker;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity){
            a = (Activity) context;
            saveYearAndLevel = (saveYearandLevelEventListener) a;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_year_level,container,false);
        year_picker = (NumberPicker) view.findViewById(R.id.yearPicker);
        level_picker = (NumberPicker) view.findViewById(R.id.levelPicker);
        year_picker.setMinValue(1);
        year_picker.setMaxValue(4);
        year_picker.setValue(1);

        level_picker.setMinValue(1);
        level_picker.setMaxValue(4);
        level_picker.setValue(1);

        Button button = (Button)view.findViewById(R.id.saveCourse);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle result = new Bundle();
        result.putString("bundleKey", "result");
        int year = year_picker.getValue();
        int level = level_picker.getValue();
      //  Course newCourse = new Course(text.getText().toString(), (double) grade);


        saveYearAndLevel.saveYearAndLevelFun(year,level);
        dismiss();
    }


}
