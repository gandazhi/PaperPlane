package com.marktony.zhihudaily.favorites;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.data.source.repository.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.data.source.repository.GuokrHandpickNewsRepository;
import com.marktony.zhihudaily.data.source.repository.ZhihuDailyNewsRepository;
import com.marktony.zhihudaily.favorites.FavoritesContract;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter {

    @NonNull
    private final FavoritesContract.View mView;

    @NonNull
    private final ZhihuDailyNewsRepository mZhihuRepository;

    @NonNull
    private final DoubanMomentNewsRepository mDoubanRepository;

    @NonNull
    private final GuokrHandpickNewsRepository mGuokrRepository;

    public FavoritesPresenter(@NonNull FavoritesContract.View view,
                              @NonNull ZhihuDailyNewsRepository zhihuRepository,
                              @NonNull DoubanMomentNewsRepository doubanRepository,
                              @NonNull GuokrHandpickNewsRepository guokrRepository) {
        mView = view;
        mView.setPresenter(this);
        this.mZhihuRepository = zhihuRepository;
        this.mDoubanRepository = doubanRepository;
        this.mGuokrRepository = guokrRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFavorites() {

    }
}
