package com.livingworld.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class PinActivity extends BaseActivity {

    @BindView(R.id.pin_lock_view)
    PinLockView mPinLockView;
    @BindView(R.id.indicator_dots)
    IndicatorDots mIndicatorDots;

    TrxService trxService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        ButterKnife.bind(this);
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        mPinLockView.setPinLockListener(mPinLockListener);

        mPinLockView.setPinLength(6);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);

        trxService = ApiUtils.TrxService(getApplicationContext());
    }

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            createTransaction();
        }

        @Override
        public void onEmpty() {

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };

    SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void createTransaction(){
        Map<String,String> map = new HashMap<>();
        User user = Preferences.getUser(getApplicationContext());
        map.put("tid", user.getUserId());
        map.put("cardId",user.getMember().getMemberType());
        map.put("cardNumber",user.getMember().getCardNumber());
        map.put("merchant","0DA7E526-4175-4C55-B30C-87AF6A48E19D");
        map.put("receiptNo", UUID.randomUUID().toString());
        map.put("amount","100000");
        map.put("sourceOfFund","01");
        map.put("description","Api Posting Transaction");
        map.put("memberType","01");
        map.put("time",timeFormatter.format(new Date()));
        trxService.create(map).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    Response body = response.body();
                    boolean success = (boolean) body.getData();
                    if(success){
                        final Dialog dialog = new Dialog(PinActivity.this);
                        dialog.setContentView(R.layout.dialog_custom);
                        dialog.show();
                    }
                }else{
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
