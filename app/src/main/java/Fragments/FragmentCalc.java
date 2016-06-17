package Fragments;

import android.app.Activity;
import android.app.DialogFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Xml;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;


import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Locale;

import admin.example.com.pipes_v2.Dialog_pipes_by;
import admin.example.com.pipes_v2.Main;
import admin.example.com.pipes_v2.Product;
import admin.example.com.pipes_v2.R;

public class FragmentCalc extends Fragment implements Main.OnBackPressedListener
{
    FragmentStart start;
        @Override
        public void onBackPressed() {
            start= new FragmentStart();
            FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
            transaction_start.replace(R.id.container,start).commit();
            getActivity().setTitle(R.string.menu_calk);
        }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public int count;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TYPE = "Type_pipes"; //
    public static final String APP_PREFERENCES_NABOR1 = "nabor1"; //
    public static final String APP_PREFERENCES_NABOR2 = "nabor2"; //
    public static final String APP_PREFERENCES_NABOR3 = "nabor3"; //
    public static final String APP_PREFERENCES_NABOR4 = "nabor4"; //
    public static final String APP_PREFERENCES_NABOR5 = "nabor5"; //
    public static final String APP_PREFERENCES_NABOR6 = "nabor6"; //
    public static final String APP_PREFERENCES_NABOR7 = "nabor7"; //
    public static final String APP_PREFERENCES_NABOR8 = "nabor8"; //
    public static final String APP_PREFERENCES_NABOR9 = "nabor9"; //
    public static final String APP_PREFERENCES_NABOR10 = "nabor10"; //
    public static  final String APP_PREFERENCES_COUNT = "Count"; // cчетчик

    SharedPreferences mSettings;
    FragmentBuy buy;

    public int[] array_bu = new int[] {114,159,168,219,273,300,325,377,426,530,720,820,1220};
    DialogFragment dlg1;
    Bundle bundle1;
    TextView txt_c;
    TextView txt_D;
    EditText edit_D;

    public ArrayList<Product> products = new ArrayList<Product>();

    //private OnFragmentInteractionListener mListener;


//region DialogFragment
private static final int REQUEST_WEIGHT = 1;
    private static final int REQUEST_ANOTHER_ONE = 2;

    public void openWeightPicker() {
        Log.d("Log","openWeightPicker");
        Dialog_pipes_by fragment = new Dialog_pipes_by();
        fragment.setTargetFragment(this, REQUEST_WEIGHT);

        fragment.setArguments(bundle1);

        fragment.show(getFragmentManager(), fragment.getClass().getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_WEIGHT:
                    int weight = data.getIntExtra(Dialog_pipes_by.TAG_WEIGHT_SELECTED, -1);
                    //используем полученные результаты

                    edit_D.setText(weight + "");
                    //txt_D.setText(weight + "");

                    txt_D.setText(" D = " + weight + "");

                    //...
                    break;
                case REQUEST_ANOTHER_ONE:
                    //...
                    break;
                //обработка других requestCode
            }
            //updateUI();
        }
    }

    //endregion



    public FragmentCalc() {
        // Required empty public constructor
    }

    public static FragmentCalc newInstance(String param1, String param2) {
        FragmentCalc fragment = new FragmentCalc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Context context = getActivity();
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_calc, container, false);
        buy = new FragmentBuy();

        final TextView txt_pipes = (TextView) v.findViewById(R.id.txt_pipes);
        txt_pipes.setText(mSettings.getString(APP_PREFERENCES_TYPE, ""));

        edit_D = (EditText) v.findViewById(R.id.edit_D);
        final EditText edit_S = (EditText) v.findViewById(R.id.edit_S);
        final EditText edit_L = (EditText) v.findViewById(R.id.edit_L);

        edit_D.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.ADD);
        edit_S.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.ADD);
        edit_L.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.ADD);

        final TextView txt_M = (TextView) v.findViewById(R.id.txt_M);
        txt_D = (TextView) v.findViewById(R.id.txt_D);
        final TextView txt_S = (TextView) v.findViewById(R.id.txt_S);
        final TextView txt_L = (TextView) v.findViewById(R.id.txt_L);

//region Edit
        edit_D.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_D.setText(edit_D.getText().toString());
                if (edit_D.getText().toString().length() != 0){

                    if(edit_S.getText().length() != 0 && edit_L.getText().length() != 0){
                        Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                        txt_M.setText(String.format( Locale.US, "%.2f", res_pM));
                    }
                }else txt_M.setText("***");
            }
        });
        edit_S.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_S.setText(edit_S.getText().toString());
                if (edit_S.getText().toString().length() != 0){

                    if(edit_D.getText().length() != 0 && edit_L.getText().length() != 0){
                        Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                        txt_M.setText(String.format( Locale.US, "%.2f", res_pM));
                    }
                }
            }
        });
        edit_L.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_L.setText(edit_L.getText().toString());
                if (edit_L.getText().toString().length() != 0){

                    if(edit_D.getText().length() != 0 && edit_S.getText().length() != 0){
                        Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                        txt_M.setText(" M = " + String.format( Locale.US, "%.2f", res_pM));
                    }
                }else txt_M.setText("***");
            }
        });
/**
        edit_D.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_D.setText(edit_D.getText().toString());
                if(edit_S.getText().length() != 0 && edit_L.getText().length() != 0){
                    Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                    txt_M.setText(" M = " + String.format( Locale.US, "%.2f", res_pM));
                }
                return true;
            }
        });
        edit_S.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_S.setText(edit_S.getText().toString());
                if(edit_D.getText().length() != 0 && edit_L.getText().length() != 0){
                    Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                    txt_M.setText(String.format( Locale.US, "%.2f", res_pM));
                }
                return true;
            }
        });
        edit_L.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_L.setText(edit_L.getText().toString());
                if(edit_S.getText().length() != 0 && edit_D.getText().length() != 0){
                    Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                    txt_M.setText(String.format( Locale.US, "%.2f", res_pM));
                }
                hideSoftKeyboard(getActivity());
                return true;
            }
        });
*/
//endregion
// region By_Button
        LinearLayout layout_by = (LinearLayout) v.findViewById(R.id.by);
        layout_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                transaction_start.replace(R.id.container,buy).commit();
            }
        });

        txt_c = (TextView) v.findViewById(R.id.txt_count);
        txt_c.setText(mSettings.getString(APP_PREFERENCES_COUNT, ""));
        try {
            count = Integer.parseInt(txt_c.getText().toString());
        }catch (Exception e){
            count=0;

        }
        //endregion
//region Add_Button
        LinearLayout layout_add = (LinearLayout) v.findViewById(R.id.add);
        layout_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = null;
                if(edit_D.getText().toString().length() == 0 || edit_S.getText().toString().length() == 0 || edit_L.getText().toString().length() == 0)
                {
                    Toast toast = Toast.makeText(getActivity(),
                            "Введите параметры для расчета", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    for(int i=0; i<= array_bu.length; i++) {
                        if (array_bu[i] == Integer.parseInt(edit_D.getText().toString())){
                            count = count +1;
                            txt_c.setText(Integer.toString(count));
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putString(APP_PREFERENCES_COUNT, Integer.toString(count));
                            Product product = new Product(txt_pipes.getText().toString(),"L = " + txt_L.getText().toString(),"D = " + txt_D.getText().toString(),"S = " + txt_S.getText().toString(),"M = " + txt_M.getText().toString(), true);
                            switch (count){
                                case 1 :
                                    editor.putString(APP_PREFERENCES_NABOR1,writeUsingNormalOperation(product));
                                    break;
                                case 2 :
                                    editor.putString(APP_PREFERENCES_NABOR2,writeUsingNormalOperation(product));
                                    break;
                                case 3 :
                                    editor.putString(APP_PREFERENCES_NABOR3,writeUsingNormalOperation(product));
                                    break;
                                case 4 :
                                    editor.putString(APP_PREFERENCES_NABOR4,writeUsingNormalOperation(product));
                                    break;
                                case 5 :
                                    editor.putString(APP_PREFERENCES_NABOR5,writeUsingNormalOperation(product));
                                    break;
                                case 6 :
                                    editor.putString(APP_PREFERENCES_NABOR6,writeUsingNormalOperation(product));
                                    break;
                                case 7 :
                                    editor.putString(APP_PREFERENCES_NABOR7,writeUsingNormalOperation(product));
                                    break;
                                case 8 :
                                    editor.putString(APP_PREFERENCES_NABOR8,writeUsingNormalOperation(product));
                                    break;
                                case 9 :
                                    editor.putString(APP_PREFERENCES_NABOR9,writeUsingNormalOperation(product));
                                    break;
                            }
                            animation = AnimationUtils.loadAnimation(getActivity(),R.anim.mycombo);
                            FrameLayout frameLayout = (FrameLayout) getActivity().findViewById(R.id.calc_pipes);
                            frameLayout.startAnimation(animation);
                            editor.apply();
                            edit_D.setText("");
                            edit_S.setText("");
                            edit_L.setText("");
                            break;
                        }
                        else {
                            if(array_bu[i] > Integer.parseInt(edit_D.getText().toString())){
                                Dialog_pipes_by fragment_1 = new Dialog_pipes_by();
                                bundle1 = new Bundle();
                                bundle1.putString("min",array_bu[i-1]+"");
                                bundle1.putString("max", array_bu[i]+"");
                                fragment_1.setArguments(bundle1);
                                openWeightPicker();
                                break;
                            }
                        }
                    }
                }


            }
        });
        //endregion
        //region Clear_Button
        LinearLayout cleaner = (LinearLayout) v.findViewById(R.id.clear);
        cleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_D.setText("");
                edit_S.setText("");
                edit_L.setText("");
            }
        });
        //endregion

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("key",products);
        buy.setArguments(bundle);
        return v;
    }
    public static String writeUsingNormalOperation(Product product) {
        String format =
                "<?xml version='1.0' encoding='UTF-8'?>" +
                        "<data>" + "<pipes>" +"<type_pipes>%s</type_pipes>" +"<L>%s</L>" + "<D>%s</D>" +"<S>%s</S>" + "<M>%s</M>" + "<box>%s</box>" + "</pipes>" + "</data>";
        return String.format(format,product.type_pipes, product.L, product.D, product.S, product.M, product.box + "");
    }
    // TODO: Rename method, update argument and hook method into UI event
    /**
        public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
        */
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}


/**
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void setArguments(Bundle args) {

    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /**
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/



