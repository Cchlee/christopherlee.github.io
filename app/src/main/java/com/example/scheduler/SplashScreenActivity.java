package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import android.content.Intent;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3500)
                .withBackgroundResource(R.drawable.login_background)
                .withLogo(R.drawable.ic_timetable)
                .withAfterLogoText("Scheduler3")
                .withFooterText("Copyright 2019");

        //config.getFooterTextView().setTextColor(Color.WHITE);
        config.getLogo().getLayoutParams().height = 400;
        config.getLogo().getLayoutParams().width = 400;
        config.getLogo().requestLayout();
        //If Line 31 throws a NPE comment it out. Otherwise use line 33 instead
        //config.getLogo().setScaleType(ImageView.ScaleType.FIT_XY);

        View splash = config.create();
        setContentView(splash);
    }
}