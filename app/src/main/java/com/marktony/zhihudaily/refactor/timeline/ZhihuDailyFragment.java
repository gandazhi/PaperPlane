package com.marktony.zhihudaily.refactor.timeline;

import android.content.Intent;
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
import com.marktony.zhihudaily.refactor.data.ContentType;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;
import com.marktony.zhihudaily.refactor.details.DetailsActivity;

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

    private ZhihuDailyNewsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

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
            mPresenter.loadNews(false, false, c.getTimeInMillis());
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的item position
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部并且是向下滑动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        loadMore();
                    }
                }

                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;
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
        mPresenter.loadNews(false, true, c.getTimeInMillis());
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
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(active));
    }

    @Override
    public void showResult(@NonNull List<ZhihuDailyNews.Question> list) {
        if (mAdapter == null) {
            mAdapter = new ZhihuDailyNewsAdapter(list, getContext());
            mAdapter.setItemClickListener((v, i) -> {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.KEY_ARTICLE_ID, list.get(i).getId());
                intent.putExtra(DetailsActivity.KEY_ARTICLE_TYPE, ContentType.TYPE_ZHIHU_DAILY);
                intent.putExtra(DetailsActivity.KEY_ARTICLE_TITLE, list.get(i).getTitle());
                startActivity(intent);

                mPresenter.outdate(list.get(i).getId());

            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateData(list);
        }
        if (mLayoutManager.findLastVisibleItemPosition() == -1) {
            loadMore();
        }
        mEmptyView.setVisibility(list.isEmpty() ? View.VISIBLE : View.INVISIBLE);
    }

    private void loadMore() {
        Calendar c = Calendar.getInstance();
        c.set(mYear, mMonth, --mDay);
        mPresenter.loadNews(true, false, c.getTimeInMillis());
    }

}
