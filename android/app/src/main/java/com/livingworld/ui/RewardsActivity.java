package com.livingworld.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.RewardsAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.rewards.RewardsService;
import com.livingworld.clients.rewards.model.Event;
import com.livingworld.clients.rewards.model.Redeem;
import com.livingworld.clients.rewards.model.Reward;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.thefinestartist.Base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class RewardsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

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
    @BindView(R.id.pg_wait_trx_month)
    ProgressBar pgWaitTrxMonth;

    MemberService memberService;
    RewardsService rewardsService;
    RewardsAdapter rewardsAdapter;
    Event event;
    List<Reward> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        ButterKnife.bind(this);

        memberService = ApiUtils.MemberService(getApplicationContext());
        rewardsService = ApiUtils.RewardService(getApplicationContext());
        swiperefresh.setOnRefreshListener(this);
        pgWaitTrxMonth.setVisibility(View.GONE);

        User user = Preferences.getUser(getApplicationContext());
        tvPoint.setText(user.getMember().getPoints()+" Points");

        event = (Event) getIntent().getSerializableExtra("event");
        list = new ArrayList<>();
        if (event!=null){
            tvRewardsTitle.setText(event.getDescription());
            String date = dateFormatter.format(event.getStartDate())+" - "+dateFormatter.format(event.getEndDate());
            tvRewardsDate.setText(date);
            //list = event.getRewards();
            if (event.getImage()!=null) {
                Glide.with(this)
                        .load(Static.IMAGES_URL+event.getImage()).into(imageView);
            }else{
                Glide.with(this)
                        .load(Static.NO_IMAGE_URL).into(imageView);
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
        //recyclerView.addItemDecoration(new SpacesItemDecoration(0));

        getCurrentRewards();
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

    @Override
    public void onRefresh() {
        tvPoint.setVisibility(View.GONE);
        pgWaitTrxMonth.setVisibility(View.VISIBLE);
        memberService.refreshUser().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Response body = response.body();
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(body.getData()).getAsJsonObject();

                    User user = gson.fromJson(jsonObject, User.class);
                    if (user!=null){
                        Preferences.setUser(getApplicationContext(), user);
                        tvPoint.setText(user.getMember().getPoints()+" Points");
                    }
                    tvPoint.setVisibility(View.VISIBLE);
                    pgWaitTrxMonth.setVisibility(View.GONE);
                }else{
                    tvPoint.setVisibility(View.VISIBLE);
                    pgWaitTrxMonth.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                tvPoint.setVisibility(View.VISIBLE);
                pgWaitTrxMonth.setVisibility(View.GONE);
            }
        });
        getCurrentEvent();
        getCurrentRewards();
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

    private void getCurrentEvent(){
        rewardsService.getCurrentEvent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<Event> currentEvents = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Event>>() {
                    }.getType());
                    if (currentEvents!=null && !currentEvents.isEmpty()){
                        event = currentEvents.get(0);
                        tvRewardsTitle.setText(event.getDescription());
                        String date = dateFormatter.format(event.getStartDate())+" - "+dateFormatter.format(event.getEndDate());
                        tvRewardsDate.setText(date);
                        //List<Reward> rewards = list;
                        //list.removeAll(rewards);
                        //rewards = event.getRewards();
                        //for (Reward reward : rewards){
                        //    list.add(reward);
                        //}
                        //rewardsAdapter.notifyDataSetChanged();
                        if (event.getImage()!=null) {
                            Glide.with(getApplicationContext())
                                    .load(Static.IMAGES_URL+event.getImage()).into(imageView);
                        }else{
                            Glide.with(getApplicationContext())
                                    .load(Static.NO_IMAGE_URL).into(imageView);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable throwable) {

            }
        });
    }

    private void getCurrentRewards(){
        List<Reward> rewards = list;
        list.removeAll(rewards);
        rewardsService.getRewardList().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<Reward> data = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Reward>>() {}.getType());
                    Log.d("TAG", "data:"+data);
                    for (Reward obj : data){
                        list.add(obj);
                    }
                    rewardsAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showMessage(Static.SOMETHING_WRONG);
            }
        });
    }
}
