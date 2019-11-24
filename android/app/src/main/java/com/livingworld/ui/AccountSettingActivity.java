package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.model.Response;
import com.livingworld.clients.rewards.model.RedeemedReward;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.thefinestartist.Base;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AccountSettingActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_profile)
    CircleImageView ivProfile;
    @BindView(R.id.menu_trx_history)
    LinearLayout menuTrxHistory;
    @BindView(R.id.menu_lucky_draw)
    LinearLayout menuLuckyDraw;
    @BindView(R.id.menu_redeemed_reward)
    LinearLayout menuRedeemedReward;
    @BindView(R.id.menu_about_us)
    LinearLayout menuAboutUs;
    @BindView(R.id.menu_feedback)
    LinearLayout menuFeedback;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;

    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);

        authService = ApiUtils.AuthService(this);

//        Glide.with(this).load("https://scontent-sit4-1.cdninstagram.com/t51.2885-19/s150x150/22158665_821785821334849_4414678351050964992_n.jpg").apply(new RequestOptions().centerCrop()).into(ivProfile);

        User user = Preferences.getUser(getApplicationContext());

        if (user.getPhotoProfileUrl() != null) {
            Glide.with(getApplicationContext()).load(Static.IMAGES_URL + user.getPhotoProfileUrl()).into(ivProfile);
        } else {
            Glide.with(getApplicationContext()).load(Static.NO_IMAGE_URL).into(ivProfile);
        }
        tvName.setText(user.getFullName());
        tvCardNumber.setText(user.getMember().getCardNumber());
        if (user.getStatus() == 3) {
            tvCardNumber.setVisibility(View.GONE);
        } else {
            tvCardNumber.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = Preferences.getUser(getApplicationContext());

        if (user.getPhotoProfileUrl() != null) {
            Glide.with(getApplicationContext()).load(Static.IMAGES_URL + user.getPhotoProfileUrl()).into(ivProfile);
        } else {
            Glide.with(getApplicationContext()).load(Static.NO_IMAGE_URL).into(ivProfile);
        }
        tvName.setText(user.getFullName());
        tvCardNumber.setText(user.getMember().getCardNumber());
    }

    @OnClick({R.id.iv_finish, R.id.menu_trx_history, R.id.menu_lucky_draw, R.id.menu_redeemed_reward, R.id.menu_about_us, R.id.menu_language, R.id.menu_feedback, R.id.ll_profile, R.id.menu_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.ll_profile:
                startActivity(new Intent(getApplicationContext(), InformationAccountActivity.class));
                break;
            case R.id.menu_trx_history:
                startActivity(new Intent(getApplicationContext(), TrxHistoryActivity.class));
                break;
            case R.id.menu_lucky_draw:
                startActivity(new Intent(getApplicationContext(), LuckyDrawsActivity.class));
                break;
            case R.id.menu_redeemed_reward:
                startActivity(new Intent(getApplicationContext(), RedeemedRewardActivity.class));
                break;
            case R.id.menu_about_us:
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                break;
            case R.id.menu_language:
                startActivity(new Intent(getApplicationContext(), LanguageChoiceActivity.class));
                break;
            case R.id.menu_feedback:
                startActivity(new Intent(getApplicationContext(), GiveUsFeedbackActivity.class));
                break;
            case R.id.menu_logout:
                new MaterialDialog.Builder(AccountSettingActivity.this)
                        .title("Confirm Logout")
                        .content("Are you sure?")
                        .positiveText("Yes")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                signOut();
                            }
                        })
                        .negativeText("No")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        }).show();

                break;
        }
    }

    private void signOut() {
        showPleasewaitDialog();

        authService.signOut().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if (response.isSuccessful()) {
                    Preferences.setUser(getApplicationContext(), null);
                    Preferences.setPublicKey(getApplicationContext(), null);
                    Preferences.setToken(getApplicationContext(), null);
                    Preferences.setLoginFlag(getApplicationContext(), false);
                    Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.errorBody() != null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string().trim());
                        showSnackbar(jObjError.getString("message"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showSnackbar(Static.SOMETHING_WRONG);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dissmissPleasewaitDialog();
                showSnackbar(Static.SOMETHING_WRONG);
            }
        });

    }
}
