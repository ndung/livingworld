package com.livingworld.clients.auth.model;

import com.google.gson.annotations.SerializedName;
import com.livingworld.clients.member.model.Member;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dizzay on 1/19/2018.
 */

public class User implements Serializable{

    private String userId;
    private String fullName;
    private String email;
    private String mobileNumber;
    //private Date dateOfBirth;
    private String photoProfileUrl;
    private int status;
    private String idNumber;
    private int passwordStatus;

    private Member member;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    /**public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }*/

    public String getPhotoProfileUrl() {
        return photoProfileUrl;
    }

    public void setPhotoProfileUrl(String photoProfileUrl) { this.photoProfileUrl = photoProfileUrl; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getPasswordStatus() { return passwordStatus; }

    public void setPasswordStatus(int passwordStatus) { this.passwordStatus = passwordStatus; }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                //", dateOfBirth=" + dateOfBirth +
                ", photoProfileUrl='" + photoProfileUrl + '\'' +
                ", status='" + status + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", passwordStatus='" + passwordStatus + '\'' +
                ", member=" + member +
                '}';
    }
}
