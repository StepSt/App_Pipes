package Fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import admin.example.com.pipes_v2.Main;
import admin.example.com.pipes_v2.R;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class FragmentAbout extends Fragment implements Main.OnBackPressedListener
{
    FragmentStart start;
    @Override
    public void onBackPressed() {
        start= new FragmentStart();
        FragmentTransaction transaction_start = getFragmentManager().beginTransaction();
        transaction_start.replace(R.id.container,start).commit();
        getActivity().setTitle(R.string.menu_calk);
    }

    private static final int REQUEST_FINE_LOCATION = 11;
    MapView mapView;
    GoogleMap map;
    private OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_about, container, false);
        WebView mWebView;
        //region WebView
        mWebView = (WebView) v.findViewById(R.id.webView_about);
        Log.d("Log",">"+mWebView);


        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        //mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setInitialScale(100);
        // указываем страницу загрузки
        mWebView.loadUrl("https://2gis.ru/ufa/geo/2393172957201846%2C56.12686%2C54.773242/firm/2393065583037146?queryState=center%2F56.126136%2C54.773602%2Fzoom%2F18");
        mWebView.setWebViewClient(new MyWebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        //endregion
        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}
