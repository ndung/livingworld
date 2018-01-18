package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;


public interface UserService {
    int checkCardNumber(String cardNumber);
    User register(RequestNewUser request);
    User signUp(SignUpRequest signUpRequest);
    User signIn(SignInRequest request);
    User edit(RequestEditUser request);
    User paymentRegistration(RequestNewUser requestNewUser);
}
