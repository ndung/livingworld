package id.co.icg.lw.services.ecash;

public interface EcashApiService {

    LoginResponse login() throws Exception;

    LogoutResponse logout(String token) throws Exception;

    ValidateResponse validate(String phoneNumber) throws Exception;

    TicketResponse getTicket(String phoneNumber) throws Exception;

    BalanceInquiryResponse getBalanceInquiry(String userId, String token) throws Exception;

}
