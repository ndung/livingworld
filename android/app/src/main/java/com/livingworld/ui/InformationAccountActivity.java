package com.livingworld.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;
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
    @BindView(R.id.et_home_phone)
    EditText etHomePhone;
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

        etName.setText(member.getIdentityName());
        etEmail.setText(member.getEmail());
        etAddress.setText(member.getAddress());
        etBod.setText(member.getDateOfBirth());
        etPhone.setText(user.getMobileNumber());
        etHomePhone.setText(member.getHomePhone());
        etZipCode.setText(member.getZipcode());
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
        etBod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                etBod.setText(year + "-" + convertNumber((monthOfYear + 1)) + "-" + convertNumber(dayOfMonth));
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        etBod.setFocusable(false);
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
                                .title("Pilih")
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
//                        String nameNewFile = PartUtils.prepareUpload(getApplicationContext(), imageFile, user.getId());
//                        imageKTPPath = nameNewFile;
//                        Glide.with(InformationAccountActivity.this).load(imageFile).into(ivProfile);
                        confirmDialog(imageFile);
                        break;
                    case CODE_GALLERY:
//                        nameNewFile = PartUtils.prepareUpload(getApplicationContext(), imageFile, user.getId());
//                        imageKTPPath = nameNewFile;
//                        Glide.with(InformationAccountActivity.this).load(imageFile).into(ivProfile);
                        confirmDialog(imageFile);
                        break;
                }
            }

        });
    }

    private void confirmDialog(final File path){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
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
                            showMessage("Berhasil upload photo profile");
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
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
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
//        Bitmap icon = BitmapFactory.decodeFile(path.getAbsolutePath());
//        image.setImageBitmap(icon);

        dialog.show();


    }

    private void setData(){
        Map<String,String> map = new HashMap<>();

        if(etName.getText().toString().isEmpty()){
            showMessage("Name "+ Static.REQUIRED);
            return;
        }
        map.put("name",etName.getText().toString());

        if(etEmail.getText().toString().isEmpty()){
            showMessage("Email "+ Static.REQUIRED);
            return;
        }
        map.put("email",etEmail.getText().toString());

        if(etPhone.getText().toString().isEmpty()){
            showMessage("Mobile Number "+ Static.REQUIRED);
            return;
        }
        map.put("mobilePhoneNumber",etPhone.getText().toString());

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

        if(etHomePhone.getText().toString().isEmpty()){
            showMessage("Home Number "+ Static.REQUIRED);
            return;
        }
        map.put("homePhoneNumber",etHomePhone.getText().toString());

        for (int i=0; i < religionMasters.size(); i++){
            Master master = religionMasters.get(i);
            if(master.getName().equalsIgnoreCase(spReligion.getSelectedItem().toString())){
                map.put("religion",master.getId());
                break;
            }
        }
        Log.d(TAG,"religion:"+map.get("religion"));

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
        map.put("memberType", member.getMemberType());

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
