package com.marktony.zhihudaily.refactor.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentContentRepository;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.refactor.data.source.local.DoubanMomentContentLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.local.DoubanMomentNewsLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.DoubanMomentContentRemoteDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.DoubanMomentNewsRemoteDataSource;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String KEY_ARTICLE_TYPE = "KEY_ARTICLE_TYPE";
    public static final String KEY_ARTICLE_ID = "KEY_ARTICLE_ID";
    public static final String KEY_ARTICLE_TITLE = "KEY_ARTICLE_TITLE";

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
                    .add(R.id.container, mDetailsFragment, DetailsFragment.class.getSimpleName())
                    .commit();
        }

        new DetailsPresenter(mDetailsFragment,
                DoubanMomentNewsRepository.getInstance(DoubanMomentNewsRemoteDataSource.getInstance(), DoubanMomentNewsLocalDataSource.getInstance()),
                DoubanMomentContentRepository.getInstance(DoubanMomentContentRemoteDataSource.getInstance(), DoubanMomentContentLocalDataSource.getInstance()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mDetailsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, DetailsFragment.class.getSimpleName(), mDetailsFragment);
        }
    }
}
