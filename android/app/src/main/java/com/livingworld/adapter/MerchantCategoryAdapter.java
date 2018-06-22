package com.livingworld.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
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

public class MerchantCategoryAdapter extends RecyclerView.Adapter<MerchantCategoryAdapter.ViewHolder> {

    private static final String TAG = MerchantCategoryAdapter.class.toString();

    List<MerchantCategory> list = new ArrayList<>();

    Activity context;

    public MerchantCategoryAdapter(Activity context, List<MerchantCategory> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchant_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MerchantCategory model = list.get(position);
        holder.tvTitle.setText(model.getMerchantCategoryName());
        holder.ivClick.setVisibility(View.VISIBLE);

        MerchantAdapter adapter = new MerchantAdapter(context, model.getMerchantList(), new MerchantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Merchant m) {
                Intent intent = new Intent(context, MerchantDetailActivity.class);
                intent.putExtra("merchant", m);
                context.startActivity(intent);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvItem.setLayoutManager(layoutManager);
        holder.rvItem.setAdapter(adapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.rvItem.getVisibility()== View.VISIBLE){
                    holder.ivClick.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_chevron_grey));
                    holder.rvItem.setVisibility(View.GONE);
                }else {
                    holder.ivClick.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_chevron));
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
