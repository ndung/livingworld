package com.livingworld.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.livingworld.R;
import com.livingworld.util.SampleSlide;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intro);
        addSlide(SampleSlide.newInstance(R.layout.layout_intro_1));
        addSlide(SampleSlide.newInstance(R.layout.layout_intro_2));
        addSlide(SampleSlide.newInstance(R.layout.layout_intro_3));
        addSlide(SampleSlide.newInstance(R.layout.layout_intro_4));

        setDoneText("");
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
//        super.onDonePressed(currentFragment);
    }
}
