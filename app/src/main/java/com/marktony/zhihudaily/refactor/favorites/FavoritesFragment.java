package com.marktony.zhihudaily.refactor.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private View mEmptyView;

    public FavoritesFragment() {
        // Empty constructor is needed as a fragment
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.re_framgent_favorites, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void setPresenter(FavoritesContract.Presenter presenter) {
        if(presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mEmptyView = view.findViewById(R.id.empty_view);
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(active));
    }
}
