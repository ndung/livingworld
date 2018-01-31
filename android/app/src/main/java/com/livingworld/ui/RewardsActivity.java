package com.livingworld.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.rewards.RewardsrService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class RewardsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    RewardsrService rewardsrService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        ButterKnife.bind(this);

        rewardsrService = ApiUtils.RewardsrService(getApplicationContext());
        swiperefresh.setOnRefreshListener(this);
        swiperefresh.setRefreshing(true);
        getTotalPoint();
    }

    private void getTotalPoint() {
        rewardsrService.getTotalPoint().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                swiperefresh.setRefreshing(false);
                if(response.isSuccessful()){
                    Response body = response.body();
                    tvPoint.setText(String.valueOf(body.getData()));
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                swiperefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getTotalPoint();
    }
}
