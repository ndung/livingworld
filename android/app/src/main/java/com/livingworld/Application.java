package com.livingworld;

import com.livingworld.util.Static;
import com.snatik.storage.Storage;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by Dizzay on 1/30/2018.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EasyImage.configuration(this)
                .setImagesFolderName(Static.DIR_IMAGE)
                .saveInAppExternalFilesDir()
                .saveInRootPicturesDirectory();
        Storage storage = new Storage(getApplicationContext());
        String path = storage.getExternalStorageDirectory();
        String pathFile = path + File.separator + Static.DIR_IMAGE;
        if(storage.createDirectory(pathFile)){
            System.out.println("EXISTS : "+pathFile);
        }else{
            System.out.println("Not Exists");
        }
        storage.createDirectory(pathFile);
    }
}
