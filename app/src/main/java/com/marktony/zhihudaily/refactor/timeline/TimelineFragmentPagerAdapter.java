package com.marktony.zhihudaily.refactor.timeline;

import android.content.Context;
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

    public TimelineFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        titles = new String[]{
                context.getString(R.string.zhihu_daily),
                context.getString(R.string.douban_moment)};
    }

    @Override
    public Fragment getItem(int i) {
        return TimelinePageFragment.newInstance(i + 1);
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
