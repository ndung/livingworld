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

    private String ecashId;
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

    public void setPhotoProfileUrl(String photoProfileUrl) {
        this.photoProfileUrl = photoProfileUrl;
    }

    public String getEcashId() {
        return ecashId;
    }

    public void setEcashId(String ecashId) {
        this.ecashId = ecashId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                //", dateOfBirth=" + dateOfBirth +
                ", photoProfileUrl='" + photoProfileUrl + '\'' +
                ", ecashId='" + ecashId + '\'' +
                ", member=" + member +
                '}';
    }
}
