package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.MerchantCategoryAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.merchant.MerchantService;
import com.livingworld.clients.merchant.model.MerchantCategory;
import com.livingworld.clients.model.Response;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MerchantSearchActivity extends BaseActivity {

    private static final String TAG = MerchantSearchActivity.class.toString();

    @BindView(R.id.et_search)
    EditText editText;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;

    List<MerchantCategory> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_search);
        ButterKnife.bind(this);

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
