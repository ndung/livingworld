package com.livingworld.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.car.CarrService;
import com.livingworld.clients.model.Response;
import com.livingworld.util.Static;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class MyCarAddActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_step)
    TextView tvStep;
    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.et_nama)
    EditText etNama;
    @BindView(R.id.et_colour)
    EditText etColour;
    @BindView(R.id.et_plat)
    EditText etPlat;
    CarrService carrService;

    boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car_add);
        ButterKnife.bind(this);

        carrService = ApiUtils.CarService(getApplicationContext());

        if(getIntent().getBooleanExtra("edit", false)){
            edit = true;
            etPlat.setText(getIntent().getStringExtra("vehicleId"));
            etNama.setText(getIntent().getStringExtra("vehicleType"));
            etColour.setText(getIntent().getStringExtra("vehicleColor"));
        }



    }

    @OnClick({R.id.iv_finish, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_next:
                Map<String, String> map = new HashMap<>();
                map.put("vehicleId", etPlat.getText().toString());
                map.put("vehicleType",etNama.getText().toString());
                map.put("vehicleColor",etColour.getText().toString());

                showPleasewaitDialog();
                if(edit){
                    carrService.editVehicle(map).enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            dissmissPleasewaitDialog();
                            if (response.isSuccessful()) {
                                finish();
                            } else if (response.errorBody() != null) {
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
                }else {
                    carrService.createVehicle(map).enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            dissmissPleasewaitDialog();
                            if (response.isSuccessful()) {
                                finish();
                            } else if (response.errorBody() != null) {
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
                tvStep.setText("Step 2/2");
                break;
        }
    }
}
