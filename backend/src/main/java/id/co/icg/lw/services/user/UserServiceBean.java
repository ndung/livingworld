package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean implements UserService {


    @Override
    public boolean checkCardNumber(String phoneNumber) {
        return false;
    }

    @Override
    public boolean checkOTP(String otp) {
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }

    @Override
    public User register(RequestNewUser request) {
        return null;
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        return null;
    }

    @Override
    public User login(RequestLogin request) {
        return null;
    }

    @Override
    public User edit(RequestEditUser request) {
        return null;
    }

    @Override
    public User paymentRegistration(RequestNewUser requestNewUser) {
        return null;
    }
}
