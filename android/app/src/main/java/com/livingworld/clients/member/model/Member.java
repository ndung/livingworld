package com.livingworld.clients.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.livingworld.clients.master.model.City;
import com.livingworld.clients.master.model.Gender;
import com.livingworld.clients.master.model.MaritalStatus;
import com.livingworld.clients.master.model.MemberType;
import com.livingworld.clients.master.model.Nationality;
import com.livingworld.clients.master.model.Religion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dizzay on 1/30/2018.
 */

public class Member implements Serializable{

    private String cardNumber;
    private String identityName;
    private String idenitityNumber;
    private String religion;
    private String gender;
    private String martialStatus;
    private String address;
    private String city;
    private String zipcode;
    private String homePhone;
    private String memberType;
    private String nationalitly;
    private String dateOfBirth;
    private String email;
    private String mobileNumber;

    private List<MemberType> memberTypes;
    private List<Religion> religions;
    private List<Gender> genders;
    private List<MaritalStatus> maritalStatuses;
    private List<Nationality> nationalities;
    private List<City> cities;


    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

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

    public String getNationalitly() {
        return nationalitly;
    }

    public void setNationalitly(String nationalitly) {
        this.nationalitly = nationalitly;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }

    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public List<MemberType> getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(List<MemberType> memberTypes) {
        this.memberTypes = memberTypes;
    }

    public List<Religion> getReligions() {
        return religions;
    }

    public void setReligions(List<Religion> religions) {
        this.religions = religions;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public List<MaritalStatus> getMaritalStatuses() {
        return maritalStatuses;
    }

    public void setMaritalStatuses(List<MaritalStatus> maritalStatuses) {
        this.maritalStatuses = maritalStatuses;
    }

    public List<Nationality> getNationalities() {
        return nationalities;
    }

    public void setNationalities(List<Nationality> nationalities) {
        this.nationalities = nationalities;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Member{" +
                "cardNumber='" + cardNumber + '\'' +
                ", identityName='" + identityName + '\'' +
                ", idenitityNumber='" + idenitityNumber + '\'' +
                ", religion='" + religion + '\'' +
                ", gender='" + gender + '\'' +
                ", martialStatus='" + martialStatus + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", memberType='" + memberType + '\'' +
                ", nationalitly='" + nationalitly + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }

    private String currentMonthTransaction;
    private String points;
    private String luckyDraws;

    public String getCurrentMonthTransaction() {
        return currentMonthTransaction;
    }

    public void setCurrentMonthTransaction(String currentMonthTransaction) {
        this.currentMonthTransaction = currentMonthTransaction;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getLuckyDraws() {
        return luckyDraws;
    }

    public void setLuckyDraws(String luckyDraws) {
        this.luckyDraws = luckyDraws;
    }
}
