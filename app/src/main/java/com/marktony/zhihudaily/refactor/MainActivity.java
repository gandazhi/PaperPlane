package com.marktony.zhihudaily.refactor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.info.InfoFragment;
import com.marktony.zhihudaily.refactor.timeline.TimelineFragment;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    private TimelineFragment mTimelineFragment;
    private InfoFragment mInfoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re_activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            mTimelineFragment = new TimelineFragment();
            mInfoFragment = new InfoFragment();
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener((menuItem -> {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_timeline:
                    ft.replace(R.id.container, mTimelineFragment);
                break;

                case R.id.nav_movie:
                    ft.replace(R.id.container, mInfoFragment);
                    break;

                case R.id.nav_settings:
                    ft.replace(R.id.container, mInfoFragment);
                    break;

                case R.id.nav_bookmarks:
                    ft.replace(R.id.container, mInfoFragment);
                    break;

                default:
                    break;

            }
            ft.commit();
            return true;
        }));
    }
}
