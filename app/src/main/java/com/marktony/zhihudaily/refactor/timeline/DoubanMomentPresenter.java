package com.marktony.zhihudaily.refactor.timeline;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.source.DoubanMomentNewsRepository;

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
    public void refresh() {

    }

    @Override
    public void load() {

    }
}
