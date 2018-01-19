package com.livingworld.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.livingworld.R;
import com.livingworld.clients.inbox.model.Inbox;
import com.livingworld.util.Static;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InboxDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    Inbox inbox;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_detail);
        ButterKnife.bind(this);
        inbox = (Inbox) getIntent().getSerializableExtra(Static.MODEL_INBOX);

        tvTitle.setText(inbox.getTitle());
        tvDate.setText(inbox.getDate());
        tvContent.setText(inbox.getMessage());

    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
