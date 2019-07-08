package com.livingworld.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.clients.rewards.model.RedeemedReward;
import com.livingworld.clients.rewards.model.Reward;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedeemedRewardsAdapter extends RecyclerView.Adapter<RedeemedRewardsAdapter.ViewHolder> {

    private static final String TAG = RedeemAdapter.class.toString();
    private List<RedeemedReward> list;
    private Context context;

    public RedeemedRewardsAdapter(Context context, List<RedeemedReward> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RedeemedRewardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeemed_reward, parent, false);
        return new RedeemedRewardsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RedeemedRewardsAdapter.ViewHolder holder, int position) {
        final RedeemedReward model = list.get(position);
        if (model.getRewardId().getMerchant()!=null) {
            holder.tvMerchant.setText(model.getRewardId().getMerchant().getMerchantName());
        }
        holder.tvReward.setText(model.getRewardId().getRewardName());
        holder.tvQuantity.setText("x"+model.getQuantity());
        holder.tvPoint.setText(model.getRewardPoint()+"pts");
        if (model.getStatus()!=0){
            holder.tvMerchant.setPaintFlags(holder.tvMerchant.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvReward.setPaintFlags(holder.tvReward.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvQuantity.setPaintFlags(holder.tvQuantity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvPoint.setPaintFlags(holder.tvPoint.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (model.getRewardId().getRewardImage()!=null) {
            Glide.with(context)
                    .load(Static.LW_URL+model.getRewardId().getRewardImage()).into(holder.imageView);
        }else{
            Glide.with(context)
                    .load("http://103.27.207.124/~ifabula/demo/img/seller_galery/no_image.jpg").into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_merchant)
        TextView tvMerchant;
        @BindView(R.id.tv_reward)
        TextView tvReward;
        @BindView(R.id.tv_quantity)
        TextView tvQuantity;
        @BindView(R.id.tv_point)
        TextView tvPoint;
        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
