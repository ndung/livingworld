package com.livingworld.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.livingworld.R;
import com.livingworld.adapter.HorizontalAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.master.model.MemberType;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.member.model.Member;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.offers.OffersService;
import com.livingworld.clients.offers.model.CurrentOffer;
import com.livingworld.clients.rewards.RewardsService;
import com.livingworld.clients.rewards.model.Event;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.util.ChipperUtils;
import com.livingworld.util.GsonDeserializer;
import com.livingworld.util.IDRUtils;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.iv_inbox)
    ImageView ivInbox;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.view_card)
    RelativeLayout viewCard;
    @BindView(R.id.cv_scan_bc)
    CardView cvScanBc;
    @BindView(R.id.cv_my_bc)
    CardView cvMyBc;
    @BindView(R.id.cv_parking)
    CardView cvParking;
    @BindView(R.id.tv_view_coffer)
    TextView tvViewCoffer;
    @BindView(R.id.pg_wait_trx_month)
    ProgressBar pgWaitTrxMonth;
    @BindView(R.id.tv_trx_month)
    TextView tvTrxMonth;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.tv_name_card)
    TextView tvNameCard;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_poin)
    TextView tvPoin;
    @BindView(R.id.tv_lucdraw)
    TextView tvLucdraw;
    @BindView(R.id.iv_live_card)
    ImageView ivLiveCard;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_member)
    TextView tvMember;
    @BindView(R.id.tv_bam)
    TextView tvBam;

    @BindView(R.id.ll_reward)
    RelativeLayout eventLayout;
    @BindView(R.id.tv_eventName)
    TextView tvEventName;
    @BindView(R.id.tv_eventDate)
    TextView tvEventDate;

    MemberService memberService;
    TrxService trxService;
    OffersService offersService;
    RewardsService rewardService;

    HorizontalAdapter adapter;
    List<CurrentOffer> list;
    Event event = null;

    private int MODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        trxService = ApiUtils.TrxService(getApplicationContext());
        memberService = ApiUtils.MemberService(getApplicationContext());
        offersService = ApiUtils.OffersService(getApplicationContext());
        rewardService = ApiUtils.RewardService(getApplicationContext());

        swiperefresh.setOnRefreshListener(this);
        tvTrxMonth.setVisibility(View.VISIBLE);
        pgWaitTrxMonth.setVisibility(View.GONE);
        //getBalanceMonth();

        list = new ArrayList<>();
        adapter = new HorizontalAdapter(getApplicationContext(), list, new HorizontalAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(CurrentOffer obj) {
                Intent intent = new Intent(getApplicationContext(), CurrentOfferDetailActivity.class);
                intent.putExtra("currentOffer", obj);
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setAdapter(adapter);

        getCurrentOffers();
        getCurrentEvent();

        tvBam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BAMActivity.class));
            }
        });

        ivInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InboxActivity.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AccountSettingActivity.class));
            }
        });

        initMember();

    }

    private static final String TAG = HomeActivity.class.toString();

    private void getCurrentOffers() {
        offersService.getCurrentOffers().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    List<CurrentOffer> offers = list;
                    list.removeAll(offers);
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<CurrentOffer> currentOffers = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<CurrentOffer>>() {
                    }.getType());
                    for (CurrentOffer obj : currentOffers) {
                        list.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");

    private void getCurrentEvent(){
        rewardService.getCurrentEvent().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDeserializer()).create();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();

                    List<Event> currentEvents = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Event>>() {
                    }.getType());
                    Log.d(TAG,"currentEvents:"+currentEvents);
                    if (currentEvents!=null && !currentEvents.isEmpty()){
                        event = currentEvents.get(0);
                        tvEventName.setText(Html.fromHtml(event.getName()));
                        String date = dateFormatter.format(event.getStartDate())+" - "+dateFormatter.format(event.getEndDate());
                        tvEventDate.setText(date);
                    }else if (currentEvents.isEmpty()){
                        tvEventName.setText("Rewards");
                        tvEventDate.setText("Coming Soon");
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void initMember() {
        ivInbox.setImageResource(R.drawable.ic_inbox);
        tvName.setVisibility(View.GONE);
        //viewCard.setVisibility(View.VISIBLE);

        initGetDetail();

    }

    private void initGetDetail() {
        User user = Preferences.getUser(getApplicationContext());
        Member member = user.getMember();
        if (member != null) {

            String memberType = member.getMemberType();
            List<MemberType> memberTypes = member.getMemberTypes();

            Collections.sort(memberTypes, new Comparator<MemberType>() {
                @Override
                public int compare(MemberType o1, MemberType o2) {
                    return Double.valueOf(Double.parseDouble(o1.getMinimumTransaction())).
                            compareTo(Double.valueOf(Double.parseDouble(o2.getMinimumTransaction())));
                }
            });

            int i = 1;
            for (MemberType type : memberTypes){
                if (memberType.equals(type.getId())){
                    if (type.getName().equalsIgnoreCase("BLUE")){
                        ivLiveCard.setImageDrawable(getResources().getDrawable(R.drawable.blue));
                    }
                    else if (type.getName().equalsIgnoreCase("PREMIERE")){
                        ivLiveCard.setImageDrawable(getResources().getDrawable(R.drawable.black));
                    }
                    break;
                }
                i=i+1;
            }

            if (i==memberTypes.size()){
                tvTrxMonth.setText("Congratulations");
                progressBar.setVisibility(View.GONE);
                tvMember.setText(getResources().getString(R.string.black_member_description));
            }else {
                final Intent intent = new Intent(getApplicationContext(), AboutLiveCardActivity.class);
                Double currentMonthTransaction = 0d;
                if (member.getCurrentMonthTransaction() != null) {
                    currentMonthTransaction = Double.parseDouble(member.getCurrentMonthTransaction());
                }
                Double minimumValue = 500000d;
                String myString = String.format(getResources().getString(R.string.spend_more_description), IDRUtils.toRupiah(minimumValue), "GREEN");
                if (member.getMemberType().equalsIgnoreCase("DCB2E640-C16D-4D55-86D4-DEAB5A7DD092")&&currentMonthTransaction<minimumValue){
                    tvName.setVisibility(View.VISIBLE);
                    tvName.setText("Hello, "+user.getFullName()+"!");
                    viewCard.setVisibility(View.GONE);
                }else{
                    MemberType upgrade = memberTypes.get(i);
                    minimumValue = Double.parseDouble(upgrade.getMinimumTransaction());
                    myString = String.format(getResources().getString(R.string.spend_more_description), IDRUtils.toRupiah(minimumValue), upgrade.getName());
                    tvName.setVisibility(View.GONE);
                    viewCard.setVisibility(View.VISIBLE);
                    intent.putExtra("card", i);
                }
                myString = myString + ". Learn more";
                int j = myString.indexOf("Learn more");
                Spannable wordtoSpan = new SpannableString(myString);
                wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), j, j+10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvMember.setText(wordtoSpan);
                tvMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                });

                tvTrxMonth.setText(IDRUtils.toRupiah(currentMonthTransaction));
                Double progress = currentMonthTransaction / minimumValue * 100;
                progressBar.setProgress(progress.intValue());

                if (progress>=100){
                    tvBam.setVisibility(View.VISIBLE);
                }
            }

            tvNameCard.setText(member.getIdentityName());
            tvCard.setText(member.getCardNumber());
            tvPoin.setText(member.getPoints() + "pts");
            tvLucdraw.setText(member.getLuckyDraws());

        }
    }

    private static final int MY_PERMISSION = 1;

    @OnClick({R.id.cv_scan_bc, R.id.cv_my_bc, R.id.cv_parking, R.id.ll_reward, R.id.tv_view_coffer, R.id.cv_merchant})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_scan_bc:
                //MODE = 0;
                //if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION);
                //} else {
                //    new IntentIntegrator(this).initiateScan();
                //}
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));
                break;
            case R.id.cv_my_bc:
                startActivity(new Intent(getApplicationContext(), MyQRCodeActivity.class));
                break;
            case R.id.cv_parking:
                //MODE = 1;
                //if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION);
                //} else {
                //    new IntentIntegrator(this).initiateScan();
                //}
                //startActivity(new Intent(getApplicationContext(), MyCarActivity.class));
                break;
            case R.id.tv_view_coffer:
                startActivity(new Intent(getApplicationContext(), CurrentOffersActivity.class));
                break;
            case R.id.cv_merchant:
                startActivity(new Intent(getApplicationContext(), MerchantListActivity.class));
                break;
            case R.id.ll_reward:
                //if (event!=null) {
                    Intent intent = new Intent(getApplicationContext(), RewardsActivity.class);
                    intent.putExtra("event", event);
                    startActivity(intent);
                //}
                break;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION: {
                if (grantResults.length > 0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        showMessage("Please allow app permission for using Camera manually in your Android Settings");
                    } else {
                        new IntentIntegrator(this).initiateScan();
                    }
                }
                return;
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result.getContents() != null) {
                Intent intent = new Intent(getApplicationContext(), PaymentMethodActivity.class);
                intent.putExtra("MODE", MODE);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRefresh() {
        tvTrxMonth.setVisibility(View.GONE);
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
                        initGetDetail();
                    }
                    tvTrxMonth.setVisibility(View.VISIBLE);
                    pgWaitTrxMonth.setVisibility(View.GONE);
                }else{
                    tvTrxMonth.setVisibility(View.VISIBLE);
                    pgWaitTrxMonth.setVisibility(View.GONE);

                    if (response.code()==400){
                        authenticationFailed();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                tvTrxMonth.setVisibility(View.VISIBLE);
                pgWaitTrxMonth.setVisibility(View.GONE);

            }
        });
        getCurrentEvent();
        getCurrentOffers();
        swiperefresh.setRefreshing(false);
    }
}
