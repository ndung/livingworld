package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.model.Response;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.et_email)
    TextInputLayout etEmail;
    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        authService = ApiUtils.AuthService(getApplicationContext());

    }

    @OnClick({R.id.iv_finish, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_next:
                etEmail.setError(null);
                Map<String, String> map = new HashMap<>();
                map.put("email", etEmail.getEditText().getText().toString());
                String cardNumber = Preferences.getString(this, "cardNumber");
                map.put("number", cardNumber);
                showPleasewaitDialog();
                authService.resetPassword(map).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        dissmissPleasewaitDialog();
                        if (response.isSuccessful()) {
                            Response body = response.body();
                            double data = (double) body.getData();
                            if (data > 0) {
                                showEmailSentDialog();
                            } else if (data == 0) {
                                etEmail.setError(getResources().getString(R.string.reset_password_failed));
                            } else if (data < 0) {
                                etEmail.setError(getResources().getString(R.string.reset_password_email));
                            }
                        } else if (response.errorBody() != null) {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                                showMessage(jObjError.getString("message"));
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

                break;
        }
    }

    private void showEmailSentDialog(){
        Intent intent = new Intent(this, ResetPasswordSuccessActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
