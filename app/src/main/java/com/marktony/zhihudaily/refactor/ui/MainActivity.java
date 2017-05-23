package com.marktony.zhihudaily.refactor.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;
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

        initFragments(savedInstanceState);

        mBottomNavigationView.setOnNavigationItemSelectedListener((menuItem -> {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.nav_timeline:
                    showFragment(mTimelineFragment);
                break;

                case R.id.nav_movie:
                    showFragment(mInfoFragment);
                    break;

                case R.id.nav_settings:
                    showFragment(mInfoFragment);
                    break;

                case R.id.nav_bookmarks:
                    showFragment(mInfoFragment);
                    break;

                default:
                    break;

            }
            ft.commit();
            return true;
        }));
    }

    private void initFragments(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mTimelineFragment = new TimelineFragment();
            mInfoFragment = new InfoFragment();
        } else {
            mTimelineFragment = (TimelineFragment) fm.getFragment(savedInstanceState, TimelineFragment.class.getSimpleName());
            mInfoFragment = (InfoFragment) fm.getFragment(savedInstanceState, InfoFragment.class.getSimpleName());
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        if (fragment instanceof TimelineFragment) {
            if (!mTimelineFragment.isAdded()) {
                fm.beginTransaction().add(R.id.container, mTimelineFragment, TimelineFragment.class.getSimpleName()).commit();
            }
            fm.beginTransaction().show(mTimelineFragment).commit();
            hideFragments(fm, mInfoFragment);

        } else if (fragment instanceof InfoFragment) {
            if (!mInfoFragment.isAdded()) {
                fm.beginTransaction().add(R.id.container, mInfoFragment, InfoFragment.class.getSimpleName()).commit();
            }
            fm.beginTransaction().show(mInfoFragment).commit();
            hideFragments(fm, mTimelineFragment);

        }
    }

    private void hideFragments(FragmentManager fm, Fragment... framgents) {
        for (Fragment f : framgents) {
            fm.beginTransaction().hide(f).commit();
        }
    }
}
