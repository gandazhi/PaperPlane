package com.marktony.zhihudaily.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.app.App;

/**
 * Created by Lizhaotailang on 2016/8/12.
 */

public class DoubanDetailActivity extends AppCompatActivity {

    private DoubanDetailFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        if (savedInstanceState != null) {
            fragment = (DoubanDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        } else {
            fragment = DoubanDetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        new DoubanDetailPresenter(this, fragment);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "fragment", fragment);
    }
}
