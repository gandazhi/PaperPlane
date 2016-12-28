package com.marktony.zhihudaily.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by Lizhaotailang on 2016/8/23.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // the 'theme' has two value, 0 and 1
        // 0 --> day theme, 1 --> night theme
        if (getSharedPreferences("user_settings",MODE_PRIVATE).getInt("theme", 0) == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

}
