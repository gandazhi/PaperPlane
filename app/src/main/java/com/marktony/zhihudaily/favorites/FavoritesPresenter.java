package com.marktony.zhihudaily.favorites;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.favorites.FavoritesContract;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter {

    @NonNull
    private final FavoritesContract.View mView;

    public FavoritesPresenter(@NonNull FavoritesContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFavorites() {

    }
}
