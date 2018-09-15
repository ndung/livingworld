package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.MerchantCategoryAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.merchant.MerchantService;
import com.livingworld.clients.merchant.model.Merchant;
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
    @BindView(R.id.iv_search)
    SearchView ivSearch;
    @BindView(R.id.tv_header)
    TextView layoutHeader;

    MerchantService merchantService;
    List<MerchantCategory> list = new ArrayList<>();
    List<MerchantCategory> listMerchant;
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

                        listMerchant = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<MerchantCategory>>() {}.getType());
                        Log.d(TAG, "listMerchant:"+listMerchant);
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

        ivSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutHeader.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });
        ivSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                onSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        ivSearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ivSearch.onActionViewCollapsed();
                layoutHeader.setVisibility(View.VISIBLE);
                list.clear();
                for (MerchantCategory category : listMerchant) {
                    list.add(category);
                }
                recyclerView.setVisibility(View.VISIBLE);
                merchantAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!ivSearch.isIconified()) {
            ivSearch.setIconified(true);
        } else {
            finish();
        }
    }

    private void onSearch(String text){
        list.clear();
        for (MerchantCategory obj : listMerchant) {
            try {
                MerchantCategory category = obj.clone();
                List<Merchant> merchants = category.getMerchantList();
                List<Merchant> searched = new ArrayList<>();
                for (Merchant merchant : merchants) {
                    if (merchant.getMerchantName().toUpperCase().contains(text.toUpperCase())) {
                        searched.add(merchant);
                    }
                }
                category.setMerchantList(searched);
                if (!category.getMerchantList().isEmpty()) {
                    list.add(category);
                }
            }catch(Exception ex){
                Log.e(TAG, "err", ex);
            }
        }
        if (!list.isEmpty()) {
            merchantAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
