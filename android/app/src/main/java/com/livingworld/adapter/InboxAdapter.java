package com.livingworld.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.inbox.model.Inbox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 1/8/2018.
 */

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    List<Inbox> list = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(Inbox model);
    }

    private final OnItemClickListener listener;

    public InboxAdapter(List<Inbox> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Inbox inbox = list.get(position);
        holder.tvTitle.setText(inbox.getTitle());
        holder.tvDate.setText(inbox.getDate());
//        if(position > 0){
//            holder.tvTitle.setTextColor(Color.parseColor("#333333"));
//            holder.tvTitle.setTypeface(null, Typeface.NORMAL);
//            holder.ivInbox.setImageResource(R.drawable.rectangle_copy_2);
//            holder.llNew.setVisibility(View.GONE);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(inbox);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_click)
        ImageView ivClick;
        @BindView(R.id.iv_img_inbox)
        ImageView ivInbox;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.ll_new)
        LinearLayout llNew;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
