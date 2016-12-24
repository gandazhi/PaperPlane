package com.marktony.zhihudaily.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;

public class ZhihuDetailActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        ZhihuDetailFragment fragment = ZhihuDetailFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        new ZhihuDetailPresenter(this, fragment);

    }
}