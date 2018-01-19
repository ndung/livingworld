package com.livingworld.util;

import android.arch.lifecycle.BuildConfig;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.password;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class ChipperUtils {

    public static String getPublicKey(String cardNumber, String pwd){
        Date date = new Date();
        String dateNow = new SimpleDateFormat("YYYYMMDD").format(date);
        String data = cardNumber+getMD5Hash(pwd)+dateNow+ BuildConfig.APPLICATION_ID;
        return getMD5Hash(data);
    }

    public static String getMD5Hash(String value){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());

            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
