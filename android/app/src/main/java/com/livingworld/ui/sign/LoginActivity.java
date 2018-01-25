package com.livingworld.ui.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.ui.HomeActivity;
import com.livingworld.ui.IntroActivity;
import com.livingworld.ui.fragment.login.CreatePasswordFragment;
import com.livingworld.ui.fragment.login.EnterPasswordFragment;
import com.livingworld.ui.fragment.login.InputCardNumberFragment;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.ChipperUtils;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Header;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;
    FragmentManager fragmentManager;
    boolean trying = false;

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

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(STEP == STEP_CARD){
                    checkCard();
                } else if(STEP == INPUT_PASSWORD){
                    checkPassword();
                } else if(STEP == CREATE_PASSWORD){

                }
            }
        });
    }

    private void checkCard() {
        cardNumber = inputCardNumberFragment.getNunber();
        if(!cardNumber.isEmpty()){
            showPleasewaitDialog();
            String params = "cardNumber";
            Map<String, String> map = new HashMap<>();
            map.put(params, cardNumber);
            RequestBody body =
                    RequestBody.create(MediaType.parse("text/plain"), cardNumber);

            authService.checkCard(body).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    dissmissPleasewaitDialog();
                    if(response.isSuccessful()){
                        Response body = response.body();
                        double data = (double) body.getData();
                        if(data > 0){
                            STEP = INPUT_PASSWORD;
                            setFragment(enterPasswordFragment);
                        } else if(data == 0){
                            STEP = CREATE_PASSWORD;
                            setFragment(createPasswordFragment);
                        } else if(data < 0){
                            showMessage("Kartu tidak terdaftar");
                        }
                    } else{
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
            Toast.makeText(getApplicationContext(), "Card number "+ Static.REQUIRED, Toast.LENGTH_LONG).show();

        }
    }

    private void checkPassword() {
        String pwd = enterPasswordFragment.getPwd().toString();
        if(!cardNumber.isEmpty()){
            showPleasewaitDialog();
            final String publicKey = ChipperUtils.getPublicKey(cardNumber,pwd);
            Map<String, String> map = new HashMap<>();
            map.put("cardNumber", cardNumber);
            map.put("publicKey", publicKey);
            authService.signIn(map).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    dissmissPleasewaitDialog();
                    if(response.isSuccessful()){
                        Response body = response.body();
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                        User user = gson.fromJson(jsonObject, User.class);
                        if(user != null){
                            String token = response.headers().get("Token");
                            Preferences.setUser(getApplicationContext(), user);
                            Preferences.setCardNumber(getApplicationContext(), cardNumber);
                            Preferences.setPublicKey(getApplicationContext(), publicKey);
                            Preferences.setToken(getApplicationContext(), token);
                            Preferences.setLoginFlag(getApplicationContext(), true);
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            IntroActivity.activity.finish();
                            finish();
                        } else {
                            showMessage(body.getMessage());
                        }
                    } else{
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
            Toast.makeText(getApplicationContext(), "Card number "+ Static.REQUIRED, Toast.LENGTH_LONG).show();

        }
    }

    private void setFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(mainFrame.getId(), fragment).commit();
    }
}
