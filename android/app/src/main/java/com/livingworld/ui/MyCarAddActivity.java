package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.ui.fragment.mycar.MyCarAddFormFragment;
import com.livingworld.ui.fragment.mycar.MyCarEnterPINFragment;
import com.livingworld.ui.fragment.registration.Step2SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCarAddActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_step)
    TextView tvStep;
    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car_add);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), new MyCarAddFormFragment()).commit();
    }

    @OnClick({R.id.iv_finish, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_next:
                getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), new MyCarEnterPINFragment()).commit();
                tvStep.setText("Step 2/2");
                break;
        }
    }
}
