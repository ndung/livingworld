package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.adapter.RedeemAdapter;
import com.livingworld.clients.rewards.model.Reward;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RewardRedeemActivity extends BaseActivity {


    private static final String TAG = RewardRedeemActivity.class.toString();

    @BindView(R.id.tv_rewardTitle)
    TextView tvTitle;
    @BindView(R.id.tv_rewardPoints)
    TextView tvPoint;
    @BindView(R.id.tv_merchantName)
    TextView tvMerchant;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.bt_redeem)
    Button btRedeem;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_merchant)
    ImageView ivMerchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_reward);
        ButterKnife.bind(this);

        final Reward reward = (Reward) getIntent().getSerializableExtra("reward");
        tvTitle.setText(reward.getRewardName());
        tvPoint.setText(String.valueOf(reward.getRewardPoint()));
        tvDesc.setText(reward.getRewardDescription());
        if (reward.getMerchant()!=null) {
            tvMerchant.setText(reward.getMerchant().getMerchantName());
        }
        if (reward.getRewardImage()!=null) {
            Glide.with(this)
                    .load(Static.LW_URL+reward.getRewardImage()).into(ivMerchant);
        }else{
            Glide.with(this)
                    .load("http://103.27.207.124/~ifabula/demo/img/seller_galery/no_image.jpg").into(ivMerchant);
        }
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Reward, String> map = Preferences.getRedeems(getApplicationContext());

                if (map == null) {
                    map = new HashMap<Reward, String>();
                }
                if (!map.containsKey(reward)) {
                    map.put(reward, "1");
                    Preferences.setRedeems(getApplicationContext(), map);
                }
                startActivity(new Intent(getApplicationContext(), RedeemActivity.class));
            }
        });
    }

}
