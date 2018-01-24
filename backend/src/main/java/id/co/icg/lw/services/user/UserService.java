package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    int checkCardNumber(String cardNumber);
    User register(String userId, UserRequest request);
    User signUp(SignUpRequest signUpRequest) throws Exception;
    User signIn(SignInRequest request) throws Exception;
    User edit(String usrId, EditUserRequest request);
    User paymentRegistration(UserRequest userRequest);
    User uploadPhotoProfile(String userId, MultipartFile multipartFile);

    List<User> findAll();
}
