package com.livingworld.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.adapter.RedeemAdapter;
import com.livingworld.adapter.RedeemsAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.rewards.RewardsService;
import com.livingworld.clients.rewards.model.Redeem;
import com.livingworld.clients.rewards.model.Reward;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class RedeemActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_totalPoints)
    TextView tvTotalPoints;

    Activity activity;
    RewardsService rewardsService;
    RedeemsAdapter adapter;

    private static final String TAG = RedeemActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_rewards);
        ButterKnife.bind(this);
        activity = this;
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rewardsService = ApiUtils.RewardService(getApplicationContext());

        final List<Reward> rewardList = new ArrayList<>();
        final Map<Reward, String> map = Preferences.getRedeems(this);
        totalPoints(map);
        rewardList.addAll(map.keySet());

        RedeemAdapter redeemAdapter = new RedeemAdapter(this, rewardList, new RedeemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Reward model, int x) {
                Map<Reward, String> map = Preferences.getRedeems(activity);
                if (map == null) {
                    map = new HashMap<Reward, String>();
                }
                if (x==0){
                    map.remove(model);
                }else {
                    map.put(model, String.valueOf(x));
                }
                totalPoints(map);
                Preferences.setRedeems(activity, map);
            }
        });
        adapter = new RedeemsAdapter(this, rewardList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(redeemAdapter);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redeem(map);
            }
        });
    }

    private void totalPoints(Map<Reward, String> map){
        int pts = 0;
        for (Reward reward : map.keySet()){
            pts = pts + (Integer.parseInt(reward.getRewardPoint()))*Integer.parseInt(map.get(reward));
        }
        tvTotalPoints.setText(String.valueOf(pts));
    }

    private void redeem(Map<Reward, String>  rewardList){
        Map<String, String> map = new HashMap<>();
        Map<String, String> rewards = new LinkedHashMap<>();
        for (Reward reward : rewardList.keySet()){
            rewards.put(String.valueOf(reward.getRewardId()), rewardList.get(reward));
        }
        map.put("rewards", new Gson().toJson(rewards));

        showPleasewaitDialog();
        rewardsService.redeem(map).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if (response.isSuccessful()) {
                    Response body = response.body();
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(body.getData()).getAsJsonObject();
                    Redeem redeem = gson.fromJson(jsonObject, Redeem.class);

                    final Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.dialog_redeem);
                    TextView code = dialog.findViewById(R.id.tv_redeem_code);
                    code.setText(redeem.getCode());
                    TextView tv = dialog.findViewById(R.id.tv_view);

                    Button btSubmit = dialog.findViewById(R.id.bt_submit);
                    btSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Dialog dialog2 = new Dialog(activity);
                            dialog2.setContentView(R.layout.dialog_redeems);
                            RecyclerView rv = dialog2.findViewById(R.id.rv_item);
                            final LinearLayoutManager llManager = new LinearLayoutManager(getApplicationContext());
                            rv.setLayoutManager(llManager);
                            rv.setAdapter(adapter);

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
                    dialog.show();
                } else {
                    showMessage(Static.SOMETHING_WRONG);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dissmissPleasewaitDialog();
                showMessage(Static.SOMETHING_WRONG);
            }
        });
    }

}
