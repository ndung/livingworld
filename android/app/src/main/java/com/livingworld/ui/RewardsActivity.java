package com.livingworld.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.adapter.RewardsAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.rewards.RewardsService;
import com.livingworld.clients.rewards.model.Event;
import com.livingworld.clients.rewards.model.Reward;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class RewardsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.tv_rewardsTitle)
    TextView tvRewardsTitle;
    @BindView(R.id.tv_rewardsDate)
    TextView tvRewardsDate;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.rv_item)
    RecyclerView recyclerView;

    RewardsService rewardsService;
    RewardsAdapter rewardsAdapter;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        ButterKnife.bind(this);

        rewardsService = ApiUtils.RewardService(getApplicationContext());
        swiperefresh.setOnRefreshListener(this);
        //swiperefresh.setRefreshing(true);

        User user = Preferences.getUser(getApplicationContext());
        tvPoint.setText(user.getMember().getPoints()+" Points");
        //getTotalPoint();

        event = (Event) getIntent().getSerializableExtra("event");
        List<Reward> list = new ArrayList<>();
        if (event!=null){
            tvRewardsTitle.setText(event.getDescription());
            String date = dateFormatter.format(event.getStartDate())+" - "+dateFormatter.format(event.getEndDate());
            tvRewardsDate.setText(date);
            list = event.getRewards();
            if (event.getImage()!=null) {
                Glide.with(this)
                        .load(Static.IMAGES_URL+event.getImage()).into(imageView);
            }else{
                Glide.with(this)
                        .load("http://103.27.207.124/~ifabula/demo/img/seller_galery/no_image.jpg").into(imageView);
            }
        }

        rewardsAdapter = new RewardsAdapter(this, list, new RewardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Reward reward) {
                Intent intent = new Intent(getApplicationContext(), RewardRedeemActivity.class);
                intent.putExtra("reward", reward);
                startActivity(intent);
            }
        });
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setAdapter(rewardsAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(16));

    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildLayoutPosition(view) % 2 == 0){
                outRect.right = space;
            }else{
                outRect.right = 0;
            }
            outRect.left = 0;
            outRect.bottom = space;
            outRect.top = 0;

        }
    }

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");

    private void getTotalPoint() {
        rewardsService.getTotalPoint().enqueue(new Callback<Response>() {
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
        //getTotalPoint();
        swiperefresh.setRefreshing(false);
    }

    @OnClick({R.id.iv_finish, R.id.how_to_redeem_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.how_to_redeem_layout:
                startActivity(new Intent(this, HowToRedeemActivity.class));
                break;
        }
    }
}
