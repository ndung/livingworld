package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.inbox.model.Inbox;
import com.livingworld.util.Static;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HowToRedeemActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_redeem);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
