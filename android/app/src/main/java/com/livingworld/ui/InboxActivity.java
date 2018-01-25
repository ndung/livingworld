package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.adapter.InboxAdapter;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.inbox.InboxService;
import com.livingworld.clients.inbox.model.Inbox;
import com.livingworld.clients.model.Response;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class InboxActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    InboxService inboxService;
    List<Inbox> list = new ArrayList<>();
    boolean loading = false;
    private int PAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ButterKnife.bind(this);
        inboxService = ApiUtils.InboxService(getApplicationContext());

        showPleasewaitDialog();
        getMessage();

    }

    private void getMessage() {
        inboxService.getMessage("message/"+PAGE+"/page").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if (response.isSuccessful()){
                    Response body = response.body();
                    if(body.getData() != null){
                        list.addAll((List<Inbox>) body.getData());
                        initAdapter();
                        loading = false;
                    }
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
        InboxAdapter inboxAdapter = new InboxAdapter(list, new InboxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Inbox model) {
                Intent intent = new Intent(getApplicationContext(), InboxDetailActivity.class);
                intent.putExtra(Static.MODEL_INBOX, model);
                startActivity(intent);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(inboxAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0)
                {
                    int pastVisiblesItems, visibleItemCount, totalItemCount;

                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = true;
                            PAGE = PAGE + 1;
                            getMessage();
                        }
                    }
                }
            }
        });
    }
}
