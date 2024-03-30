package com.programmerdatch.webviewdatchapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView logoImage;
    private Handler checkConn;
    private Runnable checkRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoImage = findViewById(R.id.logoImage);

        AnimateImage animateImage = new AnimateImage(MainActivity.this);
        animateImage.applyFadeOutAnimation(logoImage);

        checkConn = new Handler();
        checkRun = new Runnable() {
            @Override
            public void run() {
                NetworkChecker networkChecker = new NetworkChecker();
                if(networkChecker.isNetworkAvailable(MainActivity.this))
                {
                    Toast.makeText(MainActivity.this, "No Network!", Toast.LENGTH_LONG).show();
                    checkConn.postDelayed(checkRun, 10000);
                }
                else
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 6000);
                }
            }
        };

        checkConn.post(checkRun);

    }
}