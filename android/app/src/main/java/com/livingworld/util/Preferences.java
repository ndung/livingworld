package com.livingworld.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.livingworld.clients.auth.model.User;
import com.livingworld.clients.rewards.model.Reward;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


    public static void setRedeems(Context context, Map<Reward,String> map){
        SharedPreferences.Editor editor = getEditor(context);
        Gson gson =  new Gson();
        LinkedTreeMap<String,String> linkedTreeMap = new LinkedTreeMap<String,String>();
        for (Reward key : map.keySet()){
            linkedTreeMap.put(gson.toJson(key),map.get(key));
        }
        String json = gson.toJson(linkedTreeMap);
        editor.putString(Static.REDEEM_DATA, json);
        editor.commit();
    }

    public static Map<Reward,String> getRedeems(Context context){
        LinkedTreeMap<Reward,String> linkedTreeMap = new LinkedTreeMap<Reward,String>();
        SharedPreferences pref = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        try {
            Gson gson = new Gson();
            String json = pref.getString(Static.REDEEM_DATA, "");
            Map map = gson.fromJson(json, Map.class);
            for (Object key : map.keySet()){
                linkedTreeMap.put(gson.fromJson((String) key, Reward.class), (String) map.get(key));
            }
        }catch(Exception ex){
            //return null;
        }
        return linkedTreeMap;
    }

    private static final String TAG = Preferences.class.toString();

    public static boolean isMessageRead(String inboxId, Context context){
        SharedPreferences pref = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        try {
            Gson gson = new Gson();
            String json = pref.getString(Static.READ_INBOX, "");
            List<String> list = gson.fromJson(json, List.class);
            if (list!=null && list.contains(inboxId)){
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void addReadMessages(String inboxId, Context context){
        SharedPreferences pref = context.getSharedPreferences(Static.MyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = getEditor(context);
        try {
            Gson gson = new Gson();
            String json = pref.getString(Static.READ_INBOX, "");
            List<String> list = gson.fromJson(json, List.class);
            if (list==null){
                list = new ArrayList<>();
            }
            if (!list.contains(inboxId)) {
                list.add(inboxId);
            }
            json = gson.toJson(list);

            editor.putString(Static.READ_INBOX, json);
            editor.commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
