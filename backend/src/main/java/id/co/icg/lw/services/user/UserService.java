package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;


public interface UserService {
    boolean checkCardNumber(String cardNumber);
    boolean checkOTP(String otp);
    boolean checkEmail(String email);
    User register(RequestNewUser request);
    User signUp(SignUpRequest signUpRequest);
    User login(RequestLogin request);
    User edit(RequestEditUser request);
    User paymentRegistration(RequestNewUser requestNewUser);
}
