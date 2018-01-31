package com.livingworld.clients.auth.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class User implements Serializable{

    @SerializedName("id")
    String id;
    @SerializedName("fullName")
    String fullName;
    @SerializedName("email")
    String email;
    @SerializedName("mobileNumber")
    String mobileNumber;
    @SerializedName("dateOfBirth")
    String dateOfBirth;
    @SerializedName("photoProfileUrl")
    String photoProfileUrl;
    int gender;
    int martialStatus;
    int nationality;
    String address;
    int city;
    int zipCode;
    String homeNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhotoProfileUrl() {
        return photoProfileUrl;
    }

    public void setPhotoProfileUrl(String photoProfileUrl) {
        this.photoProfileUrl = photoProfileUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(int martialStatus) {
        this.martialStatus = martialStatus;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }
}
