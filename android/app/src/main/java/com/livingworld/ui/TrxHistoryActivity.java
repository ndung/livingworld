package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.TrxAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.clients.trx.model.History;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class TrxHistoryActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.rv_item)
    RecyclerView recyclerView;

    TrxService trxService;
    TrxAdapter adapter;

    List<History> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trx_history);
        ButterKnife.bind(this);

        adapter = new TrxAdapter(this, list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        trxService = ApiUtils.TrxService(getApplicationContext());
        trxService.getHistory().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<History> data = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<History>>() {}.getType());
                    for (History obj : data){
                        list.add(obj);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showMessage(Static.SOMETHING_WRONG);
            }
        });
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
