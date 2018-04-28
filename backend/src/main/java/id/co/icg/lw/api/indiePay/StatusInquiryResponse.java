package id.co.icg.lw.api.indiePay;

public class StatusInquiryResponse {
    private String token;
    private String traceNumber;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }
}
