package Fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import admin.example.com.pipes_v2.Main;
import admin.example.com.pipes_v2.Product;
import admin.example.com.pipes_v2.R;

public class FragmentCalcSheet extends Fragment implements Main.OnBackPressedListener
{
    FragmentStart start;
    @Override
    public void onBackPressed() {
        start= new FragmentStart();
        FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
        transaction_start.replace(R.id.container,start).commit();
        getActivity().setTitle(R.string.menu_calk);
    }
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
    public static  final String APP_PREFERENCES_D = "user_d";
    SharedPreferences mSettings;
    FragmentBuy buy;

    DialogFragment dlg1;
    Bundle bundle1;

    TextView txt_c;
    TextView txt_D;

    EditText edit_D;
    EditText edit_S;
    private OnFragmentInteractionListener mListener;

    public ArrayList<Product> products = new ArrayList<Product>();
    boolean flag_add_one;
    public FragmentCalcSheet() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity();
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_calc_sheet, container, false);
//region FragmentCalc
        buy = new FragmentBuy();
        final TextView txt_pipes = (TextView) v.findViewById(R.id.txt_pipes);

        txt_pipes.setText(mSettings.getString(APP_PREFERENCES_TYPE, ""));

        final EditText edit_L = (EditText) v.findViewById(R.id.edit_L);


        edit_L.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.ADD);
        final TextView txt_M = (TextView) v.findViewById(R.id.txt_M);

        final TextView txt_L = (TextView) v.findViewById(R.id.txt_L);

        //edit_S.setText("0");
//region Edit
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

                    if(edit_L.getText().length() != 0){
                        Double res_pM = 4.455*Double.parseDouble(edit_L.getText().toString());
                        txt_M.setText(String.format( Locale.US, "%.2f", res_pM));
                    }
                }else txt_M.setText("***");
            }
        });
        /**
        edit_L.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_L.setText(edit_L.getText().toString());
                if(edit_L.getText().length() != 0){
                    Double res_pM =4.455*Double.parseDouble(edit_L.getText().toString());
                    txt_M.setText(String.format( Locale.US, "%.2f", res_pM));
                }
                return true;
            }
        });
*/
//endregion
        final LinearLayout layout_add = (LinearLayout) v.findViewById(R.id.add);
// region By_Button
        LinearLayout layout_by = (LinearLayout) v.findViewById(R.id.by);
        layout_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    flag_add_one = true;
                    layout_add.callOnClick();
                }
                else {
                    FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                    transaction_start.replace(R.id.container,buy).commit();
                }
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

        layout_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = null;
                if(edit_L.getText().toString().length() == 0)
                {
                    Toast toast = Toast.makeText(getActivity(),
                            "Введите параметры для расчета", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    count = count +1;
                    txt_c.setText(Integer.toString(count));

                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(APP_PREFERENCES_COUNT, Integer.toString(count));

                    Product product = new Product(txt_pipes.getText().toString()," L = " + txt_L.getText().toString(),"",""," M = " + txt_M.getText().toString(), true);
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
                    FrameLayout frameLayout = (FrameLayout) getActivity().findViewById(R.id.calc_sheet);
                    frameLayout.startAnimation(animation);
                    editor.apply();
                    edit_L.setText("");
                    if(flag_add_one){
                        flag_add_one = false;
                        FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                        transaction_start.replace(R.id.container,buy).commit();
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
                edit_L.setText("");
            }
        });
        //endregion
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("key",products);
        buy.setArguments(bundle);
// endregion

        // Inflate the layout for this fragment
        return v;
    }
    public static String writeUsingNormalOperation(Product product) {
        String format =
                "<?xml version='1.0' encoding='UTF-8'?>" +
                        "<data>" + "<pipis>" +"<type_pipes>%s</type_pipes>" +"<L>%s</L>" + "<D>%s</D>" +"<S>%s</S>" + "<M>%s</M>" + "<box>%s</box>" + "</pipis>" + "</data>";
        return String.format(format,product.type_pipes, product.L, product.D, product.S, product.M, product.box + "");
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
