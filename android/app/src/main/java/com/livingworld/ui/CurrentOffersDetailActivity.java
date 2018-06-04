package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.jaeger.library.StatusBarUtil;
import com.livingworld.R;
import com.livingworld.clients.offers.model.CurrentOffer;
import com.livingworld.clients.offers.model.CurrentOfferImage;
import com.livingworld.util.Static;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentOffersDetailActivity extends AppCompatActivity {

    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_offers_detail);
        ButterKnife.bind(this);

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CurrentOffer currentOffer = (CurrentOffer) getIntent().getSerializableExtra("currentOffer");

        tvTitle.setText(currentOffer.getTitle());
        tvDate.setText(dateFormatter.format(currentOffer.getStartDate())+" - "+dateFormatter.format(currentOffer.getEndDate()));
        tvDesc.setText(currentOffer.getDescription());

        StatusBarUtil.setTransparent(this);

        HashMap<String, String> url_maps = new HashMap<>();
        if (currentOffer.getCurrentOfferImages()!=null){
            for (CurrentOfferImage image : currentOffer.getCurrentOfferImages()) {
                url_maps.put(image.getCurrentOfferImageId(), Static.IMAGES_URL + image.getCurrentOfferImageId());
            }
        }
        //url_maps.put("Pengumuman Hari ini", "https://marketplace.canva.com/MABy2PdFF7w/1/0/thumbnail_large/canva-student-council-event-poster-MABy2PdFF7w.jpg");
        //url_maps.put("Jangan lewatkan !", "https://i.pinimg.com/736x/5d/32/0d/5d320daf07e10f03cb94c3bf2f274f13--flyer-design-event-poster-design.jpg");
        //url_maps.put("Ingat !!!", "https://i.pinimg.com/originals/d6/1c/1d/d61c1d8bb37f7225c5fa56c28ba99276.jpg");

        loadSlider(url_maps);
    }

    private void loadSlider(HashMap<String, String> url_maps) {
        for (String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(getApplicationContext());

            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            slider.addSlider(textSliderView);
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Top);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.setVisibility(View.VISIBLE);
    }
}
