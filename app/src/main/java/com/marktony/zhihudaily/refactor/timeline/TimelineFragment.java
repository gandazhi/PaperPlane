package com.marktony.zhihudaily.refactor.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.data.source.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.refactor.data.source.ZhihuDailyNewsRepository;
import com.marktony.zhihudaily.refactor.data.source.local.DoubanMomentNewsLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.local.ZhihuDailyNewsLocalDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.DoubanMomentNewsRemoteDataSource;
import com.marktony.zhihudaily.refactor.data.source.remote.ZhihuDailyNewsRemoteDataSource;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class TimelineFragment extends Fragment {

    private FloatingActionButton mFab;

    private ZhihuDailyFragment mZhihuFragment;
    private DoubanMomentFragment mDoubanFragment;

    public TimelineFragment() {
        // Requires the empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            FragmentManager fm = getChildFragmentManager();
            mZhihuFragment = (ZhihuDailyFragment) fm.getFragment(savedInstanceState, ZhihuDailyFragment.class.getSimpleName());
            mDoubanFragment = (DoubanMomentFragment) fm.getFragment(savedInstanceState, DoubanMomentFragment.class.getSimpleName());
        } else {
            mZhihuFragment = ZhihuDailyFragment.newInstance();
            mDoubanFragment = DoubanMomentFragment.newInstance();
        }

        new ZhihuDailyPresenter(mZhihuFragment, ZhihuDailyNewsRepository.getInstance(
                ZhihuDailyNewsRemoteDataSource.getInstance(),
                ZhihuDailyNewsLocalDataSource.getInstance()));

        new DoubanMomentPresenter(mDoubanFragment, DoubanMomentNewsRepository.getInstance(
                DoubanMomentNewsRemoteDataSource.getInstance(),
                DoubanMomentNewsLocalDataSource.getInstance()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        initViews(view);

        mFab.setOnClickListener(v -> {

        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager fm = getChildFragmentManager();
        fm.putFragment(outState, ZhihuDailyFragment.class.getSimpleName(), mZhihuFragment);
        fm.putFragment(outState, DoubanMomentFragment.class.getSimpleName(), mDoubanFragment);
    }

    private void initViews(View view) {
        ViewPager mViewPager = view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TimelineFragmentPagerAdapter(
                getChildFragmentManager(),
                getContext(),
                mZhihuFragment,
                mDoubanFragment));

        TabLayout mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mFab = view.findViewById(R.id.fab);
    }

}
