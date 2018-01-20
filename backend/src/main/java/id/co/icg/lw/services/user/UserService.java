package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {
    int checkCardNumber(String cardNumber);
    User register(String userId, RegisterRequest request);
    User signUp(SignUpRequest signUpRequest);
    User signIn(SignInRequest request) throws Exception;
    User edit(String usrId, EditUserRequest request);
    User paymentRegistration(RegisterRequest registerRequest);
    User uploadPhotoProfile(String userId, MultipartFile multipartFile);
}
