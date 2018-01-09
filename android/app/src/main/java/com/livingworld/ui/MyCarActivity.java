package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.livingworld.R;
import com.livingworld.adapter.InboxAdapter;
import com.livingworld.adapter.MyCarAdapter;
import com.livingworld.model.Inbox;
import com.livingworld.model.MyCar;
import com.livingworld.ui.fragment.mycar.MyCarAddFormFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCarActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);
        ButterKnife.bind(this);

        initAdapter();

    }

    private void initAdapter() {
        MyCarAdapter adapter = new MyCarAdapter(null, new MyCarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyCar model) {

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
