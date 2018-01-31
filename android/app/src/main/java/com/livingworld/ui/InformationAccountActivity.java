package com.livingworld.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.livingworld.R;
import com.livingworld.clients.ApiUtils;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.master.MasterService;
import com.livingworld.clients.master.model.Master;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.member.model.Member;
import com.livingworld.clients.model.Response;
import com.livingworld.util.BaseActivity;
import com.livingworld.util.PartUtils;
import com.livingworld.util.Preferences;
import com.livingworld.util.Static;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    List<Master> religionMasters = new ArrayList<>();
    List<Master> cityMasters = new ArrayList<>();
    List<Master> materialStatusMasters = new ArrayList<>();
    List<Master> genderMasters = new ArrayList<>();
    List<Master> nationalityMasters = new ArrayList<>();

    Member member = null;

    private static final int CODE_CAMERA = 3321;
    private static final int CODE_GALLERY = 3322;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        ButterKnife.bind(this);

        masterService = ApiUtils.MasterService(getApplicationContext());
        memberService = ApiUtils.MemberService(getApplicationContext());

        Glide.with(this).load("https://scontent-sit4-1.cdninstagram.com/t51.2885-19/s150x150/22158665_821785821334849_4414678351050964992_n.jpg").apply(new RequestOptions().centerCrop()).into(ivProfile);

        user = Preferences.getUser(getApplicationContext());
//        initData(user);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        initTglLahir();
        initGetDetail();
        initChangePicture();
    }

    private void initChangePicture() {
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImageProfile();
            }
        });
    }

    private void initGetDetail() {
        memberService.detail().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    Response body = response.body();
                    if(body != null && body.getData() != null){
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.toJsonTree(response.body()).getAsJsonObject();
                        member = gson.fromJson(jsonObject.get("data"), Member.class);
                        etName.setText(member.getFullName());
                        etName.setSelection(member.getFullName().length());
//                        etName.setText(member.getKtpNo());
                        etBod.setText(member.getDateOfBirth());
                        etAddress.setText(member.getAddress());
                        etZipCode.setText(String.valueOf(member.getZipCode()));
                        etHomePhone.setText(member.getHomePhone());
                        initMasters();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

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
                                etBod.setText(String.valueOf(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth));
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
                    for (int i=0; i < list.size(); i++){
                        Master master = list.get(i);
                        if(master.getId() == member.getReligion()){
                            spReligion.setSelection(i);
                            break;
                        }
                    }
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
                    for (int i=0; i < list.size(); i++){
                        Master master = list.get(i);
                        if(master.getId() == member.getCity()){
                            spCity.setSelection(i);
                            break;
                        }
                    }
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
                    for (int i=0; i < list.size(); i++){
                        Master master = list.get(i);
                        if(master.getId() == member.getMartialStatus()){
                            spMaterialStatus.setSelection(i);
                            break;
                        }
                    }
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
                    for (int i=0; i < list.size(); i++){
                        Master master = list.get(i);
                        if(master.getId() == member.getGender()){
                            spGender.setSelection(i);
                            break;
                        }
                    }
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
                    for (int i=0; i < list.size(); i++){
                        Master master = list.get(i);
                        if(master.getId() == member.getNationality()){
                            spNationality.setSelection(i);
                            break;
                        }
                    }
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
