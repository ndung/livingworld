package com.livingworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.master.MasterService;
import com.livingworld.clients.master.model.Master;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.model.Response;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.Static;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class BAMRegistrationActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_ktp)
    EditText etKtp;
    @BindView(R.id.sp_religion)
    Spinner spReligion;
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
    @BindView(R.id.et_home)
    EditText etHome;
    @BindView(R.id.bt_next)
    Button btNext;
    MasterService masterService;
    List<Master> religionMasters = new ArrayList<>();
    List<Master> cityMasters = new ArrayList<>();
    List<Master> materialStatusMasters = new ArrayList<>();
    List<Master> genderMasters = new ArrayList<>();
    List<Master> nationalityMasters = new ArrayList<>();

    MemberService memberService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bamregistration);
        ButterKnife.bind(this);
        masterService = ApiUtils.MasterService(getApplicationContext());
        memberService = ApiUtils.MemberService(getApplicationContext());
        initMasters();

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etName.getText().toString();
                if(nama.isEmpty()){
                    showMessage("Name "+ Static.REQUIRED);
                    return;
                }

                String ktp = etKtp.getText().toString();
                if(ktp.isEmpty()){
                    showMessage("KTP "+ Static.REQUIRED);
                    return;
                }

                String date = etBod.getText().toString();
                if(date.isEmpty()){
                    showMessage("Date "+ Static.REQUIRED);
                    return;
                }

                String address = etAddress.getText().toString();
                if(address.isEmpty()){
                    showMessage("Address "+ Static.REQUIRED);
                    return;
                }

                String zipCode = etZipCode.getText().toString();
                if(zipCode.isEmpty()){
                    showMessage("ZIP Code "+ Static.REQUIRED);
                    return;
                }

                String homePhone = etHome.getText().toString();
                if(homePhone.isEmpty()){
                    showMessage("Home Phone "+ Static.REQUIRED);
                    return;
                }

                String idReligion = String.valueOf(religionMasters.get(spReligion.getSelectedItemPosition()).getId());
                String idGender = String.valueOf(genderMasters.get(spGender.getSelectedItemPosition()).getId());
                String idMaterialStatus = String.valueOf(materialStatusMasters.get(spMaterialStatus.getSelectedItemPosition()).getId());
                String idNationality = String.valueOf(nationalityMasters.get(spNationality.getSelectedItemPosition()).getId());
                String idCity = String.valueOf(cityMasters.get(spCity.getSelectedItemPosition()).getId());

                registerMember(nama, ktp, date, address, zipCode, homePhone, idReligion, idGender, idMaterialStatus, idNationality, idCity);
            }
        });
    }

    private void registerMember(String nama, String ktp, String date, String address, String zipCode, String homePhone, String idReligion, String idGender, String idMaterialStatus, String idNationality, String idCity) {
        showPleasewaitDialog();
        Map<String, String> map = new HashMap<>();
        map.put("fullName", nama);
        map.put("ktpNo", ktp);
        map.put("gender", idGender);
        map.put("religion", idReligion);
        map.put("martialStatus", idMaterialStatus);
        map.put("dateOfBirth", date);
        map.put("nationality", idNationality);
        map.put("address", address);
        map.put("city", idCity);
        map.put("zipCode", zipCode);
        map.put("homeNumber", homePhone);

        memberService.registerMember(map).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    Response body = response.body();
                    boolean success = (boolean) body.getData();
                    if(success){
                        showMessage("Success to register member");
                        finish();
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
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    List<Master> list = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Master>>() {}.getType());
                    religionMasters.addAll(list);
                    ArrayAdapter<Master> adapter =
                            new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, religionMasters);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spReligion.setAdapter(adapter);
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
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    List<Master> list = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Master>>() {}.getType());
                    cityMasters.addAll(list);
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
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    List<Master> list = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Master>>() {}.getType());
                    materialStatusMasters.addAll(list);
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
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    List<Master> list = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Master>>() {}.getType());
                    genderMasters.addAll(list);
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
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    List<Master> list = gson.fromJson(jsonObject.getAsJsonArray("data"), new TypeToken<List<Master>>() {}.getType());
                    nationalityMasters.addAll(list);
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
}
