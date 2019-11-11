package com.app.buddar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.app.buddar.FirstActivity.pref;
import static com.app.buddar.util.RestAdapter.getUnsafeOkHttpClient;

/**
 * DashBoard activity
 * Luis Felipe Alvarez Sanchez
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    //nav
    private Toolbar toolbar;
    private DrawerLayout menu;
    private ImageView hamb_button, eventosButton;
    private LinearLayout cart_button;
    private NavigationView nav_view;
    private LinearLayout logoutButton;
    private TextView home_button;


    FrameLayout fragment_container;
    //Fragments init
    ProfileFragment profileFragment;
    DashboardFragment dashboardFragment;
    ConnectionsFragment connectionsFragment;
    HistoryFragment historyFragment;
    HelpFragment helpFragment;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Api apiInterface = retrofit.create(Api.class);

    /**
     * onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Hamburger button - toggles menu
        hamb_button = (ImageView) findViewById(R.id.hamb_button);
        hamb_button.setOnClickListener(this);
        //Home button
        home_button = (TextView) findViewById(R.id.home_button);
        home_button.setOnClickListener(this);
        //Eventos button
        eventosButton = (ImageView) findViewById(R.id.eventosButton);
        eventosButton.setOnClickListener(this);
        //Logout button
        logoutButton = (LinearLayout) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);
        //Side menu and toolbar initialization
        nav_view = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set username
        menu = (DrawerLayout) findViewById(R.id.menu);

        //Sets the toolbar and drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, menu, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        menu.addDrawerListener(toggle);

        //Fragments
        fragment_container = findViewById(R.id.fragment_container);
        dashboardFragment = new DashboardFragment();
        profileFragment = new ProfileFragment();
        historyFragment = new HistoryFragment();
        helpFragment = new HelpFragment();
        connectionsFragment = new ConnectionsFragment();

        setFragment(dashboardFragment);
    }

    /**
     * Side menu onNavigationItemSelected
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Logouts the user and clears the shared preferences
            case R.id.profile:
                setFragment(profileFragment);
                break;
            case R.id.connections:
                setFragment(connectionsFragment);
                break;
            case R.id.history:
                setFragment(historyFragment);
                break;
            case R.id.help:
                setFragment(helpFragment);
                break;
            case R.id.home_button:
                setFragment(dashboardFragment);
                break;
        }

        menu.closeDrawer(Gravity.START, false);
        return false;
    }

    /**
     * onClick method
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hamb_button:
                menu.openDrawer(Gravity.START);
                break;
            case R.id.logoutButton:
                Log.d("log", "logout");
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent logout = new Intent(DashboardActivity.this, FirstActivity.class);
                startActivity(logout);
                finish();
                break;
            case R.id.home_button:
                setFragment(dashboardFragment);
                break;
        }
    }

    /**
     * Sets the fragment
     *
     * @param fragment
     */
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    /**
     * onBackPressed method
     */
    @Override
    public void onBackPressed() {
    }
}
