package Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import admin.example.com.pipes_v2.BoxAdapter;
import admin.example.com.pipes_v2.Main;
import admin.example.com.pipes_v2.Product;
import admin.example.com.pipes_v2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentBuy.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentBuy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBuy extends Fragment implements Main.OnBackPressedListener
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

    public  ArrayList<Product> products = new ArrayList<Product>();
    public  BoxAdapter boxAdapter;
    String message_string = "";

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
    AlertDialog.Builder ad;
    private OnFragmentInteractionListener mListener;

    FragmentCalc calc;
    FragmentRegistration registration;

    public FragmentBuy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBuy.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBuy newInstance(String param1, String param2) {
        FragmentBuy fragment = new FragmentBuy();
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
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_buy, container, false);
        calc = new FragmentCalc();
        registration = new FragmentRegistration();
        //Bundle bundle = getArguments();
        //products = bundle.getParcelableArrayList("key");
        //Toast.makeText(getActivity(), bundle.getString("key").toString(), Toast.LENGTH_LONG).show();

        TextView txt_count = (TextView) v.findViewById(R.id.textView18);
        //region Sent

        final EditText editText_delivery = (EditText) v.findViewById(R.id.editText_delivery);
        final TextView txt_adressDelivery = (TextView) v.findViewById(R.id.txt_addressDelivery);
        final CheckBox checkBox_delivery = (CheckBox) v.findViewById(R.id.checkBox_delivery);
        checkBox_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_delivery.isChecked())
                {
                    editText_delivery.setVisibility(View.VISIBLE);
                    txt_adressDelivery.setVisibility(View.VISIBLE);
                }else{
                    editText_delivery.setVisibility(View.INVISIBLE);
                    txt_adressDelivery.setVisibility(View.INVISIBLE);
                }
            }
        });
        //region Clear_Button
        //region Window
        String title = "Запрос";
        String message = "Вы уверены";
        String button1String = "Нет";
        String button2String = "Да";
        ad = new AlertDialog.Builder(getContext());
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast toast = Toast.makeText(getActivity(),
                "OK ", Toast.LENGTH_SHORT);
        toast.show();
    }
});
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_COUNT, "0");
                editor.apply();
                start= new FragmentStart();
                FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                transaction_start.replace(R.id.container,start).commit();
            }
        });

        //endrion
        LinearLayout clear = (LinearLayout) v.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences.Editor editor = mSettings.edit();
                //editor.putString(APP_PREFERENCES_COUNT, "0");
                //editor.apply();
                //start= new FragmentStart();
                //FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                //transaction_start.replace(R.id.container,start).commit();
                ad.show();
            }
        });
        //endregion
        //region Sent_Button
        LinearLayout sent = (LinearLayout) v.findViewById(R.id.sent);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // проверяем, первый ли раз открывается программа
                SharedPreferences.Editor editor = mSettings.edit();
                String hasIDuser = mSettings.getString("User_name", "");
                //hasIDuser = "Сергей";

                if (hasIDuser == "") {
                    // выводим нужную активность
                    FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                    transaction_start.replace(R.id.container,registration).commit();
                }
                else
                {
                    //region EMail
                    message_string += "Имя заказчика: " + hasIDuser + "\n"
                    + "Телефон заказчика: " + mSettings.getString("User_numphome","") + "\n";
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"legionst@gmail.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "Заказ от " + hasIDuser + " " + mSettings.getString("User_numphome",""));
                    for (Product p : boxAdapter.getBox()) {
                        if (p.box)
                            switch (p.type_pipes){
                                case "Труба БУ":
                                case "Труба восстановленная":
                                case "Труба НКТ":
                                    message_string += "- Тип: " + p.type_pipes + " Диаметр наружный: " + p.D + " Диаметр внутренний: " + p.S + " Длинна: " + p.L + " Масса: " + p.M + "\n";
                                    break;
                                case "Штанга":
                                    message_string += "- Тип: " + p.type_pipes + " Диаметр наружный: " + p.D + " Длинна: " + p.L + " Масса: " + p.M + "\n";
                                    break;
                                case "Труба профильная":
                                    message_string += "- Тип: " + p.type_pipes + " Сторона А: " + p.D + " Сторона B: " + p.S + " Длинна: " + p.L + " Масса: " + p.M + "\n";
                                    break;
                                case "Лист профильный":
                                    message_string += "- Тип: " + p.type_pipes + " Длинна: " + p.L + " Масса: " + p.M + "\n";
                            }
                    }
                    if (checkBox_delivery.isChecked()) {
                        message_string += "\n" + "Доставка по адресу: " + editText_delivery.getText().toString();
                    }
                    Log.d("Log>","message" + message_string);
                    email.putExtra(Intent.EXTRA_TEXT, message_string);
                    //email.setType("message/rfc822");
                    email.setType("application/octet-stream");
                    startActivity(Intent.createChooser(email, "Выберите email клиент :"));
                    editor.putString(APP_PREFERENCES_COUNT, "0");
                    editor.apply();

                    //endregion
                }
                //start = new FragmentStart();
                //FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                //transaction_start.replace(R.id.container,start).commit();
            }
        });
//endregion
        //endregion
        int count = Integer.parseInt(mSettings.getString(APP_PREFERENCES_COUNT, ""));
        int i = 1;
        for (i = 1; i<= count; i++)
        {
            switch (i)
            {
                case 1:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR1, ""));
                    break;
                case 2:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR2, ""));
                    break;
                case 3:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR3, ""));
                    break;
                case 4:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR4, ""));
                    break;
                case 5:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR5, ""));
                    break;
                case 6:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR6, ""));
                    break;
                case 7:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR7, ""));
                    break;
                case 8:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR8, ""));
                    break;
                case 9:
                    xmlP(mSettings.getString(APP_PREFERENCES_NABOR9, ""));
                    break;
            }
        }
        boxAdapter = new BoxAdapter(getActivity().getApplicationContext(), products);
        ListView lvMain = (ListView) v.findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
        txt_count.setText(boxAdapter.getCount() + "");

//region Button_order
        Button button_order = (Button) v.findViewById(R.id.button_order);
        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Товары в корзине:";
                for (Product p : boxAdapter.getBox()) {
                    if (p.box)
                        result += "\n" + p.type_pipes;
                }
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            }
        });
//endregion
        // Inflate the layout for this fragment
        return v;
    }
public void xmlP (String nabor){
    String tmp = "";
    String type_pipes = "";
    String L = "";
    String D = "";
    String S = "";
    String M = "";
    Boolean box = false ;


    try {
        //XmlPullParser xpp = getResources().getXml(R.xml.data);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        //Log.d("LOG",">" + mSettings.getString(APP_PREFERENCES_TYPE, ""));

        xpp.setInput( new StringReader(nabor));

        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                // начало документа
                case XmlPullParser.START_DOCUMENT:
                    break;
                // начало тэга
                case XmlPullParser.START_TAG:
                    tmp = xpp.getName().toString();
                    break;
                // конец тэга
                case XmlPullParser.END_TAG:
                    break;
                // содержимое тэга
                case XmlPullParser.TEXT:
                    switch(tmp)
                {
                    case "type_pipes":
                        type_pipes = xpp.getText().toString();
                        Log.d("LOG",">" + xpp.getText().toString());
                        break;
                    case "L":
                        L = xpp.getText().toString();
                        Log.d("LOG",">" + xpp.getText().toString());
                        break;
                    case "D":
                        D = xpp.getText().toString();
                        Log.d("LOG",">" + xpp.getText().toString());
                        break;
                    case "S":
                        S = xpp.getText().toString();
                        Log.d("LOG",">" + xpp.getText().toString());
                        break;
                    case "M":
                        M = xpp.getText().toString();
                        Log.d("LOG",">" + xpp.getText().toString());
                        break;
                    case "box":
                        box = Boolean.parseBoolean(xpp.getText().toString());
                        break;
                }
                    break;
                default:
                    break;
            }
            // следующий элемент
            xpp.next();
        }
        products.add(new Product(type_pipes,L,D,S,M,box));
    } catch (XmlPullParserException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
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
