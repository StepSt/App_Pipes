package Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import admin.example.com.pipes_v2.R;

/**
 * Created by StepByStep on 16.05.2016.
 */
    public class FragmentStart extends ListFragment {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TYPE = "Type_pipes"; // выбранный тип
    SharedPreferences mSettings;

    FragmentCalc calc;

    FragmentCalcRod calcRod;
    FragmentCalcCut calcCut;


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Труба БУ", "Труба восстановленная", "Труба НКТ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.spinner_item_menu, R.id.TextViewPlus_item_menu, values);
        setListAdapter(adapter);
        Context context = getActivity();
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        String[] values = new String[] { "Штанга", "Труба профильная", "Лист профилный"};

        final ListView listView1 = (ListView) v.findViewById(R.id.list1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.spinner_item_menu_2, R.id.TextViewPlus_item_menu, values);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                calcRod = new FragmentCalcRod();
                calcCut = new FragmentCalcCut();
                FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                switch (position)
                {
                    case 0:
                        transaction_start.replace(R.id.container,calcRod).commit();
                        break;
                    case 1:
                        transaction_start.replace(R.id.container,calcCut).commit();
                        break;
                }
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_TYPE, listView1.getItemAtPosition(position).toString());
                editor.apply();
            }
        });

        return v;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        calc = new FragmentCalc();
        FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
        transaction_start.replace(R.id.container,calc).commit();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_TYPE, l.getItemAtPosition(position).toString());
        editor.apply();
    }
}
