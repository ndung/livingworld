package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.livingworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MerchantListActivity extends AppCompatActivity {

    @BindView(R.id.ll_merchant_click)
    LinearLayout llMerchantClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll_merchant_click)
    public void onViewClicked() {
        startActivity(new Intent(getApplicationContext(), MerchantDetailActivity.class));
    }
}
