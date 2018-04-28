package id.co.icg.lw.api.indiePay;

import id.co.icg.lw.api.ecash.TransactionHistoryResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IndiePayApi {

    @POST("login")
    Call<LoginResponse> login(@Body RequestLogin requestLogin);

    @POST("logout")
    Call<StatusResponse> logout(@Body Token token);

    @POST("otp-request")
    Call<OtpResponse> getOtp(@Body OtpRequest otpRequest);

    @POST("register-service")
    Call<RegisterServiceResponse> registerService(@Body RegisterServiceRequest request);

    @GET("get-list-question")
    Call<QuestionList> getQuestionList();

    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @POST("balance-inquiry")
    Call<BalanceInquiryResponse> getBalanceInquiry(@Body BalanceInquiryRequest request);

    @POST("account-history")
    Call<TransactionHistoryResponse> getAccountHistory(@Body TransactionHistoryRequest request);

    @POST("trx-status")
    Call<StatusInquiryResponse> getStatusInquiry(@Body StatusInquiryRequest request);

}
