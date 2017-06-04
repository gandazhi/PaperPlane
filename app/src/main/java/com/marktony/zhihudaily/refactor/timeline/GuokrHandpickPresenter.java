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

    public GuokrHandpickPresenter(@NonNull GuokrHandpickContract.View  view,
                                  @NonNull GuokrHandpickNewsRepository repository) {
        this.mView = view;
        this.mRepository = repository;
        this.mView.setPresenter(this);
    }

    @Override
    public void load(boolean forceUpdate, boolean clearCache, int offset, int limit) {

        if (forceUpdate) {
            mView.setLoadingIndicator(true);
        }
        mRepository.getGuokrHandpickNews(forceUpdate, clearCache, offset, limit, new GuokrHandpickDataSource.LoadGuokrHandpickNewsCallback() {
            @Override
            public void onNewsLoad(@NonNull List<GuokrHandpickNews.Result> list) {
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

    @Override
    public void start() {

    }
}
