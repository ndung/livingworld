package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.adapter.HorizontalAdapter;
import com.livingworld.util.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.tv_bam)
    TextView tvBam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recylerView.setLayoutManager(layoutManager);
        recylerView.setAdapter(new HorizontalAdapter(getApplicationContext(), new HorizontalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        }));

        tvBam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
