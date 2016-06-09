package Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import admin.example.com.pipes_v2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRateLoad.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRateLoad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRateLoad extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentRateLoad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRateLoad.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRateLoad newInstance(String param1, String param2) {
        FragmentRateLoad fragment = new FragmentRateLoad();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_rate_load, container, false);
        TextView txt_11 = (TextView) v.findViewById(R.id.txt11);
        txt_11.setTextColor(Color.YELLOW);
        TextView txt_12 = (TextView) v.findViewById(R.id.txt12);
        txt_12.setTextColor(Color.YELLOW);
        TextView txt_13 = (TextView) v.findViewById(R.id.txt13);
        txt_13.setTextColor(Color.YELLOW);

        TextView txt_31 = (TextView) v.findViewById(R.id.txt31);
txt_31.setTextColor(Color.YELLOW);
        TextView txt_32 = (TextView) v.findViewById(R.id.txt32);
txt_32.setTextColor(Color.YELLOW);
        TextView txt_33 = (TextView) v.findViewById(R.id.txt33);
txt_33.setTextColor(Color.YELLOW);

        TextView txt_51 = (TextView) v.findViewById(R.id.txt51);
txt_51.setTextColor(Color.YELLOW);
        TextView txt_52 = (TextView) v.findViewById(R.id.txt52);
txt_52.setTextColor(Color.YELLOW);
        TextView txt_53 = (TextView) v.findViewById(R.id.txt53);
txt_53.setTextColor(Color.YELLOW);

        TextView txt_71 = (TextView) v.findViewById(R.id.txt71);
txt_71.setTextColor(Color.YELLOW);
        TextView txt_72 = (TextView) v.findViewById(R.id.txt72);
txt_72.setTextColor(Color.YELLOW);
        TextView txt_73 = (TextView) v.findViewById(R.id.txt73);
txt_73.setTextColor(Color.YELLOW);

        TextView txt_91 = (TextView) v.findViewById(R.id.txt91);
txt_91.setTextColor(Color.YELLOW);
        TextView txt_92 = (TextView) v.findViewById(R.id.txt92);
txt_92.setTextColor(Color.YELLOW);
        TextView txt_93 = (TextView) v.findViewById(R.id.txt93);
txt_93.setTextColor(Color.YELLOW);

        TextView txt_111 = (TextView) v.findViewById(R.id.txt111);
txt_111.setTextColor(Color.YELLOW);
        TextView txt_112 = (TextView) v.findViewById(R.id.txt112);
txt_112.setTextColor(Color.YELLOW);
        TextView txt_113 = (TextView) v.findViewById(R.id.txt113);
txt_113.setTextColor(Color.YELLOW);

        TextView txt_131 = (TextView) v.findViewById(R.id.txt131);
txt_131.setTextColor(Color.YELLOW);
        TextView txt_132 = (TextView) v.findViewById(R.id.txt132);
txt_132.setTextColor(Color.YELLOW);
        TextView txt_133 = (TextView) v.findViewById(R.id.txt133);
txt_133.setTextColor(Color.YELLOW);

        TextView txt_151 = (TextView) v.findViewById(R.id.txt151);
txt_151.setTextColor(Color.YELLOW);
        TextView txt_152 = (TextView) v.findViewById(R.id.txt152);
txt_152.setTextColor(Color.YELLOW);
        TextView txt_153 = (TextView) v.findViewById(R.id.txt153);
txt_153.setTextColor(Color.YELLOW);

        TextView txt_171 = (TextView) v.findViewById(R.id.txt171);
txt_171.setTextColor(Color.YELLOW);
        TextView txt_172 = (TextView) v.findViewById(R.id.txt172);
txt_172.setTextColor(Color.YELLOW);
        TextView txt_173 = (TextView) v.findViewById(R.id.txt173);
txt_173.setTextColor(Color.YELLOW);
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
