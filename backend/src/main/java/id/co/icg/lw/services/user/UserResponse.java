package id.co.icg.lw.services.user;

import id.co.icg.lw.domain.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserResponse {

    private String id;
    private String fullName;
    private String email;
    private String idNumber;
    private String mobileNumber;
    private Date dateOfBirth;
    private String photoProfileUrl;

    public UserResponse() {}

    public UserResponse(User user) {
        setId(user.getUserId());
        setFullName(user.getFullName());
        setEmail(user.getEmail());
        setIdNumber(user.getIdNumber());
        setDateOfBirth(user.getDateOfBirth());
        setMobileNumber(user.getMobileNumber());
        user.getPhotoProfileUrl();
    }

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

    public String getIdNumber() { return idNumber; }

    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

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
