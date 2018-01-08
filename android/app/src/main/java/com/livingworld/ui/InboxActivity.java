package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.livingworld.R;
import com.livingworld.adapter.InboxAdapter;
import com.livingworld.model.Inbox;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InboxActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ButterKnife.bind(this);
        initAdapter();

    }

    private void initAdapter() {
        InboxAdapter inboxAdapter = new InboxAdapter(null, new InboxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Inbox model) {
                startActivity(new Intent(getApplicationContext(), InboxDetailActivity.class));
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(inboxAdapter);
    }
}
