package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.VerticalAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.offers.OffersService;
import com.livingworld.clients.offers.model.CurrentOffer;
import com.livingworld.util.GsonDeserializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class CurrentOffersActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.rv_item)
    RecyclerView recyclerView;

    VerticalAdapter adapter;
    List<CurrentOffer> list;

    OffersService offersService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_offer);
        ButterKnife.bind(this);

        offersService = ApiUtils.OffersService(getApplicationContext());

        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = new ArrayList<>();
        adapter = new VerticalAdapter(this, list, new VerticalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CurrentOffer obj) {
                Intent intent = new Intent(getApplicationContext(), CurrentOfferDetailActivity.class);
                intent.putExtra("currentOffer", obj);
                startActivity(intent);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getCurrentOffers();
    }

    private void getCurrentOffers(){
        offersService.getCurrentOffers().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<CurrentOffer> currentOffers = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<CurrentOffer>>() {}.getType());
                    for (CurrentOffer obj : currentOffers) {
                        list.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable throwable) {

            }
        });
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
    }
}
