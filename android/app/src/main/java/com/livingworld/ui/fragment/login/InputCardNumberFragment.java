package com.livingworld.ui.fragment.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.livingworld.R;
import com.livingworld.clients.auth.model.User;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputCardNumberFragment extends Fragment {


    @BindView(R.id.et_card_number)
    EditText etCardNumber;
    Unbinder unbinder;

    public InputCardNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_number, container, false);
        unbinder = ButterKnife.bind(this, view);

        User user = Preferences.getUser(getActivity());
        if (user!=null && user.getMember()!=null && user.getMember().getCardNumber()!=null){
            etCardNumber.setText(user.getMember().getCardNumber());
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public String getNunber(){
        if (etCardNumber!=null) {
            return etCardNumber.getText().toString();
        }
        return null;
    }
}
