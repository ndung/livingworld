package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.livingworld.R;
import com.livingworld.ui.fragment.login.CreatePasswordFragment;
import com.livingworld.ui.fragment.login.EnterPasswordFragment;
import com.livingworld.ui.fragment.login.InputCardNumberFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.bt_next)
    Button btNext;
    FragmentManager fragmentManager;
    boolean trying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        final InputCardNumberFragment inputCardNumberFragment = new InputCardNumberFragment();
        final EnterPasswordFragment enterPasswordFragment = new EnterPasswordFragment();
        final CreatePasswordFragment createPasswordFragment = new CreatePasswordFragment();
        setFragment(inputCardNumberFragment);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!trying) {
                    trying = true;
                    setFragment(createPasswordFragment);
                }else{
                    trying = false;
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(mainFrame.getId(), fragment).commit();
    }
}
