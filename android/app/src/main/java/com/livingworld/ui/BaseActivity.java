package com.livingworld.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.livingworld.util.Static;

/**
 * Created by Dizzay on 1/4/2018.
 */

public class BaseActivity extends AppCompatActivity {

    private static Context context;
    private MaterialDialog materialDialogPleasewait;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        materialDialogPleasewait = new MaterialDialog.Builder(BaseActivity.this)
                .content(Static.DIALOG_PLEASEWAIT_TITLE)
                .progress(true, 0)
                .build();
    }

    public void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showSnackbar(String message){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    public void showActivity(Context packageContext, Class<?> cls) {
        Intent intent = getIntent(packageContext, cls);
        showActivity(intent);
    }

    public Intent getIntent(Context packageContext, Class<?> cls) {
        return new Intent(packageContext, cls);
    }

    public void showActivity(Intent intent) {
        startActivity(intent);
//        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
//        overridePendingTransition(R.anim.push_out_right, R.anim.pull_in_left);
    }

    public void showActivityAndFinishCurrentActivity(Context packageContext, Class<?> cls) {
        Intent intent = getIntent(packageContext, cls);
        showActivity(intent);
        finish();
    }

    public void showPleasewaitDialog(){
        materialDialogPleasewait.show();
    }

    public void dissmissPleasewaitDialog(){
        if(materialDialogPleasewait != null){
            materialDialogPleasewait.dismiss();
        }
    }

    public void authenticationFailed(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
