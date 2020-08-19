package com.example.midterm2_skeletonproject;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class AddYearAndModelDialog extends DialogFragment {
    NumberPicker year_picker;
    Spinner modelSpinner;
    Context app_context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_year_level,container,false);

        return view;
    }
}
