package com.livingworld.ui.fragment.ecash;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livingworld.R;
import com.livingworld.adapter.ViewPagerAdapter;
import com.livingworld.ui.fragment.ecash.intro.EcashIntro1Fragment;
import com.livingworld.ui.fragment.login.CreatePasswordFragment;
import com.robohorse.pagerbullet.PagerBullet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EcashIntroFragment extends Fragment {

    @BindView(R.id.pagerBullet)
    PagerBullet pagerBullet;
    Unbinder unbinder;

    public EcashIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ecash_intro_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        pagerBullet.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addFragment(new EcashIntro1Fragment());
        viewPagerAdapter.addFragment(new EcashIntro1Fragment());
        viewPagerAdapter.addFragment(new EcashIntro1Fragment());
        viewPagerAdapter.addFragment(new EcashIntro1Fragment());

        pagerBullet.setIndicatorTintColorScheme(R.color.colorAccent, R.color.colorPrimary);
        pagerBullet.invalidateBullets();
        viewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
