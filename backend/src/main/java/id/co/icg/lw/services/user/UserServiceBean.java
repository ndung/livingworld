package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.UserRepository;
import id.co.icg.lw.services.file.FileService;
import id.co.icg.lw.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceBean implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

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
    public User register(String userId, UserRequest request) {
        User user = userRepository.findOne(userId);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setMobileNumber(request.getMobileNumber());
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) throws Exception{
        User user = userRepository.findByCardNumber(signUpRequest.getCardNumber());
        if (user != null) {
            throw new Exception("Card Number is already registered");
        }

        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setCardNumber(signUpRequest.getCardNumber());
        user.setPassword(PasswordUtil.md5Hash(signUpRequest.getPassword()));
        user.setRole(RoleEnum.USER);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User signIn(SignInRequest request) throws Exception{
        User user = userRepository.findByCardNumber(request.getCardNumber());
        if (!PasswordUtil.checkPublicKey(request.getPublicKey(), user.getCardNumber(), user.getPassword())) {
            throw new Exception("CardNumber or password is invalid");
        }

        return user;
    }

    @Override
    public User edit(String userId, EditUserRequest request) {
        return register(userId, request);
    }

    @Override
    public User paymentRegistration(UserRequest userRequest) {
        return null;
    }

    @Override
    public User uploadPhotoProfile(String userId, MultipartFile multipartFile) {
        String photoUrl = fileService.upload(multipartFile);
        User user = userRepository.findOne(userId);
        user.setPhotoProfileUrl(photoUrl);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }

    private boolean checkCardNumberToIfabula(String cardNumber) {
        return cardNumber.equals("88776655");
    }


}
