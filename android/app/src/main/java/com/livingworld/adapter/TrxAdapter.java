package com.livingworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.offers.model.CurrentOffer;
import com.livingworld.clients.trx.model.History;
import com.livingworld.util.IDRUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ndoenks on 04/06/18.
 */

public class TrxAdapter extends RecyclerView.Adapter<TrxAdapter.ViewHolder> {

    private List<History> list;
    private Context context;

    public TrxAdapter(Context context, List<History> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trx_history, parent, false);
        return new TrxAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final History model = list.get(position);
        holder.tvTitle.setText(model.getMerchant());
        holder.tvDate.setText(model.getTime());
        holder.tvPts.setText(model.getPoints());
        holder.tvAmount.setText("- "+ IDRUtils.toRupiah(Double.parseDouble(model.getAmount())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_pts)
        TextView tvPts;
        @BindView(R.id.tv_amount)
        TextView tvAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
