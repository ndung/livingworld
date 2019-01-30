package com.livingworld.ui.fragment.registration;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.livingworld.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Step1SignUpFragment extends Fragment {


    @BindView(R.id.et_nama)
    TextInputLayout etNama;
    @BindView(R.id.et_bod)
    TextInputLayout etBod;
    @BindView(R.id.et_mobile)
    TextInputLayout etMobile;
    @BindView(R.id.et_email)
    TextInputLayout etEmail;

    public TextInputLayout getEtNama() {
        return etNama;
    }

    public TextInputLayout getEtBod() {
        return etBod;
    }

    public TextInputLayout getEtMobile() {
        return etMobile;
    }

    public TextInputLayout getEtEmail() {
        return etEmail;
    }

    Unbinder unbinder;

    public Step1SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step1_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTglLahir();
    }

    private void initTglLahir() {
        etBod.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                etBod.getEditText().setText(year + "-" + convertNumber((monthOfYear + 1)) + "-" + convertNumber(dayOfMonth));
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        etBod.getEditText().setFocusable(false);
    }

    private String convertNumber(int number){
        String str = String.valueOf(number);
        if (str.length()<2){
            str = "0"+str;
        }
        return str;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
