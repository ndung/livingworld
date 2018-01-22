package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserResponse {
    private String fullName;
    private String email;
    private String mobileNumber;
    private Date dateOfBirth;
    private String photoProfileUrl;

    public UserResponse() {}

    public UserResponse(User user) {
        setFullName(user.getFullName());
        setEmail(user.getEmail());
        setDateOfBirth(user.getDateOfBirth());
        setMobileNumber(user.getMobileNumber());
        user.getPhotoProfileUrl();
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
        if (dateOfBirth != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            return sdf.format(dateOfBirth);
        } else {
            return null;
        }
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhotoProfileUrl() {
        return "/files/" + photoProfileUrl;
    }

    public void setPhotoProfileUrl(String photoProfileUrl) {
        this.photoProfileUrl = photoProfileUrl;
    }
}
