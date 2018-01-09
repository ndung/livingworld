package com.livingworld.ui.fragment.mycar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livingworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarAddFormFragment extends Fragment {


    public MyCarAddFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_car_add_form_fragment, container, false);
    }

}
