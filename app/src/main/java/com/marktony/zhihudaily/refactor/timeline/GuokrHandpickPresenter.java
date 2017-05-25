package com.marktony.zhihudaily.refactor.timeline;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.GuokrHandpickNewsRepository;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickPresenter implements GuokrHandpickContract.Presenter {

    @NonNull
    private final GuokrHandpickContract.View mView;

    @NonNull
    private final GuokrHandpickNewsRepository mRepository;

    private boolean mFirstLoad = true;

    public GuokrHandpickPresenter(@NonNull GuokrHandpickContract.View  view,
                                  @NonNull GuokrHandpickNewsRepository repository) {
        this.mView = view;
        this.mRepository = repository;
        this.mView.setPresenter(this);
    }

    @Override
    public void load(boolean forceUpdate, int offset, int limit) {
        if (mFirstLoad) {
            mView.setLoadingIndicator(true);
            mFirstLoad = false;
        }

        if (forceUpdate) {
            mRepository.refreshGuokrHandpickNews();
        }

        mRepository.getGuokrHandpickNews(offset, limit, new GuokrHandpickDataSource.LoadGuokrHandpickNewsCallback() {
            @Override
            public void onNewsLoad(@NonNull List<GuokrHandpickNews.Result> list) {
                mView.showResult(list);
                mView.setLoadingIndicator(false);
            }

            @Override
            public void onDataNotAvailable() {
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void start() {

    }
}
