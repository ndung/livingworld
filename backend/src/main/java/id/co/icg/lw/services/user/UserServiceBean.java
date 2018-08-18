package id.co.icg.lw.services.user;

import com.google.gson.internal.LinkedTreeMap;
import id.co.icg.lw.api.livingWorld.CreateMemberRequest;
import id.co.icg.lw.api.livingWorld.CreateMemberResponse;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public User register(UserRequest request) {
        //User user = new User();
        //user.setFullName(request.getFullName());
        //user.setEmail(request.getEmail());
        //user.setDateOfBirth(request.getDateOfBirth());
        //user.setMobileNumber(request.getMobileNumber());


        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setMemberType(0);
        createMemberRequest.setBirthOfDate(dateFormatter.format(request.getDateOfBirth()));
        createMemberRequest.setEmail(request.getEmail());
        createMemberRequest.setMobilePhoneNumber(request.getMobileNumber());
        createMemberRequest.setName(request.getFullName());
        //createMemberRequest.setTime(dateTimeFormatter.format(new Date()));
        createMemberRequest.setIdNumber("1234");
        createMemberRequest.setGender("M");


        try {
            String cardNumber = livingWorldApiService.createMember(createMemberRequest);
            User user = this.checkCardNumberToIfabula(cardNumber);
            user.setPassword(PasswordUtil.md5Hash(request.getPassword()));
            userRepository.saveAndFlush(user);
            memberRepository.saveAndFlush(user.getMember());
            return user;
        } catch (Exception ex) {
            logger.error("error", ex);
        }

        return null;
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) throws Exception {
        User user = userRepository.findByCardNumber(signUpRequest.getCardNumber());
        if (user != null) {
            throw new Exception("Card Number is already registered");
        }

        try {
            user = checkCardNumberToIfabula(signUpRequest.getCardNumber());
            if (user != null) {
                user.setPassword(PasswordUtil.md5Hash(signUpRequest.getPassword()));
                System.out.println(user);
                userRepository.saveAndFlush(user);
                memberRepository.saveAndFlush(user.getMember());
                return user;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public User signIn(SignInRequest request) throws Exception {
        User user = userRepository.findByCardNumber(request.getCardNumber());
        if (user == null) {
            throw new Exception("User is not found");
        }
        if (!PasswordUtil.checkPublicKey(request.getPublicKey(), user.getCardNumber(), user.getPassword())) {
            throw new Exception("CardNumber or password is invalid");
        }
        User updatedUser = checkCardNumberToIfabula(request.getCardNumber());
        updatedUser.setPassword(user.getPassword());
        if (updatedUser != null) {
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
        updatedUser.setPassword(user.getPassword());
        if (updatedUser != null) {
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
        System.out.println("photoUrl:" + photoUrl);
        User user = userRepository.findOne(userId);
        user.setPhotoProfileUrl(photoUrl);
        //userRepository.save(user);
        User updatedUser = checkCardNumberToIfabula(user.getCardNumber());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPhotoProfileUrl(photoUrl);
        if (updatedUser != null) {
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
        user.setPassword(PasswordUtil.md5Hash(request.getNewPassword()));
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
            List<LinkedTreeMap> list = (List<LinkedTreeMap>) livingWorldApiService.getMember(cardNumber);
            if (list != null && !list.isEmpty()) {
                for (LinkedTreeMap map : list) {

                    Member member = new Member();

                    String userId = (String) map.get("cardId");
                    String cardNo = (String) map.get("cardNumber");
                    String name = (String) map.get("name");

                    String memberType = (String) map.get("memberType");
                    MemberType memberTypeObj = memberTypeRepository.findOne(memberType);
                    if (memberTypeObj == null) {
                        List<LinkedTreeMap> memberTypes = (List<LinkedTreeMap>) livingWorldApiService.getMemberType();
                        for (LinkedTreeMap memberTypeMap : memberTypes) {
                            MemberType obj = new MemberType((String) memberTypeMap.get("id"), (String) memberTypeMap.get("name"));
                            obj.setMinimumTransaction((String) memberTypeMap.get("minimumTransaction"));
                            memberTypeRepository.save(obj);
                        }
                    }
                    member.setMemberTypes(memberTypeRepository.findAll());

                    String idNumber = (String) map.get("idNumber");

                    String religion = (String) map.get("religion");
                    Religion religionObj = religionRepository.findOne(religion);
                    if (religionObj == null) {
                        List<LinkedTreeMap> religions = (List<LinkedTreeMap>) livingWorldApiService.getReligion();
                        for (LinkedTreeMap religionMap : religions) {
                            Religion obj = new Religion((String) religionMap.get("id"), (String) religionMap.get("name"));
                            religionRepository.save(obj);
                        }
                    }
                    member.setReligions(religionRepository.findAll());

                    String gender = (String) map.get("gender");
                    Gender genderObj = genderRepository.findOne(gender);
                    if (genderObj == null) {
                        List<LinkedTreeMap> genders = (List<LinkedTreeMap>) livingWorldApiService.getGender();
                        for (LinkedTreeMap genderMap : genders) {
                            Gender obj = new Gender((String) genderMap.get("id"), (String) genderMap.get("name"));
                            genderRepository.save(obj);
                        }
                    }
                    member.setGenders(genderRepository.findAll());

                    String maritalStatus = (String) map.get("maritalStatus");
                    MaritalStatus maritalStatusObj = maritalStatusRepository.findOne(maritalStatus);
                    if (maritalStatusObj == null) {
                        List<LinkedTreeMap> maritalStatuses = (List<LinkedTreeMap>) livingWorldApiService.getMartialStatus();
                        for (LinkedTreeMap maritalStatusMap : maritalStatuses) {
                            MaritalStatus obj = new MaritalStatus((String) maritalStatusMap.get("id"), (String) maritalStatusMap.get("name"));
                            maritalStatusRepository.save(obj);
                        }
                    }
                    member.setMaritalStatuses(maritalStatusRepository.findAll());

                    String birthOfDate = (String) map.get("birthOfDate");

                    String nationality = (String) map.get("nationality");
                    Nationality nationalityObj = nationalityRepository.findOne(nationality);
                    if (nationalityObj == null) {
                        List<LinkedTreeMap> nationalities = (List<LinkedTreeMap>) livingWorldApiService.getNationality();
                        for (LinkedTreeMap nationalityMap : nationalities) {
                            Nationality obj = new Nationality((String) nationalityMap.get("id"), (String) nationalityMap.get("name"));
                            nationalityRepository.save(obj);
                        }
                    }
                    member.setNationalities(nationalityRepository.findAll());

                    String address = (String) map.get("address");

                    String city = (String) map.get("city");
                    City cityObj = cityRepository.findOne(city);
                    System.out.println("cityObj:" + cityObj);
                    if (cityObj == null) {
                        List<LinkedTreeMap> cities = (List<LinkedTreeMap>) livingWorldApiService.getCity();
                        for (LinkedTreeMap cityMap : cities) {
                            City obj = new City((String) cityMap.get("id"), (String) cityMap.get("name"));
                            cityRepository.save(obj);
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

                    User user = new User();
                    user.setUserId(userId);
                    user.setCardNumber(cardNumber);
                    user.setEmail(email);
                    user.setFullName(name);
                    user.setDateOfBirth(dateFormatter.parse(birthOfDate));
                    user.setMobileNumber(mobilePhoneNumber);
                    user.setRole(RoleEnum.USER);

                    member.setCardNumber(cardNo);
                    member.setAddress(address);
                    member.setCity(city);
                    member.setDateOfBirth(birthOfDate);
                    member.setGender(gender);
                    member.setHomePhone(homePhoneNumber);
                    member.setIdenitityNumber(idNumber);
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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
