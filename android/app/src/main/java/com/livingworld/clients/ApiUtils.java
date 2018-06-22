package com.livingworld.clients;

import android.content.Context;

import com.livingworld.clients.auth.AuthService;
import com.livingworld.clients.car.CarrService;
import com.livingworld.clients.ecash.ECashService;
import com.livingworld.clients.inbox.InboxService;
import com.livingworld.clients.master.MasterService;
import com.livingworld.clients.member.MemberService;
import com.livingworld.clients.merchant.MerchantService;
import com.livingworld.clients.offers.OffersService;
import com.livingworld.clients.rewards.RewardsService;
import com.livingworld.clients.trx.TrxService;
import com.livingworld.util.Static;


public class ApiUtils {

    public static String API = Static.BASE_URL;

    public static AuthService AuthService(Context context){
        return RetrofitClient.getClient(context, API).create(AuthService.class);
    }

    public static InboxService InboxService(Context context){
        return RetrofitClient.getClient(context, API).create(InboxService.class);
    }

    public static MasterService MasterService(Context context){
        return RetrofitClient.getClient(context, API).create(MasterService.class);
    }

    public static MerchantService MerchantService(Context context){
        return RetrofitClient.getClient(context, API).create(MerchantService.class);
    }

    public static MemberService MemberService(Context context){
        return RetrofitClient.getClient(context, API).create(MemberService.class);
    }

    public static RewardsService RewardService(Context context){
        return RetrofitClient.getClient(context, API).create(RewardsService.class);
    }

    public static TrxService TrxService(Context context){
        return RetrofitClient.getClient(context, API).create(TrxService.class);
    }

    public static CarrService CarService(Context context){
        return RetrofitClient.getClient(context, API).create(CarrService.class);
    }

    public static OffersService OffersService(Context context){
        return RetrofitClient.getClient(context, API).create(OffersService.class);
    }

    public static ECashService ECashService(Context context){
        return RetrofitClient.getClient(context, API).create(ECashService.class);
    }
}
