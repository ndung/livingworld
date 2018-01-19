package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.master.MasterService;
import com.livingworld.clients.master.model.Master;
import com.livingworld.clients.model.Response;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

public class InformationAccountActivity extends BaseActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_profile)
    CircleImageView ivProfile;
    @BindView(R.id.iv_change_pwd)
    ImageView ivChangePwd;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.sp_gender)
    Spinner spGender;
    @BindView(R.id.sp_material_status)
    Spinner spMaterialStatus;
    @BindView(R.id.et_bod)
    EditText etBod;
    @BindView(R.id.sp_nationality)
    Spinner spNationality;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.sp_city)
    Spinner spCity;
    @BindView(R.id.et_zip_code)
    EditText etZipCode;
    @BindView(R.id.et_home_phone)
    EditText etHomePhone;
    @BindView(R.id.button4)
    Button submit;
    User user;

    MasterService masterService;

    List<Master> religionMasters = new ArrayList<>();
    List<Master> cityMasters = new ArrayList<>();
    List<Master> materialStatusMasters = new ArrayList<>();
    List<Master> genderMasters = new ArrayList<>();
    List<Master> nationalityMasters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        ButterKnife.bind(this);

        masterService = ApiUtils.MasterService(getApplicationContext());

        Glide.with(this).load("https://scontent-sit4-1.cdninstagram.com/t51.2885-19/s150x150/22158665_821785821334849_4414678351050964992_n.jpg").apply(new RequestOptions().centerCrop()).into(ivProfile);

        user = Preferences.getUser(getApplicationContext());
        initData(user);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        initMasters();
    }

    private void initMasters() {
        initMasterReligion();
        initMasterMaterialStatus();
        initMasterGender();
        initMasterNationality();
        initMasterCity();
    }

    private void initMasterReligion() {
        masterService.getMaster(Static.MASTER_RELIGION).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    religionMasters.addAll((List<Master>) response.body().getData());
                    ArrayAdapter<Master> adapter =
                            new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, religionMasters);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
//                    spReligion.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void initMasterCity() {
        masterService.getMaster(Static.MASTER_CITY).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    cityMasters.addAll((List<Master>) response.body().getData());
                    ArrayAdapter<Master> adapter =
                            new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, cityMasters);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spCity.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void initMasterMaterialStatus() {
        masterService.getMaster(Static.MASTER_MATERIAL_STATUS).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    materialStatusMasters.addAll((List<Master>) response.body().getData());
                    ArrayAdapter<Master> adapter =
                            new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, materialStatusMasters);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spMaterialStatus.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void initMasterGender() {
        masterService.getMaster(Static.MASTER_GENDER).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    genderMasters.addAll((List<Master>) response.body().getData());
                    ArrayAdapter<Master> adapter =
                            new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, genderMasters);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spGender.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void initMasterNationality() {
        masterService.getMaster(Static.MASTER_NATIONALITY).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    nationalityMasters.addAll((List<Master>) response.body().getData());
                    ArrayAdapter<Master> adapter =
                            new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, nationalityMasters);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spNationality.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void initData(User user) {
        etName.setText(user.getFullName());
        etEmail.setText(user.getEmail());
        etAddress.setText(user.getAddress());
        etBod.setText(user.getDateOfBirth());
        etPhone.setText(user.getMobileNumber());
        etHomePhone.setText(user.getHomeNumber());
        etZipCode.setText(String.valueOf(user.getZipCode()));
    }

    private void setData(){
        if(etName.getText().toString().isEmpty()){
            showMessage("Name "+ Static.REQUIRED);
            return;
        }
        user.setFullName(etName.getText().toString());

        if(etEmail.getText().toString().isEmpty()){
            showMessage("Email "+ Static.REQUIRED);
            return;
        }
        user.setEmail(etEmail.getText().toString());

        if(etPhone.getText().toString().isEmpty()){
            showMessage("Mobile Number "+ Static.REQUIRED);
            return;
        }
        user.setMobileNumber(etPhone.getText().toString());

        if(etBod.getText().toString().isEmpty()){
            showMessage("Date of Birth "+ Static.REQUIRED);
            return;
        }
        user.setDateOfBirth(etBod.getText().toString());

        if(etAddress.getText().toString().isEmpty()){
            showMessage("Address "+ Static.REQUIRED);
            return;
        }
        user.setAddress(etAddress.getText().toString());

        if(etZipCode.getText().toString().isEmpty()){
            showMessage("ZIP Code "+ Static.REQUIRED);
            return;
        }
        user.setZipCode(Integer.valueOf(etZipCode.getText().toString()));

        if(etHomePhone.getText().toString().isEmpty()){
            showMessage("Home Number "+ Static.REQUIRED);
            return;
        }
        user.setHomeNumber(etHomePhone.getText().toString());
    }

    @OnClick({R.id.iv_finish, R.id.iv_profile, R.id.iv_change_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.iv_profile:

                break;
            case R.id.iv_change_pwd:
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                break;
        }
    }
}
