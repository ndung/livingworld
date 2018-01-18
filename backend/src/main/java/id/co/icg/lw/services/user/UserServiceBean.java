package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.UserRepository;
import id.co.icg.lw.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int checkCardNumber(String cardNumber) {
        User user = userRepository.findByCardNumber(cardNumber);
        if (user != null) {
            return 1;
        } else if (checkCardNumberToIfabula(cardNumber)) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public User register(RequestNewUser request) {
        return null;
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setCardNumber(signUpRequest.getCardNumber());
        user.setPassword(PasswordUtil.md5(signUpRequest.getPassword()));
        user.setRole(RoleEnum.USER);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User signIn(SignInRequest request) {
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

    private boolean checkCardNumberToIfabula(String cardNumber) {
        return cardNumber.equals("88776655");
    }
}
