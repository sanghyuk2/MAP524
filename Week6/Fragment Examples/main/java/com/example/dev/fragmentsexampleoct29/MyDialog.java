package com.example.dev.fragmentsexampleoct29;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends DialogFragment implements View.OnClickListener{

    public static MyDialog newInstant (int textIdToPassToFragment){

        MyDialog m = new MyDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("help_text",textIdToPassToFragment);

        m.setArguments(bundle);

        return m;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialgo_fragment,container,false);

       TextView textInDialgo =  (TextView)view.findViewById(R.id.helptextid);
        textInDialgo.setText(getArguments().getInt("help_text"));

        Button button = (Button)view.findViewById(R.id.close_but_id);
        button.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
