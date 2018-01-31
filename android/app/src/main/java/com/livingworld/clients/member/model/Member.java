package com.livingworld.clients.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dizzay on 1/30/2018.
 */

public class Member {

    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("ktpNo")
    @Expose
    private String ktpNo;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("religion")
    @Expose
    private Integer religion;
    @SerializedName("martialStatus")
    @Expose
    private Integer martialStatus;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private Integer city;
    @SerializedName("zipCode")
    @Expose
    private Integer zipCode;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getReligion() {
        return religion;
    }

    public void setReligion(Integer religion) {
        this.religion = religion;
    }

    public Integer getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(Integer martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
}
