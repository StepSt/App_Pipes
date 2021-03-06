package Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import admin.example.com.pipes_v2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRegistration.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRegistration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegistration extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;
    FragmentBuy buy;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentRegistration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegistration.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegistration newInstance(String param1, String param2) {
        FragmentRegistration fragment = new FragmentRegistration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View v = inflater.inflate(R.layout.fragment_fragment_registration, container, false);
        //final SharedPreferences.Editor editor = mSettings.edit();
        final EditText name = (EditText) v.findViewById(R.id.edit_name);
        final EditText sname = (EditText) v.findViewById(R.id.edit_sname);
        final EditText trname = (EditText) v.findViewById(R.id.edit_trname);
        final EditText numphome = (EditText) v.findViewById(R.id.edit_numphone);
        LinearLayout save = (LinearLayout) v.findViewById(R.id.save);
        buy = new FragmentBuy();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().length() == 0 && numphome.getText().length() == 0){
                    Toast toast = Toast.makeText(getActivity(),
                            "Заполните поля все поля отмеченные звездочкой (*)", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    final SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString("User_name",name.getText().toString());
                    editor.putString("User_sname",sname.getText().toString());
                    editor.putString("User_trname",trname.getText().toString());
                    editor.putString("User_numphome",numphome.getText().toString());
                    editor.commit();
                    FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
                    transaction_start.replace(R.id.container,buy).commit();
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
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
