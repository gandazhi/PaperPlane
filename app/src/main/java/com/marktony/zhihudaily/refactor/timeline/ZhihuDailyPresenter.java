package com.marktony.zhihudaily.refactor.timeline;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;
import com.marktony.zhihudaily.refactor.data.source.ZhihuDailyNewsDataSource;
import com.marktony.zhihudaily.refactor.data.source.ZhihuDailyNewsRepository;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    @NonNull
    private final ZhihuDailyContract.View mView;

    @NonNull
    private final ZhihuDailyNewsRepository mRepository;

    private boolean mFirstLoad = true;

    public ZhihuDailyPresenter(@NonNull ZhihuDailyContract.View view,
                               @NonNull ZhihuDailyNewsRepository repository) {
        this.mView = view;
        this.mRepository = repository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadNews(boolean isLoadMore, boolean forceUpdate, long date) {

        if (mFirstLoad) {
            mView.setLoadingIndicator(true);
            mFirstLoad = false;
        }

        if (forceUpdate) {
            mRepository.refreshZhihuDailyNews();
        }

        mRepository.getZhihuDailyNews(isLoadMore, date, new ZhihuDailyNewsDataSource.LoadZhihuDailyNewsCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<ZhihuDailyNews.Question> list) {
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
