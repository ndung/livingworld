package id.co.icg.lw.services.ecash;

public class TrxHistoryResponse {
    private String amount;
    private String username;
    private String parentTransactionNumber;
    private String description;
    private String transferType;
    private String name;
    private String transactionNumber;
    private String transactionDate;
    private String trxDateTime;
    private String transferTypeID;
    private String privateFields;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParentTransactionNumber() {
        return parentTransactionNumber;
    }

    public void setParentTransactionNumber(String parentTransactionNumber) {
        this.parentTransactionNumber = parentTransactionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTrxDateTime() {
        return trxDateTime;
    }

    public void setTrxDateTime(String trxDateTime) {
        this.trxDateTime = trxDateTime;
    }

    public String getTransferTypeID() {
        return transferTypeID;
    }

    public void setTransferTypeID(String transferTypeID) {
        this.transferTypeID = transferTypeID;
    }

    public String getPrivateFields() {
        return privateFields;
    }

    public void setPrivateFields(String privateFields) {
        this.privateFields = privateFields;
    }
}
