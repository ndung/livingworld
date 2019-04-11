package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.livingworld.R;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutLiveCardActivity extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener{

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.tv_how_to_apply)
    TextView tvHowToApply;
    @BindView(R.id.tv_name_card)
    TextView tvNameCard;
    @BindView(R.id.tv_privilege)
    TextView tvPrivilege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_live_card);
        ButterKnife.bind(this);

        int position = getIntent().getIntExtra("card", 0);

        Map<String, Integer> url_maps = new LinkedHashMap<>();
        url_maps.put("Green Live Card", R.drawable.green);
        url_maps.put("Blue Live Card", R.drawable.blue);
        url_maps.put("Black Live Card", R.drawable.black);
        loadSlider(url_maps);

        slider.setCurrentPosition(position);
        display(position);
    }

    private void loadSlider(Map<String, Integer> url_maps) {
        for (String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(getApplicationContext());

            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            slider.addSlider(textSliderView);
        }
        slider.stopAutoCycle();
        slider.setDuration(0);
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Top);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setVisibility(View.VISIBLE);
        slider.addOnPageChangeListener(this);
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        display(position);
    }

    @Override
    public void onPageSelected(int position) {
        display(position);
    }

    private void display(int position){
        switch (position){
            case 0:
                tvNameCard.setText("GREEN LIVE CARD");
                tvHowToApply.setText(R.string.how_to_apply_green_live_cards);
                tvPrivilege.setText(R.string.green_card_privilege);
                break;
            case 1:
                tvNameCard.setText("BLUE LIVE CARD");
                tvHowToApply.setText(R.string.how_to_apply_blue_live_cards);
                tvPrivilege.setText(R.string.blue_card_privilege);
                break;
            case 2:
                tvNameCard.setText("PREMIERE LIVE CARD");
                tvHowToApply.setText(R.string.how_to_apply_black_live_cards);
                tvPrivilege.setText(R.string.black_card_privilege);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
