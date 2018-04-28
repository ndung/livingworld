package id.co.icg.lw.api.ecash;

public class AccountHistoryDetail {
    private String parentTransactionNumber;
    private String status;
    private String transferType;
    private String transferTypeID;
    private String transactionDate;
    private String amount;
    private String usename;
    private String traceNumber;
    private String privateFields;

    public String getParentTransactionNumber() {
        return parentTransactionNumber;
    }

    public void setParentTransactionNumber(String parentTransactionNumber) {
        this.parentTransactionNumber = parentTransactionNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferTypeID() {
        return transferTypeID;
    }

    public void setTransferTypeID(String transferTypeID) {
        this.transferTypeID = transferTypeID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public String getPrivateFields() {
        return privateFields;
    }

    public void setPrivateFields(String privateFields) {
        this.privateFields = privateFields;
    }
}
