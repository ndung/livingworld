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
import com.livingworld.util.Static;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

    private List<Reward> list;
    private Context context;
    private final RewardsAdapter.OnItemClickListener listener;

    public RewardsAdapter(Context context, List<Reward> list, RewardsAdapter.OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Reward position);
    }

    @Override
    public RewardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rewards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RewardsAdapter.ViewHolder holder, int position) {
        final Reward model = list.get(position);
        holder.tvTitle.setText(model.getRewardName());
        holder.tvPoints.setText(model.getRewardPoint()+" Points");
        if (model.getRewardImage()!=null) {
            Glide.with(context)
                    .load(Static.IMAGES_URL+model.getRewardImage()).into(holder.imageView);
        }else{
            Glide.with(context)
                    .load("http://103.27.207.124/~ifabula/demo/img/seller_galery/no_image.jpg").into(holder.imageView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_points)
        TextView tvPoints;
        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
