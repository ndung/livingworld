package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.InboxAdapter;
import com.livingworld.adapter.MerchantAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.inbox.model.Inbox;
import com.livingworld.clients.master.model.Master;
import com.livingworld.clients.merchant.MerchantService;
import com.livingworld.clients.merchant.model.Merchant;
import com.livingworld.clients.merchant.model.MerchantCategory;
import com.livingworld.clients.model.Response;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class MerchantListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MerchantService merchantService;
    List<MerchantCategory> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);
        ButterKnife.bind(this);

        merchantService = ApiUtils.MerchantService(getApplicationContext());

        showPleasewaitDialog();
        merchantService.getMerchant().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    Response body = response.body();
                    if(body.getData() != null){
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                        List<MerchantCategory> listMerchant = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<MerchantCategory>>() {}.getType());
                        list.addAll(listMerchant);
                        initAdapter();
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

    private void initAdapter() {
        if(list.size() > 0) {
            MerchantAdapter inboxAdapter = new MerchantAdapter(list, new MerchantAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(MerchantCategory model) {

                }
            });

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(inboxAdapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) {
                        int pastVisiblesItems, visibleItemCount, totalItemCount;

                        visibleItemCount = layoutManager.getChildCount();
                        totalItemCount = layoutManager.getItemCount();
                        pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

//                    if (!loading)
//                    {
//                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
//                        {
//                            loading = true;
//                            PAGE = PAGE + 1;
//                            getMessage();
//                        }
//                    }
                    }
                }
            });
        }
    }
}
