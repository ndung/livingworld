package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.ui.fragment.registration.Step1SignUpFragment;
import com.livingworld.ui.fragment.registration.Step2SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.tv_step)
    TextView tvStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setTitle("");

        getSupportFragmentManager().beginTransaction().add(mainFrame.getId(), new Step1SignUpFragment()).commit();

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), new Step2SignUpFragment()).commit();
                tvStep.setText("Step 2/2");
                btNext.setText("SIGN UP");
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
