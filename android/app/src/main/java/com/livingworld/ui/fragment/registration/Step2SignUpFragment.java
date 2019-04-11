package com.livingworld.ui.fragment.registration;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.ui.TermsAndConditionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Step2SignUpFragment extends Fragment {


    @BindView(R.id.et_pass1)
    TextInputLayout etPass1;
    @BindView(R.id.et_pass2)
    TextInputLayout etPass2;
    @BindView(R.id.tv_tnc)
    TextView tvTnc;

    Unbinder unbinder;

    public TextInputLayout getEtPass1() {
        return etPass1;
    }

    public TextInputLayout getEtPass2() {
        return etPass2;
    }

    public Step2SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step2_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);
        etPass1.setError(null);
        etPass2.setError(null);
        tvTnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), TermsAndConditionsActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
