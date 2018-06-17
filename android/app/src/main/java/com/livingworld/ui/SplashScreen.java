package com.livingworld.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.livingworld.R;
import com.livingworld.util.Preferences;
import com.thefinestartist.finestwebview.FinestWebView;
import com.thefinestartist.finestwebview.listeners.WebViewListener;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(Preferences.getLoginFlag(getApplicationContext())) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }
                    finish();
                }
            }
        }).start();
    }

    @Override
    public void onBackPressed() {

    }
}
