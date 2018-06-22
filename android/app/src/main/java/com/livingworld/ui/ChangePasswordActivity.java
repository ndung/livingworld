package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.util.ChipperUtils;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.thefinestartist.Base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.bt_next)
    Button btNext;

    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        authService = ApiUtils.AuthService(this);

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etOldPassword.getText().toString().equals("")){
                    showMessage("Old password should not be blank");
                }else if (etNewPassword.getText().toString().equals("")){
                    showMessage("New password should not be blank");
                }else if (etConfirmPassword.getText().toString().equals("")){
                    showMessage("Confirmed new password should not be blank");
                }else if (etNewPassword.getText().toString().contains(etConfirmPassword.getText().toString())){
                    showPleasewaitDialog();
                    Map<String, String> map = new HashMap<>();
                    map.put("oldPassword", etOldPassword.getText().toString());
                    map.put("newPassword", etNewPassword.getText().toString());
                    authService.changePassword(map).enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            dissmissPleasewaitDialog();
                            if (response.isSuccessful()) {
                                Response body = response.body();
                                Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                                JsonObject jsonObject = gson.toJsonTree(body.getData()).getAsJsonObject();
                                User user = gson.fromJson(jsonObject, User.class);
                                if (user != null) {
                                    Preferences.setUser(getApplicationContext(), user);
                                    finish();
                                } else {
                                    showMessage(body.getMessage());
                                }
                            }
                            else if (response.errorBody()!=null){
                                try {
                                    showMessage(response.errorBody().string());
                                }catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                            else if (response.body()!=null && response.body().getMessage()!=null){
                                showMessage(response.body().getMessage());
                            }
                            else {
                                showMessage(Static.SOMETHING_WRONG);
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            dissmissPleasewaitDialog();
                            showMessage(Static.SOMETHING_WRONG);

                        }
                    });
                }else{
                    showMessage("New password should be the same with the confirmed password");
                }
            }
        });
    }
}
