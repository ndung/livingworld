package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.LuckyDrawAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.clients.trx.model.LuckyDraw;
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

/**
 * Created by ndoenks on 02/06/18.
 */

public class LuckyDrawsActivity extends BaseActivity {

    private static final String TAG = LuckyDrawsActivity.class.toString();

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.rv_item)
    RecyclerView recyclerView;

    TrxService trxService;
    LuckyDrawAdapter adapter;

    List<LuckyDraw> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_draws);
        ButterKnife.bind(this);

        adapter = new LuckyDrawAdapter(this, list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        trxService = ApiUtils.TrxService(getApplicationContext());
        trxService.getLuckyDraws().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<LuckyDraw> data = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<LuckyDraw>>() {}.getType());
                    Log.d(TAG, "list:"+list);
                    for (LuckyDraw obj : data){
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
