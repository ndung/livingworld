package id.co.icg.lw.services.user;

import com.google.gson.internal.LinkedTreeMap;
import id.co.icg.lw.api.livingWorld.CreateMemberRequest;
import id.co.icg.lw.domain.master.*;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.*;
import id.co.icg.lw.services.file.FileService;
import id.co.icg.lw.component.PasswordUtil;
import id.co.icg.lw.services.livingWorld.LivingWorldApiService;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class UserServiceBean implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceBean.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberTypeRepository memberTypeRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private LivingWorldApiService livingWorldApiService;

    @Override
    public int checkCardNumber(String cardNumber) {
        User user = userRepository.findByCardNumber(cardNumber);
        if (user != null) {
            return 1;
        } else if (checkCardNumberToIfabula(cardNumber) != null) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int checkIdNumber(String idNumber) {
        User user = userRepository.findByIdNumber(idNumber);
        if (user != null) {
            return 1;
        } else if (checkKtpToIfabula(idNumber) != null) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int resetPassword(ResetPasswordRequest request) {
        User user = null;
        if (request.getNumber().length()>10){
            user = userRepository.findByIdNumber(request.getNumber());
        }else {
            user = userRepository.findByCardNumber(request.getNumber());
        }
        if (user!=null && user.getEmail().equalsIgnoreCase(request.getEmail())) {
            String[] to = new String[]{user.getEmail()};
            try {
                String host = "smtp.gmail.com";
                final String from = "ndung@icg.co.id";
                final String password = "GO816ogh";
                Properties props = System.getProperties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.user", from);
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.password", password);
                props.put("mail.smtp.auth", "true");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));

                InternetAddress[] to_address = new InternetAddress[to.length];
                // To get the array of addresses
                for (int i = 0; i < to.length; i++) {
                    to_address[i] = new InternetAddress(to[i]);
                    message.addRecipient(Message.RecipientType.TO, to_address[i]);
                }
                String newPassword = PasswordUtil.generatePassword();
                message.setSubject("Reset Living World Mobile Apps Password");
                message.setText("Your new password is : " + newPassword);
                Transport.send(message);

                user.setPhotoProfileUrl(user.getPhotoProfileUrl());
                user.setPassword(PasswordUtil.md5Hash(newPassword));
                user.setPasswordStatus(1);
                user.setLoginStatus(user.getLoginStatus());
                user.setDeviceId(user.getDeviceId());
                userRepository.saveAndFlush(user);
                return 1;
            } catch (Exception e) {
                logger.error("error", e);
            }
        }else{
            return -1;
        }
        return 0;
    }

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public User register(UserRequest request) throws Exception {
        User user = userRepository.findByIdNumber(request.getKtp());
        if (user != null) {
            throw new Exception("User is already registered");
        }
        if (!isPasswordValid(request.getPassword(), true, true, 6, 20)) {
            throw new Exception("Password is invalid");
        }
        user = checkKtpToIfabula(request.getKtp());
        if (user == null) {
            CreateMemberRequest createMemberRequest = new CreateMemberRequest();
            createMemberRequest.setMemberType("03");
            createMemberRequest.setBirthOfDate(dateFormatter.format(request.getDateOfBirth()));
            createMemberRequest.setEmail(request.getEmail());
            createMemberRequest.setMobilePhoneNumber(request.getMobileNumber());
            createMemberRequest.setName(request.getFullName());
            //createMemberRequest.setTime(dateTimeFormatter.format(new Date()));
            createMemberRequest.setIdNumber(request.getKtp());
            createMemberRequest.setGender("M");

            try {
                String cardNumber = livingWorldApiService.createMember(createMemberRequest);
                user = this.checkCardNumberToIfabula(cardNumber);
            } catch (Exception ex) {
                logger.error("error", ex);
            }
        }
        user.setPassword(PasswordUtil.md5Hash(request.getPassword()));
        user.setLoginStatus(1);
        user.setDeviceId(request.getDeviceId());
        userRepository.saveAndFlush(user);
        memberRepository.saveAndFlush(user.getMember());
        return user;
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) throws Exception {
        User user = null;
        if (signUpRequest.getCardNumber().length()>10){
            user = userRepository.findByIdNumber(signUpRequest.getCardNumber());
        }else {
            user = userRepository.findByCardNumber(signUpRequest.getCardNumber());
        }
        if (user != null) {
            throw new Exception("Card Number is already registered");
        }
        if (signUpRequest.getCardNumber().length()>10){
            user = checkKtpToIfabula(signUpRequest.getCardNumber());
        }else {
            user = checkCardNumberToIfabula(signUpRequest.getCardNumber());
        }
        if (user != null) {
            if (!isPasswordValid(signUpRequest.getPassword(), true, true, 6, 20)) {
                throw new Exception("Password is invalid");
            }
            user.setPassword(PasswordUtil.md5Hash(signUpRequest.getPassword()));
            user.setLoginStatus(1);
            user.setDeviceId(signUpRequest.getDeviceId());
            userRepository.saveAndFlush(user);
            memberRepository.saveAndFlush(user.getMember());
            return user;
        }

        return null;
    }

    public static boolean isPasswordValid(String password, boolean isDigit, boolean isCase, int minLength, int maxLength) {

        String PASSWORD_PATTERN = "";

        if (isDigit) PASSWORD_PATTERN += "(?=.*\\d)";
        if (isCase) PASSWORD_PATTERN += "(?=.*[a-z])";
        PASSWORD_PATTERN += ".{" + minLength + "," + maxLength + "}";
        PASSWORD_PATTERN = "(" + PASSWORD_PATTERN + ")";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public User signIn(SignInRequest request) throws Exception {
        User user = null;
        if (request.getCardNumber().length()>10){
            user = userRepository.findByIdNumber(request.getCardNumber());
        }else {
            user = userRepository.findByCardNumber(request.getCardNumber());
        }
        if (user == null) {
            throw new Exception("User is not found");
        }
        if (!PasswordUtil.checkPublicKey(request.getPublicKey(), request.getCardNumber(), user.getPassword())) {
            throw new Exception("Password is invalid");
        }
        if (user.getLoginStatus()==1 && user.getDeviceId()!=null && !user.getDeviceId().equals(request.getDeviceId())){
            throw new Exception("User has been logged in another device");
        }
        User updatedUser = checkCardNumberToIfabula(user.getCardNumber());
        if (updatedUser != null) {
            updatedUser.setPhotoProfileUrl(user.getPhotoProfileUrl());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPasswordStatus(user.getPasswordStatus());
            updatedUser.setLoginStatus(1);
            updatedUser.setDeviceId(request.getDeviceId());
            userRepository.saveAndFlush(updatedUser);
            memberRepository.saveAndFlush(updatedUser.getMember());
            return updatedUser;
        }
        return user;
    }

    public User signOut(String userId) throws Exception {
        User user = userRepository.findOne(userId);
        user.setPhotoProfileUrl(user.getPhotoProfileUrl());
        user.setPassword(user.getPassword());
        user.setPasswordStatus(user.getPasswordStatus());
        user.setLoginStatus(0);
        user.setDeviceId(null);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User refreshUserById(String userId) {
        User user = userRepository.findOne(userId);
        User updatedUser = checkKtpToIfabula(user.getIdNumber());
        if (updatedUser != null) {
            updatedUser.setPhotoProfileUrl(user.getPhotoProfileUrl());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPasswordStatus(user.getPasswordStatus());
            updatedUser.setLoginStatus(user.getLoginStatus());
            updatedUser.setDeviceId(user.getDeviceId());
            userRepository.saveAndFlush(updatedUser);
            memberRepository.saveAndFlush(updatedUser.getMember());
            return updatedUser;
        }
        return user;
    }

    @Override
    public User edit(String userId, UpdateMemberRequest request) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception("User is not found");
        }
        livingWorldApiService.updateMember(request);
        User updatedUser = checkCardNumberToIfabula(request.getCardNumber());
        if (updatedUser != null) {
            updatedUser.setPhotoProfileUrl(user.getPhotoProfileUrl());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPasswordStatus(user.getPasswordStatus());
            updatedUser.setLoginStatus(user.getLoginStatus());
            updatedUser.setDeviceId(user.getDeviceId());
            userRepository.saveAndFlush(updatedUser);
            memberRepository.saveAndFlush(updatedUser.getMember());
            return updatedUser;
        }
        return user;
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
        //userRepository.save(user);
        User updatedUser = checkCardNumberToIfabula(user.getCardNumber());
        if (updatedUser != null) {
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPhotoProfileUrl(photoUrl);
            updatedUser.setPasswordStatus(user.getPasswordStatus());
            updatedUser.setLoginStatus(user.getLoginStatus());
            updatedUser.setDeviceId(user.getDeviceId());
            userRepository.saveAndFlush(updatedUser);
            memberRepository.saveAndFlush(updatedUser.getMember());
            return updatedUser;
        }
        return user;
    }

    @Override
    public User changePassword(String userId, ChangePasswordRequest request) throws Exception {
        User user = userRepository.findOne(userId, PasswordUtil.md5Hash(request.getOldPassword()));
        if (user == null) {
            throw new Exception("User is not found");
        }
        if (!isPasswordValid(request.getNewPassword(), true, true, 6, 20)) {
            throw new Exception("Password is invalid");
        }
        user.setPassword(PasswordUtil.md5Hash(request.getNewPassword()));
        user.setPasswordStatus(0);
        userRepository.saveAndFlush(user);
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

    private User checkCardNumberToIfabula(String cardNumber) {
        try {
            return refresh((List<LinkedTreeMap>) livingWorldApiService.getMember(cardNumber));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private User checkKtpToIfabula(String noKtp) {
        try {
            return refresh((List<LinkedTreeMap>) livingWorldApiService.getMemberByNoKtp(noKtp));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private User refresh(List<LinkedTreeMap> list) throws Exception {
        if (list != null && !list.isEmpty()) {
            for (LinkedTreeMap map : list) {

                Member member = new Member();

                String userId = (String) map.get("CustomerUUID");
                String cardNo = (String) map.get("cardNumber");
                String name = (String) map.get("name");

                String memberType = (String) map.get("cardId");
                if (memberType.isEmpty()) {
                    memberType = null;
                } else {
                    MemberType memberTypeObj = memberTypeRepository.findOne(memberType);
                    if (memberTypeObj == null) {
                        List<LinkedTreeMap> memberTypes = (List<LinkedTreeMap>) livingWorldApiService.getMemberType();
                        for (LinkedTreeMap memberTypeMap : memberTypes) {
                            MemberType obj = new MemberType((String) memberTypeMap.get("id"), (String) memberTypeMap.get("name"));
                            obj.setMinimumTransaction((String) memberTypeMap.get("minimumTransaction"));
                            memberTypeRepository.save(obj);
                        }
                    }
                }
                member.setMemberTypes(memberTypeRepository.findAll());

                String idNumber = (String) map.get("idNumber");

                String religion = (String) map.get("religion");
                if (religion.isEmpty()) {
                    religion = null;
                } else {
                    Religion religionObj = religionRepository.findOne(religion);
                    if (religionObj == null) {
                        List<LinkedTreeMap> religions = (List<LinkedTreeMap>) livingWorldApiService.getReligion();
                        for (LinkedTreeMap religionMap : religions) {
                            Religion obj = new Religion((String) religionMap.get("id"), (String) religionMap.get("name"));
                            religionRepository.save(obj);
                        }
                    }
                }
                member.setReligions(religionRepository.findAll());

                String gender = (String) map.get("gender");
                if (gender.isEmpty()) {
                    gender = null;
                } else {
                    Gender genderObj = genderRepository.findOne(gender);
                    if (genderObj == null) {
                        List<LinkedTreeMap> genders = (List<LinkedTreeMap>) livingWorldApiService.getGender();
                        for (LinkedTreeMap genderMap : genders) {
                            Gender obj = new Gender((String) genderMap.get("id"), (String) genderMap.get("name"));
                            genderRepository.save(obj);
                        }
                    }
                }
                member.setGenders(genderRepository.findAll());

                String maritalStatus = (String) map.get("maritalStatus");
                if (maritalStatus.isEmpty()) {
                    maritalStatus = null;
                } else {
                    MaritalStatus maritalStatusObj = maritalStatusRepository.findOne(maritalStatus);
                    if (maritalStatusObj == null) {
                        List<LinkedTreeMap> maritalStatuses = (List<LinkedTreeMap>) livingWorldApiService.getMartialStatus();
                        for (LinkedTreeMap maritalStatusMap : maritalStatuses) {
                            MaritalStatus obj = new MaritalStatus((String) maritalStatusMap.get("id"), (String) maritalStatusMap.get("name"));
                            maritalStatusRepository.save(obj);
                        }
                    }
                }
                member.setMaritalStatuses(maritalStatusRepository.findAll());

                String birthOfDate = (String) map.get("birthOfDate");

                String nationality = (String) map.get("nationality");
                if (nationality.isEmpty()) {
                    nationality = null;
                } else {
                    Nationality nationalityObj = nationalityRepository.findOne(nationality);
                    if (nationalityObj == null) {
                        List<LinkedTreeMap> nationalities = (List<LinkedTreeMap>) livingWorldApiService.getNationality();
                        for (LinkedTreeMap nationalityMap : nationalities) {
                            Nationality obj = new Nationality((String) nationalityMap.get("id"), (String) nationalityMap.get("name"));
                            nationalityRepository.save(obj);
                        }
                    }
                }
                member.setNationalities(nationalityRepository.findAll());

                String address = (String) map.get("address");

                String city = (String) map.get("city");
                if (city.isEmpty()) {
                    city = null;
                } else {
                    City cityObj = cityRepository.findOne(city);
                    if (cityObj == null) {
                        List<LinkedTreeMap> cities = (List<LinkedTreeMap>) livingWorldApiService.getCity();
                        for (LinkedTreeMap cityMap : cities) {
                            City obj = new City((String) cityMap.get("id"), (String) cityMap.get("name"));
                            cityRepository.save(obj);
                        }
                    }
                }
                member.setCities(cityRepository.findAll());

                String zipCode = (String) map.get("zipCode");
                String homePhoneNumber = (String) map.get("homePhoneNumber");
                String mobilePhoneNumber = (String) map.get("mobilePhoneNumber");
                String email = (String) map.get("email");
                String currentMonthTransaction = (String) map.get("currentMonthTransaction");
                String points = (String) map.get("points");
                String luckyDraws = (String) map.get("luckyDraws");
                String status = (String) map.get("memberType");

                User user = new User();
                user.setUserId(userId);
                user.setCardNumber(cardNo);
                user.setIdNumber(idNumber);
                user.setEmail(email);
                user.setFullName(name);
                user.setDateOfBirth(dateFormatter.parse(birthOfDate));
                user.setMobileNumber(mobilePhoneNumber);
                user.setRole(RoleEnum.USER);
                user.setStatus(Integer.parseInt(status));

                member.setCardNumber(cardNo);
                member.setAddress(address);
                member.setCity(city);
                member.setDateOfBirth(birthOfDate);
                member.setGender(gender);
                member.setHomePhone(homePhoneNumber);
                member.setReligion(religion);
                member.setIdentityName(name);
                member.setMartialStatus(maritalStatus);
                member.setNationalitly(nationality);
                member.setZipcode(zipCode);
                member.setEmail(email);
                member.setMobileNumber(mobilePhoneNumber);
                member.setUser(user);
                member.setMemberType(memberType);
                member.setCurrentMonthTransaction(currentMonthTransaction);
                member.setPoints(points);
                member.setLuckyDraws(luckyDraws);

                user.setMember(member);

                return user;
            }
        }
        return null;
    }
}
