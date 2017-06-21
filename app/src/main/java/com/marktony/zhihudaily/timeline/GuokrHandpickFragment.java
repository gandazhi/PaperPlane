package com.marktony.zhihudaily.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.data.PostType;
import com.marktony.zhihudaily.details.DetailsActivity;
import com.marktony.zhihudaily.service.CacheService;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickFragment extends Fragment
        implements GuokrHandpickContract.View{

    private GuokrHandpickContract.Presenter mPresenter;

    // View references.
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private View mEmptyView;

    private GuokrHandpickNewsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private int mOffset = 0;

    private boolean mIsFirstLoad = true;
    private boolean mIsLoadingMore = false;
    private int mListSize = 0;

    public GuokrHandpickFragment() {
        // Requires an empty constructor.
    }

    public static GuokrHandpickFragment newInstance() {
        return new GuokrHandpickFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_page, container, false);

        initViews(view);

        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.load(true, true, 0, 20);
            mOffset = 0;
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mLayoutManager.findLastCompletelyVisibleItemPosition() == mListSize - 1 && !mIsLoadingMore) {
                    loadMore();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        if (mIsFirstLoad) {
            mPresenter.load(true, false, mOffset, 20);
            mIsFirstLoad = false;
        } else {
            mPresenter.load(false, false, mOffset, 20);
        }
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
    public void showResult(@NonNull List<GuokrHandpickNewsResult> list) {
        mOffset = list.size();
        if (mAdapter == null) {
            mAdapter = new GuokrHandpickNewsAdapter(list, getContext());
            mAdapter.setItemClickListener((v, i) -> {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.KEY_ARTICLE_ID, list.get(i).getId());
                intent.putExtra(DetailsActivity.KEY_ARTICLE_TYPE, ContentType.TYPE_GUOKR_HANDPICK);
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

        for (GuokrHandpickNewsResult item : list) {
            Intent intent = new Intent(CacheService.BROADCAST_FILTER_ACTION);
            intent.putExtra(CacheService.FLAG_ID, item.getId());
            intent.putExtra(CacheService.FLAG_TYPE, PostType.TYPE_GUOKR);
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }

    }

    @Override
    public void setPresenter(GuokrHandpickContract.Presenter presenter) {
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

    private void loadMore() {
        mIsLoadingMore = true;
        mPresenter.load(true, false, mOffset, 20);
    }

}
