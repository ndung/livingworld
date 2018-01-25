package com.livingworld.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.livingworld.clients.auth.model.User;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class Preferences {

    public static void putString(Context context, String key, String value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value).commit();
    }

    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value).commit();
    }

    public static void putInt(Context context, String key, int value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value).commit();
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        return preferences.edit();
    }

    public static String getString(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static boolean getBoolean(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static int getInt(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public static String getToken(Context context){
        return getString(context, Static.TOKEN);
    }

    public static void setToken(Context context, String token){
        putString(context, Static.TOKEN, token);
    }

    public static String getCardNumber(Context context){
        return getString(context, Static.CARD_NUMBER);
    }

    public static void setCardNumber(Context context, String token){
        putString(context, Static.CARD_NUMBER, token);
    }

    public static String getPublicKey(Context context){
        return getString(context, Static.PUBLIC_KEY);
    }

    public static void setPublicKey(Context context, String token){
        putString(context, Static.PUBLIC_KEY, token);
    }

    public static User getUser(Context context){
        try{
            String json = getString(context, Static.USER_DATA);
            return new Gson().fromJson(json, User.class);
        }catch (Exception e){
            return null;
        }
    }

    public static void setUser(Context context, User user){
        putString(context, Static.USER_DATA, new Gson().toJson(user));
    }

    public static void setLoginFlag(Context context, boolean flag){
        putBoolean(context, Static.LOGIN_KEY, flag);
    }

    public static boolean getLoginFlag(Context context){
        return getBoolean(context, Static.LOGIN_KEY);
    }

}
