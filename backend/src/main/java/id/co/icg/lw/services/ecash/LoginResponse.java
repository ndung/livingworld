package id.co.icg.lw.services.ecash;

public class LoginResponse {
    public String status;
    public String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status='" + status + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
