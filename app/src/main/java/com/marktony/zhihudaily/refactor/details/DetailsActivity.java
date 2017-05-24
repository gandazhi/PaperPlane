package com.marktony.zhihudaily.refactor.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String KEY_ARTICLE_TYPE = "KEY_ARTICLE_TYPE";
    public static final String KEY_ARTICLE_ID = "KEY_ARTICLE_ID";

    private DetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        if (savedInstanceState != null) {
            mDetailsFragment = (DetailsFragment) getSupportFragmentManager().getFragment(savedInstanceState, DetailsFragment.class.getSimpleName());
        } else {
            mDetailsFragment = DetailsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mDetailsFragment)
                    .commit();
        }

        //new DetailsPresenter();
    }
}
