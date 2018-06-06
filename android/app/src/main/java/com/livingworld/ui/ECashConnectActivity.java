package com.livingworld.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.ViewPagerAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.ecash.ECashService;
import com.livingworld.clients.model.Response;
import com.livingworld.ui.fragment.ecash.BlankFragment;
import com.livingworld.ui.fragment.ecash.EcashRegPhoneFragment;
import com.livingworld.ui.fragment.ecash.EcashSuccessFragment;
import com.livingworld.ui.fragment.ecash.EcashVerifyFragment;
import com.livingworld.ui.fragment.ecash.ECashIntroFragment;
import com.livingworld.util.GsonDeserializer;
import com.robohorse.pagerbullet.PagerBullet;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class ECashConnectActivity extends BaseActivity {

    ECashService ecashService;

    @BindView(R.id.pagerBullet)
    PagerBullet pagerBullet;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;
    FragmentManager fragmentManager;
    int POSITION = 0;
    @BindView(R.id.tv_step)
    TextView tvStep;
    List<Fragment> fragmentList;

    private static final String TAG = ECashConnectActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecash_connect);
        ButterKnife.bind(this);

        ecashService = ApiUtils.ECashService(getApplicationContext());

        fragmentManager = getSupportFragmentManager();

        fragmentList = new ArrayList<>();
        ECashIntroFragment ecashIntroFragment = new ECashIntroFragment();
        fragmentList.add(ecashIntroFragment);
        EcashRegPhoneFragment ecashRegPhoneFragment = new EcashRegPhoneFragment();
        fragmentList.add(ecashRegPhoneFragment);
        EcashVerifyFragment ecashVerifyFragment = new EcashVerifyFragment();
        fragmentList.add(ecashVerifyFragment);
        EcashSuccessFragment ecashSuccessFragment = new EcashSuccessFragment();
        fragmentList.add(ecashSuccessFragment);

        setFragment(false);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "POSITION:"+POSITION);
                if (POSITION==1){
                    showPleasewaitDialog();
                    ecashService.ticket().enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            dissmissPleasewaitDialog();
                            if (response.isSuccessful()){
                                Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                                JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                                Log.d(TAG, "response:"+response.body().getData());
                                Log.d(TAG, "jsonObject:"+jsonObject);
                                validate("rg3ibnhnf6yo3ju5xyoeigsgo7lgrd6fivpc1ei0icjervb4");
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable throwable) {
                            dissmissPleasewaitDialog();
                        }
                    });
                }
                if (POSITION < fragmentList.size()) {
                    setFragment(false);
                }
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        pagerBullet.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addFragment(new BlankFragment());
        viewPagerAdapter.addFragment(new BlankFragment());
        viewPagerAdapter.addFragment(new BlankFragment());
        viewPagerAdapter.addFragment(new BlankFragment());

        pagerBullet.setIndicatorTintColorScheme(R.color.colorAccent, R.color.colorPrimary);
        pagerBullet.invalidateBullets();
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void validate(String ticket){
        new FinestWebView.Builder(this).show("http://188.166.220.62/h2h-register/Main/register?ticket="+ticket);
    }

    private void setFragment(boolean isBack) {
        Fragment fragment;
        if(!isBack){
            fragment = fragmentList.get(POSITION);
            POSITION = POSITION + 1;
        }else {
            POSITION = POSITION - 1;
            fragment = fragmentList.get(POSITION-1);
        }
        pagerBullet.setCurrentItem(POSITION-1);
        tvStep.setText("STEP "+POSITION+"/"+fragmentList.size());
        fragmentManager.beginTransaction().replace(mainFrame.getId(), fragment, fragment.getClass().getName()).commit();
        if(POSITION == fragmentList.size()){
            btNext.setText("OK");
        }else{
            btNext.setText("Continue");
        }

    }


    @OnClick({R.id.iv_finish, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                break;
            case R.id.bt_next:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(POSITION == 1) {
            super.onBackPressed();
        }else{
            setFragment(true);
        }
    }
}
