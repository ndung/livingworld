package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.ui.fragment.login.CreatePasswordFragment;
import com.livingworld.ui.fragment.login.EnterPasswordFragment;
import com.livingworld.ui.fragment.login.InputCardNumberFragment;
import com.livingworld.util.ChipperUtils;
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
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.bt_next)
    Button btNext;
    FragmentManager fragmentManager;

    private static int STEP_CARD = 1;
    private static int INPUT_PASSWORD = 2;
    private static int CREATE_PASSWORD = 3;

    private static int STEP = 1;

    AuthService authService;

    InputCardNumberFragment inputCardNumberFragment;
    EnterPasswordFragment enterPasswordFragment;
    CreatePasswordFragment createPasswordFragment;

    String cardNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        authService = ApiUtils.AuthService(this);

        fragmentManager = getSupportFragmentManager();

        inputCardNumberFragment = new InputCardNumberFragment();
        enterPasswordFragment = new EnterPasswordFragment();
        createPasswordFragment = new CreatePasswordFragment();
        setFragment(inputCardNumberFragment);

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //if (inputCardNumberFragment.getEtCardNumber().getText().toString().equalsIgnoreCase("")) {
        //    checkCard();
        //}

        STEP = STEP_CARD;
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (STEP == STEP_CARD) {
                    checkCard();
                } else if (STEP == INPUT_PASSWORD) {
                    signIn();
                } else if (STEP == CREATE_PASSWORD) {
                    signUp();
                }
            }
        });
    }

    private void checkCard() {
        cardNumber = inputCardNumberFragment.getEtCardNumber().getEditText().getText().toString();
        if (!cardNumber.isEmpty()) {
            Preferences.putString(this, "cardNumber", cardNumber);
            showPleasewaitDialog();
            String params = "cardNumber";
            Map<String, String> map = new HashMap<>();
            map.put(params, cardNumber);
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), cardNumber);

            authService.checkCard(body).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    dissmissPleasewaitDialog();
                    if (response.isSuccessful()) {
                        Response body = response.body();
                        double data = (double) body.getData();
                        if (data > 0) {
                            STEP = INPUT_PASSWORD;
                            setFragment(enterPasswordFragment);
                        } else if (data == 0) {
                            STEP = CREATE_PASSWORD;
                            setFragment(createPasswordFragment);
                        } else if (data < 0) {
                            inputCardNumberFragment.getEtCardNumber().setError("Card number / ID number (KTP) is not registered");
                        }
                    } else if (response.errorBody() != null) {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                            inputCardNumberFragment.getEtCardNumber().setError(jObjError.getString("message"));
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
                    Log.e(TAG, "error", t);
                    showSnackbar(Static.SOMETHING_WRONG);

                }
            });
        } else {
            inputCardNumberFragment.getEtCardNumber().setError("Card number should not be empty");
        }
    }

    private static final String TAG = LoginActivity.class.toString();

    private void signIn() {
        final String pwd = enterPasswordFragment.getEtPass().getEditText().getText().toString();
        if (!pwd.isEmpty()) {
            if (!cardNumber.isEmpty()) {
                showPleasewaitDialog();
                final String publicKey = ChipperUtils.getPublicKey(cardNumber, pwd);
                Map<String, String> map = new HashMap<>();
                map.put("cardNumber", cardNumber);
                map.put("publicKey", publicKey);
                map.put("deviceId", Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID));
                authService.signIn(map).enqueue(new Callback<Response>() {
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
                                Preferences.setPublicKey(getApplicationContext(), publicKey);
                                Preferences.setToken(getApplicationContext(), token);
                                Preferences.setLoginFlag(getApplicationContext(), true);
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                FirebaseMessaging.getInstance().subscribeToTopic(user.getUserId());
                                FirebaseMessaging.getInstance().subscribeToTopic("global");
                                WelcomeActivity.activity.finish();
                                finish();
                            } else {
                                showSnackbar(body.getMessage());
                            }
                        } else if (response.errorBody() != null) {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                                enterPasswordFragment.getEtPass().setError(jObjError.getString("message"));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (response.body() != null && response.body().getMessage() != null) {
                            showSnackbar(response.body().getMessage());
                        } else {
                            showSnackbar(Static.SOMETHING_WRONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        dissmissPleasewaitDialog();
                        Log.e(TAG, "error", t);
                        showSnackbar(Static.SOMETHING_WRONG);

                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Card number " + Static.REQUIRED, Toast.LENGTH_LONG).show();
            }
        } else {
            enterPasswordFragment.getEtPass().setError("Password should not be empty");
        }
    }

    private void signUp() {
        String pwd = createPasswordFragment.getEtPass().getEditText().getText().toString();
        if (!pwd.isEmpty()) {
            if (StringUtils.isPasswordValid(pwd, true, true, 6, 20)) {
                if (!cardNumber.isEmpty()) {
                    showPleasewaitDialog();
                    Map<String, String> map = new HashMap<>();
                    map.put("cardNumber", cardNumber);
                    map.put("password", pwd);
                    map.put("deviceId", Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID));
                    authService.signUp(map).enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            dissmissPleasewaitDialog();
                            if (response.isSuccessful()) {
                                Response body = response.body();
                                Gson gson = new Gson();
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
                                    createPasswordFragment.getEtPass().setError(jObjError.getString("message"));
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
                            Log.e(TAG, "error", t);
                            showSnackbar(Static.SOMETHING_WRONG);

                        }
                    });
                } else {
                    showSnackbar("Card number " + Static.REQUIRED);
                }
            } else {
                createPasswordFragment.getEtPass().setError("Password should be valid");
            }
        } else {
            createPasswordFragment.getEtPass().setError("Password should not be empty");
        }
    }

    private void setFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(mainFrame.getId(), fragment).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        STEP = STEP_CARD;
    }
}
