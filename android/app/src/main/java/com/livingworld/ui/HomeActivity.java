package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.adapter.HorizontalAdapter;
import com.livingworld.util.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.recylerView)
    RecyclerView recylerView;
    @BindView(R.id.tv_bam)
    TextView tvBam;
    @BindView(R.id.iv_inbox)
    ImageView ivInbox;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.view_card)
    RelativeLayout viewCard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cv_scan_bc)
    CardView cvScanBc;
    @BindView(R.id.cv_my_bc)
    CardView cvMyBc;
    @BindView(R.id.cv_parking)
    CardView cvParking;
    @BindView(R.id.tv_view_coffer)
    TextView tvViewCoffer;

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
                startActivity(new Intent(getApplicationContext(), ECashConnectActivity.class));
            }
        }));

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

    private void initMember() {
        ivInbox.setImageResource(R.drawable.ic_inbox);
        tvName.setVisibility(View.GONE);
        viewCard.setVisibility(View.VISIBLE);
    }

    private void initNotMember() {
        tvName.setVisibility(View.VISIBLE);
        viewCard.setVisibility(View.GONE);
    }

    @OnClick({R.id.cv_scan_bc, R.id.cv_my_bc, R.id.cv_parking, R.id.tv_view_coffer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_scan_bc:
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));
                break;
            case R.id.cv_my_bc:
                startActivity(new Intent(getApplicationContext(), MyQRCodeActivity.class));
                break;
            case R.id.cv_parking:
                startActivity(new Intent(getApplicationContext(), MyCarActivity.class));
                break;
            case R.id.tv_view_coffer:
                startActivity(new Intent(getApplicationContext(), CurrentOfferActivity.class));
                break;
        }
    }

}
