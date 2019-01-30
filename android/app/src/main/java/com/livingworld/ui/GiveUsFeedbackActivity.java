package com.livingworld.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.inbox.InboxService;
import com.livingworld.clients.model.Response;
import com.livingworld.util.Static;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class GiveUsFeedbackActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.bt_next)
    Button sumbit;
    @BindView(R.id.editText)
    EditText editText;
    InboxService inboxService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_us_feedback);
        ButterKnife.bind(this);
        rating.setIsIndicator(false);
        rating.setRating(5);

        inboxService = ApiUtils.InboxService(getApplicationContext());

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sending(rating.getRating(), editText.getText().toString());

            }
        });
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        finish();
    }

    private void sending(float rating, String comment) {
        Map<String, String> map = new HashMap<>();
        map.put("rating", String.valueOf(rating));
        map.put("comment", comment);
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
                }else if (response.errorBody() != null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                        showMessage(jObjError.getString("message"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
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

}
