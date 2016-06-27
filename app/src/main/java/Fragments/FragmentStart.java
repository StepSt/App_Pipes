package Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import admin.example.com.pipes_v2.R;

/**
 * Created by StepByStep on 16.05.2016.
 */
    public class FragmentStart extends ListFragment {
    public int count;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TYPE = "Type_pipes"; // выбранный тип
    public static  final String APP_PREFERENCES_COUNT = "Count"; // cчетчик
    SharedPreferences mSettings;

    FragmentCalc calc;

    FragmentCalcRod calcRod;
    FragmentCalcCut calcCut;
    FragmentCalcSheet calcSheet;
    TextView txt_c;
    FragmentBuy buy;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Труба БУ", "Труба восстановленная", "Труба НКТ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.spinner_item_menu, R.id.TextViewPlus_item_menu, values);
        setListAdapter(adapter);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        String[] values = new String[] { "Штанга", "Труба профильная", "Лист профильный"};
        Context context = getActivity();
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        txt_c = (TextView) v.findViewById(R.id.txt_count);
        txt_c.setText(mSettings.getString(APP_PREFERENCES_COUNT, ""));
        try {
            count = Integer.parseInt(txt_c.getText().toString());
        }catch (Exception e){
            count=0;

        }
        final ListView listView1 = (ListView) v.findViewById(R.id.list1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.spinner_item_menu_2, R.id.TextViewPlus_item_menu, values);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                calcRod = new FragmentCalcRod();
                calcCut = new FragmentCalcCut();
                calcSheet = new FragmentCalcSheet();
                FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                switch (position)
                {
                    case 0:
                        transaction_start.replace(R.id.container,calcRod).commit();
                        break;
                    case 1:
                        transaction_start.replace(R.id.container,calcCut).commit();
                        break;
                    case 2:
                        transaction_start.replace(R.id.container,calcSheet).commit();
                        break;
                }
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_TYPE, listView1.getItemAtPosition(position).toString());
                editor.apply();
            }
        });
// region By_Button
        buy = new FragmentBuy();
        LinearLayout layout_by = (LinearLayout) v.findViewById(R.id.by);
        layout_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    Toast toast = Toast.makeText(getActivity(),
                            "В корзине нет товаров", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                    transaction_start.replace(R.id.container,buy).commit();
                }
            }
        });
//endregion
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
