package com.marktony.zhihudaily.refactor.timeline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.marktony.zhihudaily.R;


/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class TimelineFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int pageCount = 2;
    private String[] titles;

    private ZhihuDailyFragment mZhihuFragment;
    private DoubanMomentFragment mDoubanFragment;

    public TimelineFragmentPagerAdapter(@NonNull FragmentManager fm,
                                        @NonNull Context context,
                                        @NonNull ZhihuDailyFragment zhihuDailyFragment,
                                        @NonNull DoubanMomentFragment doubanMomentFragment) {
        super(fm);
        titles = new String[]{
                context.getString(R.string.zhihu_daily),
                context.getString(R.string.douban_moment)};
        this.mZhihuFragment = zhihuDailyFragment;
        this.mDoubanFragment = doubanMomentFragment;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return mZhihuFragment;
        }
        return mDoubanFragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
