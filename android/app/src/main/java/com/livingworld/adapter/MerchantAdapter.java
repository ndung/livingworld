package com.livingworld.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import com.livingworld.clients.merchant.model.Merchant;
import com.livingworld.clients.merchant.model.MerchantCategory;
import com.livingworld.ui.MerchantDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 1/8/2018.
 */

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {

    private static final String TAG = MerchantAdapter.class.toString();

    List<MerchantCategory> list = new ArrayList<>();

    Activity context;

    public MerchantAdapter(Activity context, List<MerchantCategory> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MerchantCategory model = list.get(position);
        holder.tvTitle.setText(model.getMerchantCategoryName());
//        if(position > 0){
//            holder.tvTitle.setTextColor(Color.parseColor("#333333"));
//            holder.tvTitle.setTypeface(null, Typeface.NORMAL);
//            holder.ivInbox.setImageResource(R.drawable.rectangle_copy_2);
//            holder.llNew.setVisibility(View.GONE);
//        }

        MerchantDetAdapter inboxAdapter = new MerchantDetAdapter(context, model.getMerchantList(), new MerchantDetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Merchant m) {
                Intent intent = new Intent(context, MerchantDetailActivity.class);
                intent.putExtra("merchant", m);
                context.startActivity(intent);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvItem.setLayoutManager(layoutManager);
        holder.rvItem.setAdapter(inboxAdapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.rvItem.getVisibility()== View.VISIBLE){
                    holder.rvItem.setVisibility(View.GONE);
                }else {
                    holder.rvItem.setVisibility(View.VISIBLE);
                }
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

        @BindView(R.id.rv_item)
        RecyclerView rvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
