package id.co.icg.lw.services.user;

public class ResetPasswordRequest {

    private String number;
    private String email;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
