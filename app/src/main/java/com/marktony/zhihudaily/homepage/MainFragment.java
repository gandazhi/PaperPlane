package com.marktony.zhihudaily.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.about.AboutPreferenceActivity;
import com.marktony.zhihudaily.adapter.MainPagerAdapter;
import com.marktony.zhihudaily.settings.SettingsPreferenceActivity;

/**
 * Created by Lizhaotailang on 2016/8/23.
 */

public class MainFragment extends Fragment {

    private Context context;

    private MainPagerAdapter adapter;

    private TabLayout tabLayout;

    // private OnViewPagerCreated mOnViewPagerCreated;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        /*mOnViewPagerCreated = (OnViewPagerCreated) context;*/
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        setHasOptionsMenu(true);

        // 当tab layout位置为果壳精选时，隐藏fab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                if (tab.getPosition() == 1) {
                    fab.hide();
                } else {
                    fab.show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        // mOnViewPagerCreated.viewPagerCreated();
        return view;
    }


    private void initViews(View view) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((MainActivity)context).setSupportActionBar(toolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);

        adapter = new MainPagerAdapter(getChildFragmentManager(), context);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_change_theme) {
            /*changeTheme();
            save();
            Theme.setStatusBarColor(this);*/

        } else if (id == R.id.action_settings) {
            startActivity(new Intent(getActivity(),SettingsPreferenceActivity.class));
        } else if (id == R.id.action_about) {
            startActivity(new Intent(getActivity(),AboutPreferenceActivity.class));
        }
        return true;
    }

    /*public interface OnViewPagerCreated {
        void viewPagerCreated();
    }*/

}
