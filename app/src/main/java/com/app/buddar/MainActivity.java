package com.app.buddar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * MainActivity Class
 * Created by Luis F. Alvarez
 */
public class MainActivity extends AppCompatActivity {
    //TODO: change time interval for splash to 2000
    private static int SPLASH_TIME_OUT = 1000;

    /**
     * Splash
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
        //Retrofit init
    }
}
