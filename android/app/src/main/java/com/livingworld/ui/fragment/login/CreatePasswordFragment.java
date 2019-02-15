package com.livingworld.ui.fragment.login;


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
public class CreatePasswordFragment extends Fragment {

    @BindView(R.id.et_pass)
    TextInputLayout etPass;
    @BindView(R.id.layout_tnc)
    LinearLayout tvTnc;
    Unbinder unbinder;

    public CreatePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_password, container, false);
        unbinder = ButterKnife.bind(this, view);

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

    public TextInputLayout getEtPass() {
        return etPass;
    }
}
