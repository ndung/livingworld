package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.livingworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BAMActivity extends BaseActivity {

    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.bt_dismiss)
    Button btDismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bam);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_next, R.id.bt_dismiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                startActivity(new Intent(getApplicationContext(), BAMRegistrationActivity.class));
                break;
            case R.id.bt_dismiss:
                finish();
                break;
        }
    }
}
