package id.co.icg.lw.api.ecash;

public class StatusInquiryResponse {
    private String traceNumber;
    private String status;

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
