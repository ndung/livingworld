package com.livingworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.inbox.model.Inbox;
import com.livingworld.clients.merchant.model.Merchant;
import com.livingworld.clients.merchant.model.MerchantCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 1/8/2018.
 */

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {

    List<MerchantCategory> list = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(MerchantCategory model);
    }

    private final OnItemClickListener listener;

    public MerchantAdapter(List<MerchantCategory> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MerchantCategory model = list.get(position);
        holder.tvTitle.setText(model.getMerchantCategoryName());
//        if(position > 0){
//            holder.tvTitle.setTextColor(Color.parseColor("#333333"));
//            holder.tvTitle.setTypeface(null, Typeface.NORMAL);
//            holder.ivInbox.setImageResource(R.drawable.rectangle_copy_2);
//            holder.llNew.setVisibility(View.GONE);
//        }

        holder.ivClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(model);
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
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
