package com.livingworld.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.livingworld.R;
import com.livingworld.util.Static;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GiveUsFeedbackActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.rating)
    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_us_feedback);
        ButterKnife.bind(this);

        rating.setRating(5);

    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }

}
