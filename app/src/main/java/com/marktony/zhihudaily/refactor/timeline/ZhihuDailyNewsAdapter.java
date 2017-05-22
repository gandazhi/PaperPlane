package com.marktony.zhihudaily.refactor.timeline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.refactor.interfaze.OnRecyclerViewItemOnClickListener;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0x00;
    private static final int TYPE_FOOTER = 0x02;

    @NonNull
    private final Context mContext;

    @NonNull
    private List<ZhihuDailyNews.Question> mList;
    private OnRecyclerViewItemOnClickListener mListener;

    public ZhihuDailyNewsAdapter(@NonNull List<ZhihuDailyNews.Question> list,
                                 @NonNull Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_list_item_layout, viewGroup, false);
            return new ItemViewHolder(view, mListener);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_footer, viewGroup, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemViewHolder) {

            ZhihuDailyNews.Question item = mList.get(i);

            if (item.getImages().get(0) == null){
                ((ItemViewHolder)viewHolder).itemImg.setImageResource(R.drawable.placeholder);
            } else {
                Glide.with(mContext)
                        .load(item.getImages().get(0))
                        .asBitmap()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((ItemViewHolder)viewHolder).itemImg);
            }
            ((ItemViewHolder)viewHolder).title.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public void setItemClickListener(OnRecyclerViewItemOnClickListener listener){
        this.mListener = listener;
    }

    public void updateData(@NonNull List<ZhihuDailyNews.Question> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
        notifyItemRemoved(list.size());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView itemImg;
        private TextView title;
        private OnRecyclerViewItemOnClickListener listener;

        public ItemViewHolder(View itemView, OnRecyclerViewItemOnClickListener listener) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.image_view_cover);
            title = itemView.findViewById(R.id.text_view_title);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.OnItemClick(view, getLayoutPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
