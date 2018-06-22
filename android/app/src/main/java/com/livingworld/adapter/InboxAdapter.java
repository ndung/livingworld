package com.livingworld.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.inbox.model.Inbox;
import com.livingworld.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 1/8/2018.
 */

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    List<Inbox> list = new ArrayList<>();

    private static final String TAG = InboxAdapter.class.toString();

    public interface OnItemClickListener {
        void onItemClick(Inbox model);
    }

    private final OnItemClickListener listener;

    Context context;

    public InboxAdapter(Context context, List<Inbox> list, OnItemClickListener listener) {
        this.context = context;
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

        boolean bool = Preferences.isMessageRead(String.valueOf(inbox.getId()), context);
        if (bool){
            holder.llNew.setVisibility(View.GONE);
            holder.ivInbox.setImageDrawable(context.getResources().getDrawable(R.drawable.rectangle_copy_2));
        }else{
            holder.llNew.setVisibility(View.VISIBLE);
            holder.ivInbox.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_message_green));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.addReadMessages(String.valueOf(inbox.getId()), context);
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
