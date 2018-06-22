package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    int checkCardNumber(String cardNumber);
    User register(UserRequest request);
    User signUp(SignUpRequest signUpRequest) throws Exception;
    User signIn(SignInRequest request) throws Exception;
    User edit(String userId, UpdateMemberRequest request) throws Exception;
    User paymentRegistration(UserRequest userRequest);
    User uploadPhotoProfile(String userId, MultipartFile multipartFile);
    User changePassword(String userId, ChangePasswordRequest request) throws Exception;
    List<User> findAll();

    User findOne(String userId);
}
