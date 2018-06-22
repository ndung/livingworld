package com.livingworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class RedeemsAdapter extends RecyclerView.Adapter<RedeemsAdapter.ViewHolder> {

    private static final String TAG = RedeemAdapter.class.toString();
    private List<Reward> list;
    private Context context;

    public RedeemsAdapter(Context context, List<Reward> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RedeemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeems, parent, false);
        return new RedeemsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RedeemsAdapter.ViewHolder holder, int position) {
        final Reward model = list.get(position);
        holder.tvMerchant.setText(model.getMerchant().getMerchantName());
        holder.tvReward.setText(model.getRewardName());
        Map<Reward, String> map = Preferences.getRedeems(context);

        if (map == null) {
            map = new HashMap<Reward, String>();
        }
        holder.tvQuantity.setText("X"+map.get(model));
        if (model.getRewardImage()!=null) {
            Glide.with(context)
                    .load(Static.IMAGES_URL+model.getRewardImage()).into(holder.imageView);
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
        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
