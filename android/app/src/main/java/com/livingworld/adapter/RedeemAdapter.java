package com.livingworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.clients.rewards.model.Reward;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.ViewHolder> {

    private static final String TAG = RedeemAdapter.class.toString();
    private List<Reward> list;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Reward reward, int qty);
    }

    private OnItemClickListener listener;

    public RedeemAdapter(Context context, List<Reward> list, RedeemAdapter.OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RedeemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeem, parent, false);
        return new RedeemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RedeemAdapter.ViewHolder holder, int position) {
        final Reward model = list.get(position);
        if (model.getMerchant()!=null) {
            holder.tvMerchant.setText(model.getMerchant().getMerchantName());
        }
        holder.tvReward.setText(model.getRewardName());
        Map<Reward, String> map = Preferences.getRedeems(context);

        if (map == null) {
            map = new HashMap<Reward, String>();
        }
        holder.tvQuantity.setText(map.get(model));
        holder.tvPoints.setText(model.getRewardPoint()+" Points");
        if (model.getRewardImage()!=null) {
            Glide.with(context)
                    .load(Static.LW_URL+model.getRewardImage()).into(holder.imageView);
        }else{
            Glide.with(context)
                    .load("http://103.27.207.124/~ifabula/demo/img/seller_galery/no_image.jpg").into(holder.imageView);
        }

        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.tvQuantity.getText().toString())+1;
                listener.onItemClick(model, x);
                holder.tvQuantity.setText(String.valueOf(x));
            }
        });

        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.tvQuantity.getText().toString());
                if (x>0) {
                    x = x-1;
                    listener.onItemClick(model,x);
                    holder.tvQuantity.setText(String.valueOf(x));
                }
            }
        });
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
        @BindView(R.id.tv_points)
        TextView tvPoints;
        @BindView(R.id.tv_quantity)
        TextView tvQuantity;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.iv_plus)
        ImageView ivPlus;
        @BindView(R.id.iv_minus)
        ImageView ivMinus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
