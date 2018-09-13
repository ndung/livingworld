package id.co.icg.ie.dao.model.app;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
public class Member {

    private User user;
    private String cardNumber;
    private String address;
    private City city;
    private Date createAt;
    private String dateOfBirth;
    private String email;
    private Gender gender;
    private String homePhoneNumber;
    private String identityNumber;
    private String identityName;
    private MaritalStatus maritalStatus;
    private MemberType memberType;
    private String mobileNumber;
    private Nationality nationality;
    private Religion religion;
    private Date updateAt;
    private String zipCode;

    @Id
    @Column(name = "card_number", unique = true, nullable = false)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="city")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "create_at", nullable = false)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Column(name = "date_of_birth", nullable = false)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "home_phone", nullable = false)
    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    @Column(name = "idenitity_number", nullable = false)
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Column(name = "identity_name", nullable = false)
    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="martial_status")
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="member_type")
    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @Column(name = "mobile_number", nullable = false)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="nationalitly")
    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="religion")
    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    @Column(name = "update_at", nullable = false)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Column(name = "zipcode", nullable = false)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
