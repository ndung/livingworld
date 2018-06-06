package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.model.Response;
import com.livingworld.ui.fragment.registration.Step1SignUpFragment;
import com.livingworld.ui.fragment.registration.Step2SignUpFragment;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationActivity extends BaseActivity {

    private final String TAG = RegistrationActivity.class.toString();

    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_step)
    TextView tvStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        memberService = ApiUtils.MemberService(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setTitle("");

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Step1SignUpFragment step1 = new Step1SignUpFragment();
        final Step2SignUpFragment step2 = new Step2SignUpFragment();

        getSupportFragmentManager().beginTransaction().add(mainFrame.getId(), step1).commit();

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btNext.getText().equals("NEXT") && next(step1)) {
                    getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), step2).commit();
                    tvStep.setText("Step 2/2");
                    btNext.setText("SIGN UP");
                } else if (btNext.getText().equals("SIGN UP")) {
                    signUp(step2);
                }
            }
        });
    }

    private String bod;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String confirmPassword;

    MemberService memberService;

    private boolean next(Step1SignUpFragment step1) {
        bod = step1.getEtBod().getText().toString();
        name = step1.getEtNama().getText().toString();
        mobile = step1.getEtMobile().getText().toString();
        email = step1.getEtEmail().getText().toString();

        if (bod.isEmpty()) {
            showSnackbar("Date of birth should not be empty");
        } else if (name.isEmpty()) {
            showSnackbar("Name should not be empty");
        } else if (mobile.isEmpty()) {
            showSnackbar("Mobile phone number should not be empty");
        } else if (email.isEmpty()) {
            showSnackbar("Email should not be empty");
        } else {
            return true;
        }
        return false;
    }

    private boolean signUp(Step2SignUpFragment step2) {
        password = step2.getEtPass1().getText().toString();
        confirmPassword = step2.getEtPass2().getText().toString();

        if (password.isEmpty()) {
            showSnackbar("Password should not be empty");
        } else if (confirmPassword.isEmpty()) {
            showSnackbar("Password should be confirmed");
        } else if (!password.equalsIgnoreCase(confirmPassword)) {
            showSnackbar("Password and confirmed password should match");
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("fullName", name);
            map.put("email", email);
            map.put("mobileNumber", mobile);
            map.put("dateOfBirth", bod);
            map.put("password", password);

            showPleasewaitDialog();
            memberService.registerUser(map).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    dissmissPleasewaitDialog();
                    if (response.isSuccessful()) {
                        Response body = response.body();
                        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                        JsonObject jsonObject = gson.toJsonTree(body.getData()).getAsJsonObject();
                        User user = gson.fromJson(jsonObject, User.class);
                        if (user != null) {
                            String token = response.headers().get("Token");
                            Preferences.setUser(getApplicationContext(), user);
                            Preferences.setCardNumber(getApplicationContext(), user.getMember().getCardNumber());
                            Preferences.setToken(getApplicationContext(), token);
                            Preferences.setLoginFlag(getApplicationContext(), true);
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            IntroActivity.activity.finish();
                            finish();
                        } else {
                            showMessage(body.getMessage());
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
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
