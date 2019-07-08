package com.livingworld.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.rewards.model.Redeem;
import com.livingworld.clients.rewards.model.RedeemedReward;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ndoenks on 04/06/18.
 */

public class RedeemCodeAdapter extends RecyclerView.Adapter<RedeemCodeAdapter.ViewHolder> {
    List<Redeem> list = new ArrayList<>();
    RedeemsAdapter adapter;
    Activity context;

    public RedeemCodeAdapter(Activity context, List<Redeem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RedeemCodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeem_code, parent, false);
        return new RedeemCodeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RedeemCodeAdapter.ViewHolder holder, int position) {
        final Redeem model = list.get(position);
        holder.tvRedeemCode.setText(model.getCode());
        holder.tvDate.setText("Expired date: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(model.getExpiredDate()));
        boolean isRedeemed = false;
        for (RedeemedReward reward : model.getRedeemedRewards()){
            if (reward.getStatus()==0){
                isRedeemed = false;
                break;
            }
            isRedeemed = true;
        }
        if (isRedeemed){
            holder.tvRedeemCode.setPaintFlags(holder.tvRedeemCode.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvDate.setPaintFlags(holder.tvDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog2 = new Dialog(context);
                dialog2.setContentView(R.layout.dialog_redeemed_rewards);
                TextView tv = dialog2.findViewById(R.id.tv_redeem_code);
                tv.setText(model.getCode());
                RecyclerView rv = dialog2.findViewById(R.id.rv_item);
                final LinearLayoutManager llManager = new LinearLayoutManager(context);
                rv.setLayoutManager(llManager);
                rv.setAdapter(new RedeemedRewardsAdapter(context, model.getRedeemedRewards()));

                Button btOk = dialog2.findViewById(R.id.bt_ok);
                btOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_redeem_code)
        TextView tvRedeemCode;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
