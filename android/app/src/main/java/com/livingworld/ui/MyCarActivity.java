package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.InboxAdapter;
import com.livingworld.adapter.MyCarAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.car.CarrService;
import com.livingworld.clients.model.Response;
import com.livingworld.model.Inbox;
import com.livingworld.model.MyCar;
import com.livingworld.ui.fragment.mycar.MyCarAddFormFragment;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class MyCarActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    CarrService carrService;
    List<MyCar> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);
        ButterKnife.bind(this);

        carrService = ApiUtils.CarService(getApplicationContext());
        showPleasewaitDialog();
        carrService.getVehicle().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    List<MyCar> listBody = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<MyCar>>() {}.getType());
                    list.addAll(listBody);
                    initAdapter();
                }else{
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

    private void initAdapter() {
        MyCarAdapter adapter = new MyCarAdapter(list, new MyCarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyCar model) {
                Intent intent = new Intent(getApplicationContext(), MyCarAddActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra("vehicleId ", model.getId());
                    intent.putExtra("vehicleType", model.getName());
                intent.putExtra("vehicleColor", model.getColor());
                startActivity(intent);
            }
        }, new MyCarAdapter.OnAddClickListener() {
            @Override
            public void onAddClick() {
                startActivity(new Intent(getApplicationContext(), MyCarAddActivity.class));
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
    }
}
