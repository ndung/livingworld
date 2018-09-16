package com.livingworld.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.master.MasterService;
import com.livingworld.clients.master.model.City;
import com.livingworld.clients.master.model.Gender;
import com.livingworld.clients.master.model.MaritalStatus;
import com.livingworld.clients.master.model.Master;
import com.livingworld.clients.master.model.Nationality;
import com.livingworld.clients.master.model.Religion;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.member.model.Member;
import com.livingworld.clients.model.Response;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
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
    List<Religion> religionMasters = new ArrayList<>();
    List<City> cityMasters = new ArrayList<>();
    List<MaritalStatus> materialStatusMasters = new ArrayList<>();
    List<Gender> genderMasters = new ArrayList<>();
    List<Nationality> nationalityMasters = new ArrayList<>();

    MemberService memberService;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bamregistration);
        ButterKnife.bind(this);
        masterService = ApiUtils.MasterService(getApplicationContext());
        memberService = ApiUtils.MemberService(getApplicationContext());

        user = Preferences.getUser(getApplicationContext());

        Member member = user.getMember();

        religionMasters = user.getMember().getReligions();

        ArrayAdapter<Religion> religionAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, religionMasters);
        religionAdapter.setDropDownViewResource(R.layout.spinner_item);
        spReligion.setAdapter(religionAdapter);
        for (int i=0; i < religionMasters.size(); i++){
            Master master = religionMasters.get(i);
            if(member != null && master.getId().equalsIgnoreCase(member.getReligion())){
                spReligion.setSelection(i);
                break;
            }
        }

        cityMasters = user.getMember().getCities();

        ArrayAdapter<City> cityAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, cityMasters);
        cityAdapter.setDropDownViewResource(R.layout.spinner_item);
        spCity.setAdapter(cityAdapter);
        for (int i=0; i < cityMasters.size(); i++){
            Master master = cityMasters.get(i);
            if(member != null && master.getId().equalsIgnoreCase(member.getCity())){
                spCity.setSelection(i);
                break;
            }
        }

        materialStatusMasters = user.getMember().getMaritalStatuses();

        ArrayAdapter<MaritalStatus> materialStatusAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, materialStatusMasters);
        materialStatusAdapter.setDropDownViewResource(R.layout.spinner_item);
        spMaterialStatus.setAdapter(materialStatusAdapter);
        for (int i=0; i < materialStatusMasters.size(); i++){
            Master master = materialStatusMasters.get(i);
            if(member != null && master.getId().equalsIgnoreCase(member.getMartialStatus())){
                spMaterialStatus.setSelection(i);
                break;
            }
        }

        genderMasters = user.getMember().getGenders();

        ArrayAdapter<Gender> genderAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, genderMasters);
        genderAdapter.setDropDownViewResource(R.layout.spinner_item);
        spGender.setAdapter(genderAdapter);
        for (int i=0; i < genderMasters.size(); i++){
            Master master = genderMasters.get(i);
            if(member != null && master.getId().equalsIgnoreCase(member.getGender())){
                spGender.setSelection(i);
                break;
            }
        }

        nationalityMasters = user.getMember().getNationalities();

        ArrayAdapter<Nationality> nationalityAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, nationalityMasters);
        nationalityAdapter.setDropDownViewResource(R.layout.spinner_item);
        spNationality.setAdapter(nationalityAdapter);
        for (int i=0; i < nationalityMasters.size(); i++){
            Master master = nationalityMasters.get(i);
            if(member != null && master.getId().equalsIgnoreCase(member.getNationalitly())){
                spNationality.setSelection(i);
                break;
            }
        }

        etKtp.setText(member.getIdenitityNumber());
        etName.setText(member.getIdentityName());
        etAddress.setText(member.getAddress());
        etBod.setText(member.getDateOfBirth());
        etHome.setText(member.getHomePhone());
        etZipCode.setText(member.getZipcode());

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        etBod.setFocusable(false);
        etBod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                int month = (monthOfYear + 1);
                                String monthS;
                                if (month <= 9) {
                                    monthS = "0" + month;
                                } else {
                                    monthS = String.valueOf(month);
                                }

                                int day = dayOfMonth;
                                String dayS;
                                if (day <= 9) {
                                    dayS = "0" + day;
                                } else {
                                    dayS = String.valueOf(day);
                                }
                                String tgl = String.valueOf(year + "-" + monthS + "-" + dayS);
                                etBod.setText(tgl);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    private void setData(){
        Map<String, String> map = new HashMap<>();
        map.put("tid", user.getUserId());

        if(etKtp.getText().toString().isEmpty()){
            showMessage("KTP "+ Static.REQUIRED);
            return;
        }
        map.put("idNumber", etKtp.getText().toString());

        if(etName.getText().toString().isEmpty()){
            showMessage("Name "+ Static.REQUIRED);
            return;
        }
        map.put("name",etName.getText().toString());
        map.put("email",user.getMember().getEmail());
        map.put("mobilePhoneNumber",user.getMember().getMobileNumber());

        if(etBod.getText().toString().isEmpty()){
            showMessage("Date of Birth "+ Static.REQUIRED);
            return;
        }
        map.put("birthOfDate",etBod.getText().toString());

        if(etAddress.getText().toString().isEmpty()){
            showMessage("Address "+ Static.REQUIRED);
            return;
        }
        map.put("address",etAddress.getText().toString());

        if(etZipCode.getText().toString().isEmpty()){
            showMessage("ZIP Code "+ Static.REQUIRED);
            return;
        }
        map.put("zipCode",etZipCode.getText().toString());

        //if(etHome.getText().toString().isEmpty()){
        //    showMessage("Home Number "+ Static.REQUIRED);
        //    return;
        //}
        map.put("homePhoneNumber",etHome.getText().toString());

        map.put("memberType", "38C5EAD7-7E31-4A7E-9545-FEA543B8751E");
        map.put("cardId", "38C5EAD7-7E31-4A7E-9545-FEA543B8751E");
        map.put("cardNumber", user.getMember().getCardNumber());

        for (int i=0; i < religionMasters.size(); i++){
            Master master = religionMasters.get(i);
            if(master.getName().equalsIgnoreCase(spReligion.getSelectedItem().toString())){
                map.put("religion",master.getId());
                break;
            }
        }

        for (int i=0; i < genderMasters.size(); i++){
            Master master = genderMasters.get(i);
            if(master.getName().equalsIgnoreCase(spGender.getSelectedItem().toString())){
                map.put("gender",master.getId());
                break;
            }
        }

        for (int i=0; i < materialStatusMasters.size(); i++){
            Master master = materialStatusMasters.get(i);
            if(master.getName().equalsIgnoreCase(spMaterialStatus.getSelectedItem().toString())){
                map.put("maritalStatus",master.getId());
                break;
            }
        }

        for (int i=0; i < nationalityMasters.size(); i++){
            Master master = nationalityMasters.get(i);
            if(master.getName().equalsIgnoreCase(spNationality.getSelectedItem().toString())){
                map.put("nationality",master.getId());
                break;
            }
        }

        for (int i=0; i < cityMasters.size(); i++){
            Master master = cityMasters.get(i);
            if(master.getName().equalsIgnoreCase(spCity.getSelectedItem().toString())){
                map.put("city",master.getId());
                break;
            }
        }

        showPleasewaitDialog();
        memberService.updateMember(map).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dissmissPleasewaitDialog();
                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                    User user = gson.fromJson(jsonObject.get("data"), User.class);
                    Preferences.setUser(getApplicationContext(), user);
                    showMessage("Berhasil update profil");
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

}
