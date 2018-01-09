package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.livingworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class InformationAccountActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_profile)
    CircleImageView ivProfile;
    @BindView(R.id.iv_change_pwd)
    ImageView ivChangePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        ButterKnife.bind(this);

        Glide.with(this).load("https://scontent-sit4-1.cdninstagram.com/t51.2885-19/s150x150/22158665_821785821334849_4414678351050964992_n.jpg").apply(new RequestOptions().centerCrop()).into(ivProfile);
    }

    @OnClick({R.id.iv_finish, R.id.iv_profile,R.id.iv_change_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.iv_profile:

                break;
            case R.id.iv_change_pwd:
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                break;
        }
    }
}
