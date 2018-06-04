package com.livingworld.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.trx.model.LuckyDraw;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ndoenks on 04/06/18.
 */

public class LuckyDrawAdapter extends RecyclerView.Adapter<LuckyDrawAdapter.ViewHolder> {
    List<LuckyDraw> list = new ArrayList<>();

    Activity context;

    public LuckyDrawAdapter(Activity context, List<LuckyDraw> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public LuckyDrawAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luckydraw, parent, false);
        return new LuckyDrawAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LuckyDrawAdapter.ViewHolder holder, int position) {
        final LuckyDraw model = list.get(position);
        holder.tvTitle.setText(model.getNo());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
