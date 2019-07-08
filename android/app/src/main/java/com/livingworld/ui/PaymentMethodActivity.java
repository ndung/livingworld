package com.livingworld.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class PaymentMethodActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.ll_lw_pay)
    LinearLayout llLwPay;
    @BindView(R.id.iv_img_inbox)
    ImageView ivImgInbox;

    TrxService trxService;
    private int MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        ButterKnife.bind(this);

        MODE = getIntent().getIntExtra("MODE", 0);
        trxService = ApiUtils.TrxService(getApplicationContext());
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
