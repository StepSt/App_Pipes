package admin.example.com.pipes_v2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import Fragments.FragmentPrice;
import Fragments.FragmentAbout;
import Fragments.FragmentCalc;
import Fragments.FragmentStart;

import android.support.v4.app.FragmentActivity;


public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentPrice price;
    FragmentAbout about;
    FragmentCalc calc;
    FragmentStart start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //region Fragment
        price = new FragmentPrice();
        about = new FragmentAbout();
        calc = new FragmentCalc();
        start = new FragmentStart();

        setTitle(getResources().getText(R.string.menu_calk));
        FragmentTransaction transaction_start = getSupportFragmentManager().beginTransaction();
        transaction_start.replace(R.id.container,start).commit();
        //endregion

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_call) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+79173763215"));
            startActivity(callIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_calk) {
            setTitle(getResources().getText(R.string.menu_calk));
            transaction.replace(R.id.container,start);
        } else if (id == R.id.nav_about) {
            setTitle(getResources().getText(R.string.menu_about));
            transaction.replace(R.id.container,about);

        } else if (id == R.id.nav_price) {
            setTitle(getResources().getText(R.string.menu_price));
            transaction.replace(R.id.container,price);

        } else if (id == R.id.nav_call) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+79173763215"));
            startActivity(callIntent);
            setTitle(getResources().getText(R.string.menu_call));

        } else if (id == R.id.nav_developer) {

        }transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
