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
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyFragment extends Fragment
        implements ZhihuDailyContract.View {

    private ZhihuDailyContract.Presenter mPresenter;

    // View references.
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private View mEmptyView;

    private LinearLayoutManager mLayoutManager;
    private ZhihuDailyNewsAdapter mAdapter;

    private int mYear, mMonth, mDay;

    public ZhihuDailyFragment() {
        // An empty constructor is needed as a fragment.
    }

    public static ZhihuDailyFragment newInstance() {
        return new ZhihuDailyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_page, container, false);

        initViews(view);

        mRefreshLayout.setOnRefreshListener(() -> {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            mPresenter.loadNews(false, c.getTimeInMillis());
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    Calendar c = Calendar.getInstance();
                    c.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                    c.set(mYear, mMonth, --mDay);
                    mPresenter.loadNews(false, c.getTimeInMillis());
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        c.set(mYear, mMonth, mDay);
        mPresenter.loadNews(true, c.getTimeInMillis());
    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmptyView = view.findViewById(R.id.empty_view);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mRefreshLayout.post(() ->
           mRefreshLayout.setRefreshing(active)
        );
    }

    @Override
    public void showResult(@NonNull List<ZhihuDailyNews.Question> list) {
        if (mAdapter == null) {
            mAdapter = new ZhihuDailyNewsAdapter(list, getContext());
            mAdapter.setItemClickListener((v, i) -> {

            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateData(list);
        }
        mEmptyView.setVisibility(list.isEmpty() ? View.VISIBLE : View.INVISIBLE);
    }
}
