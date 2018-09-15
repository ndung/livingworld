package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.livingworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentMethodActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.ll_lw_pay)
    LinearLayout llLwPay;
    @BindView(R.id.iv_img_inbox)
    ImageView ivImgInbox;

    private int MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        ButterKnife.bind(this);

        MODE = getIntent().getIntExtra("MODE", 0);
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.ll_lw_pay, R.id.iv_img_inbox, R.id.ll_add_payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_lw_pay:
                startConfirmationActivity();
                break;
            case R.id.iv_img_inbox:
                startConfirmationActivity();
                break;
            case R.id.ll_add_payment:
                startActivity(new Intent(getApplicationContext(), EcashConnectActivity.class));
                break;
        }
    }

    private void startConfirmationActivity(){
        if (MODE==0){
            startActivity(new Intent(getApplicationContext(), ConfirmationBillActivity.class));
        }else{
            startActivity(new Intent(getApplicationContext(), ParkingPaymentActivity.class));
        }
    }
}
