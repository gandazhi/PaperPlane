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
import com.marktony.zhihudaily.refactor.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.refactor.interfaze.OnRecyclerViewItemOnClickListener;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/22.
 */

public class DoubanMomentNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0x00;
    private static final int TYPE_NO_IMG = 0x01;
    private static final int TYPE_FOOTER = 0x02;

    @NonNull
    private Context mContext;

    @NonNull
    private List<DoubanMomentNewsPosts> mList;

    private OnRecyclerViewItemOnClickListener mListener;

    public DoubanMomentNewsAdapter(@NonNull List<DoubanMomentNewsPosts> list,
                                   @NonNull Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_ITEM:
                return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_item_layout, viewGroup, false), mListener);
            case TYPE_NO_IMG:
                return new NoImgViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_item_without_image, viewGroup, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_footer, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof FooterViewHolder) {
            return;
        }

        DoubanMomentNewsPosts item = mList.get(i);
        if (viewHolder instanceof ItemViewHolder) {

            Glide.with(mContext)
                    .load(item.getThumbs().get(0).getMedium().getUrl())
                    .asBitmap()
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.placeholder)
                    .centerCrop()
                    .into(((ItemViewHolder)viewHolder).thumb);

            ((ItemViewHolder) viewHolder).title.setText(item.getTitle());

        } else if (viewHolder instanceof NoImgViewHolder) {
            ((NoImgViewHolder) viewHolder).title.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size()) {
            return TYPE_FOOTER;
        }
        return mList.get(position).getThumbs().isEmpty() ? TYPE_NO_IMG : TYPE_ITEM;
    }

    public void updateData(@NonNull List<DoubanMomentNewsPosts> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
        notifyItemRemoved(list.size());
    }

    public void setItemClickListener(OnRecyclerViewItemOnClickListener listener) {
        this.mListener = listener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView thumb;
        TextView title;

        OnRecyclerViewItemOnClickListener listener;

        public ItemViewHolder(View itemView, OnRecyclerViewItemOnClickListener listener) {
            super(itemView);

            thumb = itemView.findViewById(R.id.image_view_cover);
            title = itemView.findViewById(R.id.text_view_title);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.OnItemClick(view, getLayoutPosition());
            }
        }
    }

    public class NoImgViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView title;

        OnRecyclerViewItemOnClickListener listener;

        public NoImgViewHolder(View itemView, OnRecyclerViewItemOnClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
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
