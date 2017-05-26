package com.marktony.zhihudaily.refactor.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.data.ContentType;
import com.marktony.zhihudaily.refactor.data.source.local.GuokrHandpickContentLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.local.ZhihuDailyContentLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.GuokrHandpickContentRemoteDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.ZhihuDailyContentRemoteDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentContentRepository;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.refactor.data.source.local.DoubanMomentContentLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.local.DoubanMomentNewsLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.DoubanMomentContentRemoteDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.DoubanMomentNewsRemoteDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.GuokrHandpickContentRepository;
import com.marktony.zhihudaily.refactor.data.source.repository.ZhihuDailyContentRepository;

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

        ContentType type = (ContentType) getIntent().getSerializableExtra(KEY_ARTICLE_TYPE);
        if (type == ContentType.TYPE_ZHIHU_DAILY) {
            new DetailsPresenter(mDetailsFragment,
                    ZhihuDailyContentRepository.getInstance(ZhihuDailyContentRemoteDataSource.getInstance(), ZhihuDailyContentLocalDataSource.getInstance()));
        } else if (type == ContentType.TYPE_DOUBAN_MOMENT) {
            new DetailsPresenter(mDetailsFragment,
                    DoubanMomentNewsRepository.getInstance(DoubanMomentNewsRemoteDataSource.getInstance(), DoubanMomentNewsLocalDataSource.getInstance()),
                    DoubanMomentContentRepository.getInstance(DoubanMomentContentRemoteDataSource.getInstance(), DoubanMomentContentLocalDataSource.getInstance()));
        } else if (type == ContentType.TYPE_GUOKR_HANDPICK) {
            new DetailsPresenter(mDetailsFragment,
                    GuokrHandpickContentRepository.getInstance(GuokrHandpickContentRemoteDataSource.getInstance(), GuokrHandpickContentLocalDataSource.getInstance()));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZhihuDailyContentRepository.destroyInstance();
        DoubanMomentContentRepository.destroyInstance();
        GuokrHandpickContentRepository.destroyInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mDetailsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, DetailsFragment.class.getSimpleName(), mDetailsFragment);
        }
    }
}
