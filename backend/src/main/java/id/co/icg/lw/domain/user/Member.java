package id.co.icg.lw.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.icg.lw.domain.master.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="member")
public class Member {

    @OneToOne @JsonIgnore
    @JoinColumn(name="user_id")
    User user;

    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "identity_name")
    private String identityName;

    @Column(name = "idenitity_number")
    private String idenitityNumber;

    private String religion;
    private String gender;
    private String address;
    private String city;
    private String zipcode;
    private String nationalitly;
    private String email;

    @Column(name = "martial_status")
    private String martialStatus;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "member_type")
    private String memberType;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", updatable = false)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;

    public Member() {}

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getIdenitityNumber() {
        return idenitityNumber;
    }

    public void setIdenitityNumber(String idenitityNumber) {
        this.idenitityNumber = idenitityNumber;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalitly() {
        return nationalitly;
    }

    public void setNationalitly(String nationalitly) {
        this.nationalitly = nationalitly;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }

    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    @PrePersist
    protected void onCreate() {
        createAt = updateAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = new Date();
    }

    @Transient
    private List<MemberType> memberTypes;

    public void setMemberTypes(List<MemberType> memberTypes) {
        this.memberTypes = memberTypes;
    }

    @Transient
    private List<Religion> religions;

    public void setReligions(List<Religion> religions) {
        this.religions = religions;
    }

    @Transient
    private List<Gender> genders;

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    @Transient
    private List<MaritalStatus> maritalStatuses;

    public void setMaritalStatuses(List<MaritalStatus> maritalStatuses) {
        this.maritalStatuses = maritalStatuses;
    }

    @Transient
    private List<Nationality> nationalities;


    public void setNationalities(List<Nationality> nationalities) {
        this.nationalities = nationalities;
    }

    @Transient
    private List<City> cities;

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Transient
    private String currentMonthTransaction;

    @Transient
    private String points;

    @Transient
    private String luckyDraws;

    public void setCurrentMonthTransaction(String currentMonthTransaction) {
        this.currentMonthTransaction = currentMonthTransaction;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setLuckyDraws(String luckyDraws) {
        this.luckyDraws = luckyDraws;
    }

    public String getCurrentMonthTransaction() {
        return currentMonthTransaction;
    }

    public String getPoints() {
        return points;
    }

    public String getLuckyDraws() {
        return luckyDraws;
    }

    public List<MemberType> getMemberTypes() {
        return memberTypes;
    }

    public List<Religion> getReligions() {
        return religions;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public List<MaritalStatus> getMaritalStatuses() {
        return maritalStatuses;
    }

    public List<Nationality> getNationalities() {
        return nationalities;
    }

    public List<City> getCities() {
        return cities;
    }
}
