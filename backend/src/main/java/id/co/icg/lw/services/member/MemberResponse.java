package id.co.icg.lw.services.member;

import id.co.icg.lw.domain.user.Member;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberResponse {
    private String fullName;
    private String ktpNo;
    private String religion;
    private String gender;
    private String martialStatus;
    private String dateOfBirth;
    private String nationality;
    private String address;
    private String city;
    private String zipCode;
    private String homePhone;

    public MemberResponse(Member member) {
        setFullName(member.getIdentityName());
        setAddress(member.getAddress());
        setCity(member.getCity());
        setDateOfBirth(member.getDateOfBirth());
        setGender(member.getGender());
        setKtpNo(member.getIdenitityNumber());
        setReligion(member.getReligion());
        setNationality(member.getNationalitly());
        setZipCode(member.getZipcode());
        setHomePhone(member.getHomePhone());
        setMartialStatus(member.getMartialStatus());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKtpNo() {
        return ktpNo;
    }

    public void setKtpNo(String ktpNo) {
        this.ktpNo = ktpNo;
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

    public String getDateOfBirth() {
        if (dateOfBirth != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(dateOfBirth);
        } else {
            return null;
        }
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
}
