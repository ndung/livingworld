package id.co.icg.lw.services.ecash;

public class ValidateResponse {
    private String msisdn;
    private String status;
    private String groupID;
    private String name;
    private String id;
    private String kyc;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    @Override
    public String toString() {
        return "ValidateResponse{" +
                "msisdn='" + msisdn + '\'' +
                ", status='" + status + '\'' +
                ", groupID='" + groupID + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", kyc='" + kyc + '\'' +
                '}';
    }
}
