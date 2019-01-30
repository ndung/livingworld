package com.livingworld.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
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
import com.livingworld.util.PartUtils;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.livingworld.util.StringUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
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
    TextInputLayout etName;
    @BindView(R.id.et_email)
    TextInputLayout etEmail;
    @BindView(R.id.et_phone)
    TextInputLayout etPhone;
    @BindView(R.id.sp_religion)
    Spinner spReligion;
    @BindView(R.id.sp_gender)
    Spinner spGender;
    @BindView(R.id.sp_material_status)
    Spinner spMaterialStatus;
    @BindView(R.id.et_bod)
    TextInputLayout etBod;
    @BindView(R.id.sp_nationality)
    Spinner spNationality;
    @BindView(R.id.et_address)
    TextInputLayout etAddress;
    @BindView(R.id.sp_city)
    Spinner spCity;
    @BindView(R.id.et_zip_code)
    TextInputLayout etZipCode;
    @BindView(R.id.et_home_phone)
    TextInputLayout etHomePhone;
    @BindView(R.id.button4)
    Button submit;
    User user;

    MasterService masterService;
    MemberService memberService;

    List<Religion> religionMasters = new ArrayList<>();
    List<City> cityMasters = new ArrayList<>();
    List<MaritalStatus> materialStatusMasters = new ArrayList<>();
    List<Gender> genderMasters = new ArrayList<>();
    List<Nationality> nationalityMasters = new ArrayList<>();

    Member member = null;

    private static final int CODE_CAMERA = 3321;
    private static final int CODE_GALLERY = 3322;

    private static final String TAG = InformationAccountActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        ButterKnife.bind(this);

        masterService = ApiUtils.MasterService(getApplicationContext());
        memberService = ApiUtils.MemberService(getApplicationContext());

        user = Preferences.getUser(getApplicationContext());

        member = user.getMember();
//        initData(user);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        initTglLahir();
        initChangePicture();

        if (user.getPhotoProfileUrl()!=null) {
            Glide.with(getApplicationContext()).load(Static.IMAGES_URL + user.getPhotoProfileUrl()).into(ivProfile);
        }else{
            Glide.with(getApplicationContext()).load(Static.NO_IMAGE_URL).into(ivProfile);
        }

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

        etName.getEditText().setText(member.getIdentityName());
        etEmail.getEditText().setText(member.getEmail());
        etAddress.getEditText().setText(member.getAddress());
        etBod.getEditText().setText(member.getDateOfBirth());
        etPhone.getEditText().setText(user.getMobileNumber());
        etHomePhone.getEditText().setText(member.getHomePhone());
        etZipCode.getEditText().setText(member.getZipcode());
    }

    private void initChangePicture() {
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImageProfile();
            }
        });
    }

    private void initTglLahir() {
        etBod.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                etBod.getEditText().setText(year + "-" + convertNumber((monthOfYear + 1)) + "-" + convertNumber(dayOfMonth));
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        etBod.getEditText().setFocusable(false);
    }

    private String convertNumber(int number){
        String str = String.valueOf(number);
        if (str.length()<2){
            str = "0"+str;
        }
        return str;
    }

    private void changeImageProfile() {
        TedPermission.with(InformationAccountActivity.this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        String[] list = new String[]{"Camera", "Gallery"};
                        new MaterialDialog.Builder(InformationAccountActivity.this)
                                .title("Choose")
                                .items(list)
                                .itemsCallback(new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                        if(position == 0){
                                            EasyImage.openCamera(InformationAccountActivity.this, CODE_CAMERA);
                                        } else if(position == 1){
                                            EasyImage.openGallery(InformationAccountActivity.this, CODE_GALLERY);
                                        }
                                    }
                                }).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                })
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type) {
                    case CODE_CAMERA:
                        confirmDialog(imageFile);
                        break;
                    case CODE_GALLERY:
                        confirmDialog(imageFile);
                        break;
                }
            }

        });
    }

    private void confirmDialog(final File path){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                showPleasewaitDialog();
                MultipartBody.Part body = PartUtils.prepareFilePart("photo", path);
                memberService.upload(body).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        dissmissPleasewaitDialog();
                        if(response.isSuccessful()){
                            Gson gson = new Gson();
                            JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                            User user = gson.fromJson(jsonObject.get("data"), User.class);
                            Preferences.setUser(getApplicationContext(), user);
                            Glide.with(getApplicationContext()).load(Static.IMAGES_URL+user.getPhotoProfileUrl()).into(ivProfile);
                            dialog.dismiss();
                            //showMessage("Berhasil upload photo profile");
                        }else if (response.errorBody() != null) {
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
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_image_confirmation, null);
        dialog.setView(dialogLayout);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        ImageView image = dialogLayout.findViewById(R.id.iv_show_image);
        Glide.with(getApplicationContext()).load(path).into(image);

        dialog.show();
    }

    private void setData(){
        Map<String,String> map = new HashMap<>();
        boolean bool = true;
        if(etName.getEditText().getText().toString().isEmpty()){
            etName.setError("Name should not be empty");
            bool = false;
        }else{
            etName.setError(null);
        }
        map.put("name",etName.getEditText().getText().toString());

        if(etEmail.getEditText().getText().toString().isEmpty()){
            etEmail.setError("Email should not be empty");
            bool = false;
        }else if (!StringUtils.isEmailValid(etEmail.getEditText().getText().toString())){
            etEmail.setError("Email should be valid");
            bool = false;
        }else{
            etEmail.setError(null);
        }
        map.put("email",etEmail.getEditText().getText().toString());

        if(etPhone.getEditText().getText().toString().isEmpty()){
            etPhone.setError("Mobile Number should not be empty");
            bool = false;
        }else if(etPhone.getEditText().getText().toString().length()<10 || !StringUtils.isMobilePhoneNumberValid(etPhone.getEditText().getText().toString())){
            etPhone.setError("Mobile Number should be valid");
            bool = false;
        }else{
            etPhone.setError(null);
        }
        map.put("mobilePhoneNumber",etPhone.getEditText().getText().toString());

        if(etBod.getEditText().getText().toString().isEmpty()){
            etBod.setError("Date of Birth should not be empty");
            bool = false;
        }else{
            etBod.setError(null);
        }
        map.put("birthOfDate",etBod.getEditText().getText().toString());

        if(etAddress.getEditText().getText().toString().isEmpty()){
            etAddress.setError("Address should not be empty");
            bool = false;
        }else{
            etAddress.setError(null);
        }
        map.put("address",etAddress.getEditText().getText().toString());

        if(etZipCode.getEditText().getText().toString().isEmpty()){
            etZipCode.setError("Zip Code should not be empty");
            bool = false;
        }else if (etZipCode.getEditText().getText().toString().length()!=5){
            etZipCode.setError("Zip Code should be valid");
            bool = false;
        }else{
            etZipCode.setError(null);
        }
        map.put("zipCode",etZipCode.getEditText().getText().toString());

        if(etHomePhone.getEditText().getText().toString().isEmpty()){
            etHomePhone.setError("Home phone number should not be empty");
            bool = false;
        } else if (etHomePhone.getEditText().getText().toString().length()<8){
            etHomePhone.setError("Home phone number should be valid");
            bool = false;
        }else{
            etHomePhone.setError(null);
        }
        map.put("homePhoneNumber",etHomePhone.getEditText().getText().toString());

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
        map.put("cardNumber", member.getCardNumber());
        map.put("tid", user.getUserId());
        map.put("idNumber", member.getIdenitityNumber());
        map.put("cardId", member.getMemberType());
        map.put("memberType", "01");

        if (bool) {
            showPleasewaitDialog();
            memberService.updateMember(map).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    dissmissPleasewaitDialog();
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                        User user = gson.fromJson(jsonObject.get("data"), User.class);
                        Preferences.setUser(getApplicationContext(), user);
                        showMessage("Success");
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
