package com.livingworld.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.clients.merchant.model.Merchant;
import com.livingworld.clients.merchant.model.MerchantCategory;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 1/8/2018.
 */

public class MerchantDetAdapter extends RecyclerView.Adapter<MerchantDetAdapter.ViewHolder> {

    List<Merchant> list = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(Merchant model);
    }

    Context context;

    private final OnItemClickListener listener;

    public MerchantDetAdapter(Context context, List<Merchant> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_det_merchant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Merchant model = list.get(position);
        holder.tvTitle.setText(model.getMerchantName());
        Glide.with(context).load(Static.LW_URL+model.getMerchantLogo()).into(holder.ivImgBox);


//        if(position > 0){
//            holder.tvTitle.setTextColor(Color.parseColor("#333333"));
//            holder.tvTitle.setTypeface(null, Typeface.NORMAL);
//            holder.ivInbox.setImageResource(R.drawable.rectangle_copy_2);
//            holder.llNew.setVisibility(View.GONE);
//        }

        holder.rvItem.setVisibility(View.GONE);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(model);
//                holder.rvItem.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_img_inbox)
        ImageView ivImgBox;
        @BindView(R.id.iv_click)
        ImageView ivClick;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.rv_item)
        RecyclerView rvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
