package com.livingworld.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.inbox.InboxService;
import com.livingworld.clients.model.Response;
import com.livingworld.util.Static;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class ContactUsActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.sumbit)
    Button sumbit;
    InboxService inboxService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);

        inboxService = ApiUtils.InboxService(getApplicationContext());

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentMessage();

            }
        });
    }

    private void sentMessage() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String comment = etComment.getText().toString();
        if (validation(name, email, comment)) return;
        sending(name, email, comment);
    }

    private void sending(String name, String email, String comment) {
        Map<String, String> map = new HashMap<>();
        map.put("fullName", name);
        map.put("email", email);
        map.put("message", comment);
        showPleasewaitDialog();
        inboxService.contacUs(map).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    boolean succes = (boolean) response.body().getData();
                    if(succes){
                        showMessage("Success to sent");
                    }else{
                        showMessage(response.body().getMessage());
                    }
                }else {
                    if (response.code()==400){
                        authenticationFailed();
                    }else {
                        showMessage(Static.SOMETHING_WRONG);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dissmissPleasewaitDialog();
                showMessage(Static.SOMETHING_WRONG);
            }
        });
    }

    private boolean validation(String name, String email, String comment) {
        if(name.isEmpty()){
            if(name.isEmpty()){
                showMessage("Name "+ Static.REQUIRED);
                return true;
            }
        }

        if(email.isEmpty()){
            if(email.isEmpty()){
                showMessage("Email "+ Static.REQUIRED);
                return true;
            }
        }

        if(comment.isEmpty()){
            if(comment.isEmpty()){
                showMessage("Comment "+ Static.REQUIRED);
                return true;
            }
        }
        return false;
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }
}
