package id.co.icg.lw.api.livingWorld;

public class AddTransactionRequest {
    private String tid;
    private String cardId;
    private String cardNumber;
    private String merchant;
    private String amount;
    private String sourceOfFund;
    private String time;
    private String receiptNo;
    private String description;
    private String memberType;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSourceOfFund() {
        return sourceOfFund;
    }

    public void setSourceOfFund(String sourceOfFund) {
        this.sourceOfFund = sourceOfFund;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCardId() { return cardId; }

    public void setCardId(String cardId) { this.cardId = cardId; }

    public String getReceiptNo() { return receiptNo; }

    public void setReceiptNo(String receiptNo) { this.receiptNo = receiptNo; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getMemberType() { return memberType; }

    public void setMemberType(String memberType) { this.memberType = memberType; }

    @Override
    public String toString() {
        return "AddTransactionRequest{" +
                "tid='" + tid + '\'' +
                ", cardId='" + cardId + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", merchant='" + merchant + '\'' +
                ", amount='" + amount + '\'' +
                ", sourceOfFund='" + sourceOfFund + '\'' +
                ", time='" + time + '\'' +
                ", receiptNo='" + receiptNo + '\'' +
                ", description='" + description + '\'' +
                ", memberType='" + memberType + '\'' +
                '}';
    }
}
