package id.co.icg.lw.api.ecash;

import id.co.icg.lw.services.ecash.*;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ECashApi {

    @FormUrlEncoded
    @POST("SessionManager/Login")
    Call<LoginResponse> login(@Field("username") String username);

    @FormUrlEncoded
    @POST("SessionManager/Logout")
    Call<LogoutResponse> logout(@Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/ValidateAccount")
    Call<ValidateResponse> validateAccount(@Field("msisdn") String msisdn, @Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/getRegisterTicket")
    Call<TicketResponse> getRegisterTicket(@Field("mobileno") String msisdn, @Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/getRegisterTicket")
    Call<BalanceInquiryResponse> getBalanceInquiry(@Field("msisdn") String msisdn,
                                                   @Field("accountId") String accountId,
                                                   @Field("token") String token);

    @FormUrlEncoded
    @POST("NonTransactionManager/TransactionHistory")
    Call<TransactionHistoryResponse> getTransactionHistory(@Field("msisdn") String msisdn,
                                                       @Field("token") String token,
                                                       @Field("currentPage") int currentPage,
                                                       @Field("pageSize") int pageSize,
                                                       @Field("accountID") String accountId,
                                                       @Field("startDate") String startDate,
                                                       @Field("endDate") String endDate);

    @FormUrlEncoded
    @POST("NonTransactionManager/Status")
    Call<StatusInquiryResponse> getStatusInquiry(@Field("token") String token,
                                                  @Field("amount") String amount,
                                                  @Field("accountTypeId") String accountTypeId,
                                                  @Field("traceNumber") String traceNumber);

    @FormUrlEncoded
    @POST("TransactionManager/Charge")
    Call<ChargeResponse> charge(@Field("msidn") String msisdn,
                                       @Field("amount") String amount,
                                       @Field("traceNumber") String traceNumber,
                                       @Field("charge_transferTypeID") String chargeTransferTypeId,
                                       @Field("token") String token);

    @FormUrlEncoded
    @POST("TransactionManager/Credit")
    Call<CreditResponse> credit(@Field("msidn") String msisdn,
                                @Field("amount") String amount,
                                @Field("traceNumber") String traceNumber,
                                @Field("charge_transferTypeID") String chargeTransferTypeId,
                                @Field("token") String token);


}
