package com.marktony.zhihudaily.refactor;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.timeline.TimelineFragment;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class MainActivity extends LifecycleActivity {

    private BottomNavigationView bottomNavigationView;
    private TimelineFragment timelineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re_activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            timelineFragment = new TimelineFragment();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener((menuItem -> {
            FragmentManager fm = getSupportFragmentManager();

            fm.beginTransaction()
                    .replace(R.id.container, timelineFragment)
                    .commit();
            return true;
        }));
    }
}
