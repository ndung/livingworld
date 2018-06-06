package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class MerchantListActivity extends BaseActivity {

    private static final String TAG = MerchantListActivity.class.toString();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;

    MerchantService merchantService;
    List<MerchantCategory> list = new ArrayList<>();
    MerchantCategoryAdapter merchantAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);
        ButterKnife.bind(this);

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        merchantService = ApiUtils.MerchantService(getApplicationContext());

        merchantAdapter = new MerchantCategoryAdapter(this, list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(merchantAdapter);

        showPleasewaitDialog();
        merchantService.getMerchant().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    Response body = response.body();
                    if(body.getData() != null){
                        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                        JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                        List<MerchantCategory> listMerchant = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<MerchantCategory>>() {}.getType());
                        for (MerchantCategory category : listMerchant) {
                            list.add(category);
                        }
                        merchantAdapter.notifyDataSetChanged();
                    }else{
                        showMessage(Static.SOMETHING_WRONG);
                    }
                } else {
                    showMessage(Static.SOMETHING_WRONG);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dissmissPleasewaitDialog();
                showMessage(Static.SOMETHING_WRONG);
            }
        });

    }

}
