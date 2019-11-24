package com.livingworld.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.livingworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordSuccessActivity extends BaseActivity {

    @BindView(R.id.bt_submit)
    Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reset_password);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
