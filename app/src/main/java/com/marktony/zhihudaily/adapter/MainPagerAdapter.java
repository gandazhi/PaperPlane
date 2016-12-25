package com.marktony.zhihudaily.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.homepage.DoubanMomentFragment;
import com.marktony.zhihudaily.homepage.DoubanMomentPresenter;
import com.marktony.zhihudaily.homepage.GuokrFragment;
import com.marktony.zhihudaily.homepage.GuokrPresenter;
import com.marktony.zhihudaily.homepage.ZhihuDailyFragment;
import com.marktony.zhihudaily.homepage.ZhihuDailyPresenter;

/**
 * Created by Lizhaotailang on 2016/8/10.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private final Context context;

    private GuokrFragment guokrFragment;
    private ZhihuDailyFragment zhihuFragment;
    private DoubanMomentFragment doubanFragment;

    private ZhihuDailyPresenter zhihuPresenter;
    private GuokrPresenter guokrPresenter;
    private DoubanMomentPresenter doubanPresenter;

    public GuokrFragment getGuokrFragment() {
        return guokrFragment;
    }

    public ZhihuDailyFragment getZhihuFragment() {
        return zhihuFragment;
    }

    public DoubanMomentFragment getDoubanFragment() {
        return doubanFragment;
    }

    public DoubanMomentPresenter getDoubanPresenter() {
        return doubanPresenter;
    }

    public GuokrPresenter getGuokrPresenter() {
        return guokrPresenter;
    }

    public ZhihuDailyPresenter getZhihuPresenter() {
        return zhihuPresenter;
    }

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        titles = new String[] {
                context.getResources().getString(R.string.zhihu_daily),
                context.getResources().getString(R.string.guokr_handpick),
                context.getResources().getString(R.string.douban_moment)
        };
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            guokrFragment = GuokrFragment.newInstance();
            guokrPresenter = new GuokrPresenter(context, guokrFragment);
            return guokrFragment;
        } else if (position == 2){
            doubanFragment = DoubanMomentFragment.newInstance();
            doubanPresenter = new DoubanMomentPresenter(context, doubanFragment);
            return doubanFragment;
        }

        zhihuFragment = ZhihuDailyFragment.newInstance();
        zhihuPresenter = new ZhihuDailyPresenter(context, zhihuFragment);
        return zhihuFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
