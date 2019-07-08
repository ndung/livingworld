package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.RedeemCodeAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.rewards.RewardsService;
import com.livingworld.clients.rewards.model.Redeem;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ndoenks on 02/06/18.
 */

public class RedeemedRewardActivity extends BaseActivity {

    private static final String TAG = RedeemedRewardActivity.class.toString();

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.rv_item)
    RecyclerView recyclerView;

    RewardsService rewardsService;
    RedeemCodeAdapter adapter;

    List<Redeem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeemed_reward);
        ButterKnife.bind(this);

        adapter = new RedeemCodeAdapter(this, list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        showPleasewaitDialog();
        rewardsService = ApiUtils.RewardService(getApplicationContext());
        rewardsService.getRedeemedReward().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<Redeem> data = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Redeem>>() {}.getType());

                    for (Redeem obj : data){
                        list.add(obj);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dissmissPleasewaitDialog();
                showMessage(Static.SOMETHING_WRONG);
            }
        });
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
