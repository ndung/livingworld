package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.clients.merchant.model.Merchant;
import com.livingworld.clients.merchant.model.MerchantOfficeHour;
import com.livingworld.util.Static;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MerchantDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_merchant)
    ImageView ivMerchant;
    @BindView(R.id.tv_merchant)
    TextView tvMerchant;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.ll_monday)
    LinearLayout llMonday;
    @BindView(R.id.ll_tuesday)
    LinearLayout llTuesday;
    @BindView(R.id.ll_wednesday)
    LinearLayout llWednesday;
    @BindView(R.id.ll_thursday)
    LinearLayout llThursday;
    @BindView(R.id.ll_friday)
    LinearLayout llFriday;
    @BindView(R.id.ll_saturday)
    LinearLayout llSaturday;
    @BindView(R.id.ll_sunday)
    LinearLayout llSunday;

    @BindView(R.id.tv_monday)
    TextView tvMonday;
    @BindView(R.id.tv_tuesday)
    TextView tvTuesday;
    @BindView(R.id.tv_wednesday)
    TextView tvWednesday;
    @BindView(R.id.tv_thursday)
    TextView tvThursday;
    @BindView(R.id.tv_friday)
    TextView tvFriday;
    @BindView(R.id.tv_saturday)
    TextView tvSaturday;
    @BindView(R.id.tv_sunday)
    TextView tvSunday;

    @BindView(R.id.label_monday)
    TextView labelMonday;
    @BindView(R.id.label_tuesday)
    TextView labelTuesday;
    @BindView(R.id.label_wednesday)
    TextView labelWednesday;
    @BindView(R.id.label_thursday)
    TextView labelThursday;
    @BindView(R.id.label_friday)
    TextView labelFriday;
    @BindView(R.id.label_saturday)
    TextView labelSaturday;
    @BindView(R.id.label_sunday)
    TextView labelSunday;


    @BindView(R.id.line_monday)
    LinearLayout lineMonday;
    @BindView(R.id.line_tuesday)
    LinearLayout lineTuesday;
    @BindView(R.id.line_wednesday)
    LinearLayout lineWednesday;
    @BindView(R.id.line_thursday)
    LinearLayout lineThursday;
    @BindView(R.id.line_friday)
    LinearLayout lineFriday;
    @BindView(R.id.line_saturday)
    LinearLayout lineSaturday;
    @BindView(R.id.line_sunday)
    LinearLayout lineSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_detail);
        ButterKnife.bind(this);

        Merchant merchant = (Merchant) getIntent().getSerializableExtra("merchant");
        tvMerchant.setText(merchant.getMerchantName());
        tvPhone.setText(merchant.getMerchantPhone());
        tvDescription.setText(merchant.getDescription());
        if (merchant.getMerchantImage()!=null) {
            Glide.with(getApplicationContext()).load(Static.LW_URL + merchant.getMerchantImage()).into(ivMerchant);
        }else{
            Glide.with(getApplicationContext()).load(Static.NO_IMAGE_URL).into(ivMerchant);
        }

        int day = new Date().getDay();
        if (day==0){
            lineSaturday.setVisibility(View.GONE);
            lineSunday.setVisibility(View.GONE);
            tvSunday.setTextColor(getResources().getColor(R.color.white));
            labelSunday.setTextColor(getResources().getColor(R.color.white));
            llSunday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }else if (day==1){
            lineMonday.setVisibility(View.GONE);
            tvMonday.setTextColor(getResources().getColor(R.color.white));
            labelMonday.setTextColor(getResources().getColor(R.color.white));
            llMonday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }else if (day==2){
            lineMonday.setVisibility(View.GONE);
            lineTuesday.setVisibility(View.GONE);
            tvTuesday.setTextColor(getResources().getColor(R.color.white));
            labelTuesday.setTextColor(getResources().getColor(R.color.white));
            llTuesday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }else if (day==3){
            lineTuesday.setVisibility(View.GONE);
            lineWednesday.setVisibility(View.GONE);
            tvWednesday.setTextColor(getResources().getColor(R.color.white));
            labelWednesday.setTextColor(getResources().getColor(R.color.white));
            llWednesday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }else if (day==4){
            lineWednesday.setVisibility(View.GONE);
            lineThursday.setVisibility(View.GONE);
            tvThursday.setTextColor(getResources().getColor(R.color.white));
            labelThursday.setTextColor(getResources().getColor(R.color.white));
            llThursday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }else if (day==5){
            lineThursday.setVisibility(View.GONE);
            lineFriday.setVisibility(View.GONE);
            tvFriday.setTextColor(getResources().getColor(R.color.white));
            labelFriday.setTextColor(getResources().getColor(R.color.white));
            llFriday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }else if (day==6){
            lineFriday.setVisibility(View.GONE);
            lineSaturday.setVisibility(View.GONE);
            tvSaturday.setTextColor(getResources().getColor(R.color.white));
            labelSaturday.setTextColor(getResources().getColor(R.color.white));
            llSaturday.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

        List<MerchantOfficeHour> list = merchant.getMerchantOfficeHourList();
        tvSunday.setText(list.get(0).getStartTime()+"-"+list.get(0).getEndTime());
        tvMonday.setText(list.get(1).getStartTime()+"-"+list.get(1).getEndTime());
        tvTuesday.setText(list.get(2).getStartTime()+"-"+list.get(2).getEndTime());
        tvWednesday.setText(list.get(3).getStartTime()+"-"+list.get(3).getEndTime());
        tvThursday.setText(list.get(4).getStartTime()+"-"+list.get(4).getEndTime());
        tvFriday.setText(list.get(5).getStartTime()+"-"+list.get(5).getEndTime());
        tvSaturday.setText(list.get(6).getStartTime()+"-"+list.get(6).getEndTime());
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
