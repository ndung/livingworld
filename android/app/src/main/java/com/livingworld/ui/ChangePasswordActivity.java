package com.livingworld.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.livingworld.util.StringUtils;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.et_old_password)
    TextInputLayout etOldPassword;
    @BindView(R.id.et_new_password)
    TextInputLayout etNewPassword;
    @BindView(R.id.et_confirm_password)
    TextInputLayout etConfirmPassword;
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
                boolean bool = true;
                if (etOldPassword.getEditText().getText().toString().equals("")){
                    etOldPassword.setError("Old password should not be empty");
                    bool = false;
                }
                if (etNewPassword.getEditText().getText().toString().equals("")){
                    etNewPassword.setError("New password should not be empty");
                    bool = false;
                }else if (!StringUtils.isPasswordValid(etNewPassword.getEditText().getText().toString(), true, true, 6, 20)) {
                    etNewPassword.setError("New password should be valid");
                    bool = false;
                }
                if (etConfirmPassword.getEditText().getText().toString().equals("")){
                    etConfirmPassword.setError("Confirmed new password should not be empty");
                    bool = false;
                }else if (!etNewPassword.getEditText().getText().toString().equals(etConfirmPassword.getEditText().getText().toString())) {
                    etConfirmPassword.setError("New password should be the same with the confirmed password");
                    bool = false;
                }
                if (bool){
                    showPleasewaitDialog();
                    Map<String, String> map = new HashMap<>();
                    map.put("oldPassword", etOldPassword.getEditText().getText().toString());
                    map.put("newPassword", etNewPassword.getEditText().getText().toString());
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
                                    showSnackbar(body.getMessage());
                                }
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                                    etOldPassword.setError(jObjError.getString("message"));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                showSnackbar(Static.SOMETHING_WRONG);
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            dissmissPleasewaitDialog();
                            showSnackbar(Static.SOMETHING_WRONG);

                        }
                    });
                }
            }
        });
    }
}
