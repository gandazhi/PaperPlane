package com.marktony.zhihudaily.refactor.timeline;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.BaseView;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class TimelineFragment extends LifecycleFragment implements BaseView {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        initViews(view);

        return view;
    }


    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void initViews(View view) {

        mViewPager = view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TimelineFragmentPagerAdapter(getChildFragmentManager(), getContext()));

        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
