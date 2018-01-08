package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.livingworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyQRCodeActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrcode);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
