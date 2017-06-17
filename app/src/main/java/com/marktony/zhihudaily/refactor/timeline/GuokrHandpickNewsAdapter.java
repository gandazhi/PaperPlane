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
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.refactor.interfaze.OnRecyclerViewItemOnClickListener;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0x00;
    private static final int TYPE_FOOTER = 0x01;

    @NonNull
    private final Context mContext;

    @NonNull
    private final List<GuokrHandpickNewsResult> mList;
    private OnRecyclerViewItemOnClickListener mListener;

    public GuokrHandpickNewsAdapter(@NonNull List<GuokrHandpickNewsResult> list,
                                    @NonNull Context context) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_FOOTER:
                return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_footer, viewGroup, false));
            case TYPE_ITEM:
                return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_list_item_layout, viewGroup, false), mListener);
        }
        return null;
        /*return (getItemViewType(i) == TYPE_FOOTER) ? new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_list_item_layout, viewGroup, false))
                : new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_footer, viewGroup, false), mListener);*/
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemViewHolder) {
            GuokrHandpickNewsResult item = mList.get(i);
            Glide.with(mContext)
                    .load(item.getImageInfo().getUrl())
                    .asBitmap()
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.placeholder)
                    .centerCrop()
                    .into(((ItemViewHolder) viewHolder).imageView);

            ((ItemViewHolder) viewHolder).textView.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position + 1 == getItemCount()) ? TYPE_FOOTER : TYPE_ITEM;
    }

    public void setItemClickListener(OnRecyclerViewItemOnClickListener listener){
        this.mListener = listener;
    }

    public void updateData(@NonNull List<GuokrHandpickNewsResult> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
        notifyItemRemoved(list.size());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView imageView;
        TextView textView;
        OnRecyclerViewItemOnClickListener listener;

        public ItemViewHolder(View itemView, OnRecyclerViewItemOnClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view_cover);
            textView = itemView.findViewById(R.id.text_view_title);

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
