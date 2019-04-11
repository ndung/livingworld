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
import com.livingworld.util.StringUtils;

import org.json.JSONObject;

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

    boolean bool = false;

    final Step1SignUpFragment step1 = new Step1SignUpFragment();
    final Step2SignUpFragment step2 = new Step2SignUpFragment();

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
                onBackPressed();
            }
        });

        getSupportFragmentManager().beginTransaction().add(mainFrame.getId(), step1).commit();

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btNext.getText().equals("NEXT") && next(step1)) {

                    getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), step2).commit();
                    tvStep.setText("Step 2/2");
                    btNext.setText("SIGN UP");
                    bool = true;
                } else if (btNext.getText().equals("SIGN UP")) {
                    signUp(step2);
                    bool = false;
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
        bod = step1.getEtBod().getEditText().getText().toString();
        name = step1.getEtNama().getEditText().getText().toString();
        mobile = step1.getEtMobile().getEditText().getText().toString();
        email = step1.getEtEmail().getEditText().getText().toString();
        boolean bool = true;
        if (email.isEmpty()) {
            step1.getEtEmail().setError("Email should not be empty");
            bool = false;
        } else if (!StringUtils.isEmailValid(email)){
            step1.getEtEmail().setError("Email should be valid");
            bool = false;
        } else{
            step1.getEtEmail().setError(null);
        }
        if (mobile.isEmpty()) {
            step1.getEtMobile().setError("Mobile phone number should not be empty");
            bool = false;
        } else if (mobile.length()<10 || !StringUtils.isMobilePhoneNumberValid(mobile)){
            step1.getEtMobile().setError("Mobile phone number should be valid");
            bool = false;
        } else{
            step1.getEtMobile().setError(null);
        }
        if (bod.isEmpty()) {
            step1.getEtBod().setError("Date of birth should not be empty");
            bool = false;
        } else{
            step1.getEtBod().setError(null);
        }
        if (name.isEmpty()) {
            step1.getEtNama().setError("Name should not be empty");
            bool = false;
        }else{
            step1.getEtNama().setError(null);
        }
        return bool;
    }


    private boolean signUp(Step2SignUpFragment step2) {
        password = step2.getEtPass1().getEditText().getText().toString();
        confirmPassword = step2.getEtPass2().getEditText().getText().toString();
        boolean bool = true;
        if (password.isEmpty()) {
            step2.getEtPass1().setError("Password should not be empty");
            bool = false;
        }else if (!StringUtils.isPasswordValid(password, true, true, 6, 20)) {
            step2.getEtPass1().setError("Password should be valid");
            bool = false;
        }else{
            step2.getEtPass1().setError(null);
        }
        if (confirmPassword.isEmpty()) {
            step2.getEtPass2().setError("Password should be confirmed");
            bool = false;
        } else if (!password.equalsIgnoreCase(confirmPassword)) {
            step2.getEtPass2().setError("Password and confirmed password should match");
            bool = false;
        } else{
            step2.getEtPass2().setError(null);
        }
        if (bool){
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
                            Preferences.setToken(getApplicationContext(), token);
                            Preferences.setLoginFlag(getApplicationContext(), true);
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            WelcomeActivity.activity.finish();
                            finish();
                        } else {
                            showSnackbar(body.getMessage());
                        }
                    } else if (response.errorBody() != null) {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                            showSnackbar(jObjError.getString("message"));
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
        return false;
    }


    @Override
    public void onBackPressed() {
        if (btNext.getText().equals("SIGN UP")) {
            Bundle args = new Bundle();
            args.putString("bod", bod);
            args.putString("name", name);
            args.putString("mobile", mobile);
            args.putString("email", email);
            step1.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), step1).commit();
            tvStep.setText("Step 1/2");
            btNext.setText("NEXT");
        } else if (btNext.getText().equals("NEXT")) {
            finish();
        }


    }
}
