package com.marktony.zhihudaily.refactor.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marktony.zhihudaily.R;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public class FavoritesFragment extends Fragment
        implements FavoritesContract.View {

    private FavoritesContract.Presenter mPresenter;

    public FavoritesFragment() {

    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.re_fragment_info, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void setPresenter(FavoritesContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {
        
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }
}
