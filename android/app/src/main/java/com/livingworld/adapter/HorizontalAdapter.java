package com.livingworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.livingworld.R;
import com.livingworld.clients.offers.model.CurrentOffer;
import com.livingworld.clients.offers.model.CurrentOfferImage;
import com.livingworld.util.Static;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 12/21/2017.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(CurrentOffer position);
    }

    private final OnItemClickListener listener;
    private List<CurrentOffer> list;
    private Context context;

    public HorizontalAdapter(Context context, List<CurrentOffer> list, OnItemClickListener listener) {
        this.listener = listener;
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CurrentOffer model = list.get(position);
        List<CurrentOfferImage> images = model.getCurrentOfferImages();
        if (images!=null) {
            Glide.with(context)
                    .load(Static.IMAGES_URL+images.get(0).getCurrentOfferImageId()).into(holder.imageView);
        }else{
            Glide.with(context)
                    .load(Static.NO_IMAGE_URL).into(holder.imageView);
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

        @BindView(R.id.iv_image)
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
