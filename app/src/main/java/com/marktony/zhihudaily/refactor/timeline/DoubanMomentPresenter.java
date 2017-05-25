package com.marktony.zhihudaily.refactor.timeline;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentNewsDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentNewsRepository;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentPresenter implements DoubanMomentContract.Presenter {

    @NonNull
    private final DoubanMomentContract.View mView;

    @NonNull
    private final DoubanMomentNewsRepository mRepository;

    private boolean mFirstLoad = true;

    public DoubanMomentPresenter(@NonNull DoubanMomentContract.View view,
                                 @NonNull DoubanMomentNewsRepository repository) {
        this.mView = view;
        this.mRepository = repository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void load(boolean isLoadMore, boolean forceUpdate, long date) {
        if (mFirstLoad) {
            mView.setLoadingIndicator(true);
            mFirstLoad = false;
        }

        if (forceUpdate) {
            mRepository.refreshDoubanMomentNews();
        }

        mRepository.getDoubanMomentNews(isLoadMore, date, new DoubanMomentNewsDataSource.LoadDoubanMomentDailyCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
                mView.showResult(list);
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onDataNotAvailable() {
                mView.setLoadingIndicator(false);
            }
        });
    }
}
