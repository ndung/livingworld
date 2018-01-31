package com.livingworld.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.snatik.storage.Storage;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Dizzay on 11/15/2017.
 */

public class PartUtils {

    public static MultipartBody.Part prepareFilePart(String partName, File file) {

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("*"),
                        file
                );

        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public static RequestBody createPartFromString(String string){
        return RequestBody.create(MediaType.parse("text/plain"), string);
    }

    public static String prepareUpload(Context context, File imageFile, String id) {
        Storage storage = new Storage(context);
//
        String path = storage.getExternalStorageDirectory();
        String pathFile = path + File.separator + Static.DIR_IMAGE;
//
//        byte[] bytes = storage.readFile(imageFile.getPath());
//        String nameNewImage = id+ "_" + System.currentTimeMillis() + ".jpg";
//        String nameNewFile = pathFile + "/" + nameNewImage;
//        storage.createFile(nameNewFile, bytes);
//
//        Bitmap original = BmpUtils.decodeFile(new File(nameNewFile), 800, 800);
//        BmpUtils.compress(original, nameNewImage);
//        return nameNewFile;

        byte[] bytes = storage.readFile(imageFile.getPath());
        String nameNewImage = id + "_" + System.currentTimeMillis() + ".jpg";
        String nameNewFile = pathFile + "/" + nameNewImage;
        storage.createFile(nameNewFile, bytes);

        Bitmap original = BmpUtils.decodeFile(new File(nameNewFile), 800, 800);
        BmpUtils.compress(original, nameNewImage);
        return nameNewFile;
    }
}

