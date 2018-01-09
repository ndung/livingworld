package com.livingworld.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.ui.fragment.ecash.EcashIntroFragment;
import com.livingworld.ui.fragment.ecash.EcashRegPhoneFragment;
import com.livingworld.ui.fragment.ecash.EcashSuccessFragment;
import com.livingworld.ui.fragment.ecash.EcashVerifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ECashConnectActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecash_connect);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        fragmentList = new ArrayList<>();
        EcashIntroFragment ecashIntroFragment = new EcashIntroFragment();
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
                if (POSITION < fragmentList.size()) {
                    setFragment(false);
                }
            }
        });

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
