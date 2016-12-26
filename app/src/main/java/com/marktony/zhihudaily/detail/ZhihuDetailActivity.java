package com.marktony.zhihudaily.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;

public class ZhihuDetailActivity extends AppCompatActivity {

    private ZhihuDetailFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        if (savedInstanceState != null) {
            fragment = (ZhihuDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        } else {
            fragment = ZhihuDetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        new ZhihuDetailPresenter(this, fragment);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "fragment", fragment);

    }
}