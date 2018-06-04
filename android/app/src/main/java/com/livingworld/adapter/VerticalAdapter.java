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
import com.livingworld.clients.offers.model.CurrentOffer;
import com.livingworld.clients.offers.model.CurrentOfferImage;
import com.livingworld.util.Static;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ndoenks on 04/06/18.
 */

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(CurrentOffer position);
    }

    private final VerticalAdapter.OnItemClickListener listener;
    private List<CurrentOffer> list;
    private Context context;

    public VerticalAdapter(Context context, List<CurrentOffer> list, VerticalAdapter.OnItemClickListener listener) {
        this.listener = listener;
        this.list = list;
        this.context = context;
    }

    @Override
    public VerticalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_offer, parent, false);
        return new VerticalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VerticalAdapter.ViewHolder holder, final int position) {
        final CurrentOffer model = list.get(position);
        List<CurrentOfferImage> images = model.getCurrentOfferImages();
        if (images!=null) {
            Glide.with(context)
                    .load(Static.IMAGES_URL+images.get(0).getCurrentOfferImageId()).into(holder.imageView);
        }else{
            Glide.with(context)
                    .load("http://103.27.207.124/~ifabula/demo/img/seller_galery/no_image.jpg").into(holder.imageView);
        }
        holder.tvTitle.setText(model.getTitle());
        holder.tvDesc.setText(model.getShortDescription());
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

        @BindView(R.id.iv_offer)
        ImageView imageView;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
