package id.co.icg.lw.services.ecash;

public interface EcashApiService {

    LoginResponse login() throws Exception;

    LogoutResponse logout(String token) throws Exception;

    ValidateResponse validate(String userId, String token) throws Exception;

    TicketResponse getTicket(String userId, String token) throws Exception;

    BalanceInquiryResponse getBalanceInquiry(String userId, String token) throws Exception;

}
