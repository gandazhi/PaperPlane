package com.marktony.zhihudaily.refactor.timeline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentFragment extends Fragment implements DoubanMomentContract.View {

    private DoubanMomentContract.Presenter mPresenter;

    // View references.
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private View mEmptyView;

    public DoubanMomentFragment() {
        // Requires default empty constructor.
    }

    public static DoubanMomentFragment newInstance() {
        return new DoubanMomentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_page, container, false);

        initViews(view);

        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.refresh();
        });

        return view;
    }

    @Override
    public void setPresenter(DoubanMomentContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mRefreshLayout.post(() ->
                mRefreshLayout.setRefreshing(active)
        );
    }

    @Override
    public void showResult(@NonNull List<DoubanMomentNews> list) {

    }
}
