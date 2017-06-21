package com.marktony.zhihudaily.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.data.ContentType;
import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.data.PostType;
import com.marktony.zhihudaily.details.DetailsActivity;
import com.marktony.zhihudaily.service.CacheService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentFragment extends Fragment
        implements DoubanMomentContract.View {

    private DoubanMomentContract.Presenter mPresenter;

    // View references.
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private View mEmptyView;
    private FloatingActionButton fab;

    private DoubanMomentNewsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private int mYear, mMonth, mDay;

    private boolean mIsLoadingMore = false;
    private boolean mIsFirstLoad = true;
    private int mListSize = 0;

    public DoubanMomentFragment() {
        // Requires default empty constructor.
    }

    public static DoubanMomentFragment newInstance() {
        return new DoubanMomentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        mMonth = Calendar.getInstance().get(Calendar.MONTH);
        mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_page, container, false);

        initViews(view);

        mRefreshLayout.setOnRefreshListener(() -> {
            Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            mPresenter.load(true, true, c.getTimeInMillis());
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy >0 ) {
                    if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mListSize - 1 && !mIsLoadingMore) {
                        loadMore();
                    }
                    fab.hide();
                } else {
                    fab.show();
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
        c.set(mYear, mMonth, mDay);
        if (mIsFirstLoad) {
            mPresenter.load(true, false, c.getTimeInMillis());
            mIsFirstLoad = false;
        } else {
            mPresenter.load(false, false, c.getTimeInMillis());
        }
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
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmptyView = view.findViewById(R.id.empty_view);
        fab = getActivity().findViewById(R.id.fab);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(active));
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showResult(@NonNull List<DoubanMomentNewsPosts> list) {
        if (mAdapter == null) {
            mAdapter = new DoubanMomentNewsAdapter(list, getContext());
            mAdapter.setItemClickListener((v, i) -> {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.KEY_ARTICLE_ID, list.get(i).getId());
                intent.putExtra(DetailsActivity.KEY_ARTICLE_TYPE, ContentType.TYPE_DOUBAN_MOMENT);
                intent.putExtra(DetailsActivity.KEY_ARTICLE_TITLE, list.get(i).getTitle());
                startActivity(intent);

                mPresenter.outdate(list.get(i).getId());

            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateData(list);
        }

        mListSize = list.size();
        mIsLoadingMore = false;

        if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mListSize - 1) {
            loadMore();
        }

        mEmptyView.setVisibility(list.isEmpty() ? View.VISIBLE : View.INVISIBLE);

        for (DoubanMomentNewsPosts item : list) {
            Intent intent = new Intent(CacheService.BROADCAST_FILTER_ACTION);
            intent.putExtra(CacheService.FLAG_ID, item.getId());
            intent.putExtra(CacheService.FLAG_TYPE, PostType.TYPE_DOUBAN);
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }
    }

    private void loadMore() {
        mIsLoadingMore = true;
        Calendar c = Calendar.getInstance();
        c.set(mYear, mMonth, --mDay);
        mPresenter.load(true, false, c.getTimeInMillis());
    }

    public void showDatePickerDialog() {
        DatePickerDialog dialog = DatePickerDialog.newInstance((datePickerDialog, year, monthOfYear, dayOfMonth) -> {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            Calendar c = Calendar.getInstance();
            c.set(mYear, mMonth, mDay);
            mPresenter.load(true, true, c.getTimeInMillis());

        }, mYear, mMonth, mDay);

        dialog.setMaxDate(Calendar.getInstance());

        Calendar minDate = Calendar.getInstance();
        minDate.set(2014, 5, 12);
        dialog.setMinDate(minDate);

        dialog.vibrate(false);
        dialog.show(getActivity().getFragmentManager(), DoubanMomentFragment.class.getSimpleName());

    }

}
