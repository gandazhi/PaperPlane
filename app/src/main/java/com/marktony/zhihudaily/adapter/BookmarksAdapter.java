package com.marktony.zhihudaily.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.marktony.zhihudaily.bean.DoubanMomentNews;
import com.marktony.zhihudaily.bean.GuokrHandpickNews;
import com.marktony.zhihudaily.bean.ZhihuDailyNews;
import com.marktony.zhihudaily.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaotailang on 2016/12/22.
 */

public class BookmarksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<DoubanMomentNews.posts> doubanList;
    private List<GuokrHandpickNews.result> guokrList;
    private List<ZhihuDailyNews.Question> zhihuList;

    private OnRecyclerViewOnClickListener listener;

    public BookmarksAdapter(@NonNull Context context,
                            ArrayList<ZhihuDailyNews.Question> zhihuList,
                            ArrayList<GuokrHandpickNews.result> guokrList,
                            ArrayList<DoubanMomentNews.posts> doubanList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.zhihuList = zhihuList;
        this.guokrList = guokrList;
        this.doubanList = doubanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return zhihuList.size() + guokrList.size() + doubanList.size();
    }
}
