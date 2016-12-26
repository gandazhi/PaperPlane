package com.marktony.zhihudaily.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.app.App;

/**
 * 2016.6.15 黎赵太郎
 * 果壳文章阅读
 */
public class GuokrDetailActivity extends AppCompatActivity {

    private GuokrDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        if (savedInstanceState != null) {
            fragment = (GuokrDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        } else {
            fragment = GuokrDetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        new GuokrDetailPresenter(this, fragment);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "fragment", fragment);
    }

}
