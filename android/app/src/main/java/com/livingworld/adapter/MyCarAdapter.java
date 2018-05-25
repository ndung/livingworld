package com.livingworld.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.model.Inbox;
import com.livingworld.model.MyCar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 1/8/2018.
 */

public class MyCarAdapter extends RecyclerView.Adapter<MyCarAdapter.ViewHolder> {

    List<MyCar> list = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(MyCar model);
    }

    public interface OnAddClickListener {
        void onAddClick();
    }

    private final OnItemClickListener listener;
    private final OnAddClickListener listenerAdd;

    public MyCarAdapter(List<MyCar> list, OnItemClickListener listener, OnAddClickListener listenerAdd) {
        this.list = list;
        this.listener = listener;
        this.listenerAdd = listenerAdd;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyCar model = list.get(position);

        holder.tvTitle.setText(model.getName());
        holder.tvPla.setText(model.getColor());


        if(position == list.size() - 1){
            holder.llAdd.setVisibility(View.VISIBLE);
            holder.llAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerAdd.onAddClick();
                }
            });
        }
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
        @BindView(R.id.tv_plat)
        TextView tvPla;
        @BindView(R.id.ll_add)
        LinearLayout llAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
