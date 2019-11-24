package com.livingworld.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.clients.trx.model.TrxResponse;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class ConfirmationBillActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.bt_next)
    Button btNext;
    TrxService trxService;

    private static final String TAG = ConfirmationBillActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_bill);
        ButterKnife.bind(this);
        trxService = ApiUtils.TrxService(getApplicationContext());
    }

    @OnClick({R.id.iv_finish, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_next:
                Intent intent = new Intent(getApplicationContext(), PinActivity.class);
                intent.putExtra("MODE", 0);
                startActivity(intent);
                //createTransaction();
                break;
        }
    }


    SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void createTransaction(){
        Map<String,String> map = new HashMap<>();
        User user = Preferences.getUser(getApplicationContext());
        map.put("tid", user.getUserId());
        map.put("cardId",user.getMember().getMemberType());
        map.put("cardNumber",user.getMember().getCardNumber());
        map.put("merchant","0DA7E526-4175-4C55-B30C-87AF6A48E19D");
        map.put("receiptNo", UUID.randomUUID().toString());
        map.put("amount","1000000");
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
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(body.getData()).getAsJsonObject();
                    TrxResponse trxResponse = gson.fromJson(jsonObject, TrxResponse.class);

                    //Response body = response.body();
                    //boolean success = (boolean) body.getData();
                    //if(success){
                        final Dialog dialog = new Dialog(ConfirmationBillActivity.this);
                        dialog.setContentView(R.layout.dialog_custom);

                    TextView tvPoint = dialog.findViewById(R.id.tv_point);
                    tvPoint.setText(String.valueOf(trxResponse.getPoints())+"pts");
                    if (trxResponse.getLuckyDraws().length>0) {
                        TextView tvLuckyDraw = dialog.findViewById(R.id.tv_lucky_draw);
                        tvLuckyDraw.setText(Arrays.asList(trxResponse.getLuckyDraws()).toString());
                    }
                    TextView tvTrxDate = dialog.findViewById(R.id.tv_trx_date);
                    tvTrxDate.setText("Transaction Date: "+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                    Button btOk = dialog.findViewById(R.id.bt_ok);
                    btOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                        dialog.show();
                    ///}
                }else if (response.errorBody() != null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                        showMessage(jObjError.getString("message"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
