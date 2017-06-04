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
    public void load(boolean forceUpdate, boolean clearCache, long date) {

        if (forceUpdate) {
            mView.setLoadingIndicator(true);
        }
        mRepository.getDoubanMomentNews(forceUpdate, clearCache, date, new DoubanMomentNewsDataSource.LoadDoubanMomentDailyCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
                if (mView.isActive()) {
                    mView.showResult(list);
                    mView.setLoadingIndicator(false);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mView.isActive()) {
                    mView.setLoadingIndicator(false);
                }
            }
        });
    }

    @Override
    public void outdate(int id) {
        mRepository.outdateItem(id);
    }
}
