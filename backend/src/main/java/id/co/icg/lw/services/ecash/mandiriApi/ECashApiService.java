package id.co.icg.lw.services.ecash.mandiriApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ECashApiService {

    @FormUrlEncoded
    @POST("SessionManager/Login")
    Call<LoginResponse> login(@Field("username") String username);

    @FormUrlEncoded
    @POST("SessionManager/Logout")
    Call<LogoutResponse> logout(@Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/ValidateAccount")
    Call<ValidateResponse> validate(@Field("msisdn") String msisdn, @Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/getRegisterTicket")
    Call<TicketResponse> getRegisterTicket(@Field("mobileno") String msisdn, @Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/getRegisterTicket")
    Call<BalanceInquiryResponse> getBalanceInquiry(@Field("msisdn") String msisdn, @Field("accountId") String accountId, @Field("token") String token);

    

}
