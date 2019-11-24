package id.co.icg.lw.api.livingWorld;

import id.co.icg.lw.domain.IFabulaResponse;
import id.co.icg.lw.services.member.UpdateMemberResponse;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LivingWorldApi {

    @GET("master")
    Call<IFabulaResponse> getMaster(@Query("type") String type);

    @GET("master")
    Call<IFabulaResponse> getReward(@Query("type") String type, @Query("card") String card);

    @GET("transaction")
    Call<IFabulaResponse> getTransaction(@Query("type") String type, @Query("card_number") String cardNumber);

    @POST("transaction?type=add")
    Call<AddTransactionResponse> addTransaction(@Body AddTransactionRequest body);

    @POST("transaction?type=redeem_points")
    Call<ResponseBody> redeemPoints(@Body RedeemPointRequest body);

    @GET("member?type=read")
    Call<IFabulaResponse> getMember(@Query("card_number") String cardNumber);

    @GET("member?type=read")
    Call<IFabulaResponse> getMemberByNoKtp(@Query("no_ktp") String cardNumber);

    @POST("member?type=create")
    Call<CreateMemberResponse> createMember(@Body CreateMemberRequest body);

    @POST("member?type=update")
    Call<ResponseBody> updateMember(@Body UpdateMemberRequest body);

}
