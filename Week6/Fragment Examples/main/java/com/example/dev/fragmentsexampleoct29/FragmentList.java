package com.example.dev.fragmentsexampleoct29;

import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentList extends ListFragment {

    ListView listview;
    View view;
    String[] array=new String[]{"Android","BlackBerry","Windows","Iphone"};

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState)
    {

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1,R.id.text1,array);

        setListAdapter(adapter);
        return super.onCreateView(i,container,savedInstanceState);
    }


    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        listview=getListView();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(), (CharSequence) listview.getItemAtPosition(i),Toast.LENGTH_LONG).show();
            }
        });
    }

}
