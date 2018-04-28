package id.co.icg.lw.api.livingWorld;

import id.co.icg.lw.domain.IFabulaResponse;
import id.co.icg.lw.services.member.CreateMemberResponse;
import id.co.icg.lw.services.member.UpdateMemberResponse;
import id.co.icg.lw.services.member.CreateMemberRequest;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LivingWorldApi {

    @GET("master")
    Call<IFabulaResponse> getMaster(@Query("type") String type);

    @GET("transaction")
    Call<IFabulaResponse> getTransaction(@Query("type") String type, @Query("card_number") String cardNumber);

    @POST("transaction?type=add")
    Call<AddTransactionResponse> addTransaction(@Body AddTransactionRequest body);

    @POST("transaction?type=redeem_points")
    Call<RedeemPointResponse> redeemPoints(@Body RedeemPointRequest body);

    @GET("member")
    Call<IFabulaResponse> getMember(@Query("type") String type, @Query("card_number") String cardNumber);

    @POST("member?type=create")
    Call<CreateMemberResponse> createMember(@Body CreateMemberRequest body);

    @POST("member?type=update")
    Call<UpdateMemberResponse> updateMember(@Body UpdateMemberRequest body);


}
