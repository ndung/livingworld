package id.co.icg.lw.services.ecash;

public class BalanceInquiryResponse {
    private String accountBalance;
    private String status;
    private String upperCreditLimit;
    private String reservedAmount;

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpperCreditLimit() {
        return upperCreditLimit;
    }

    public void setUpperCreditLimit(String upperCreditLimit) {
        this.upperCreditLimit = upperCreditLimit;
    }

    public String getReservedAmount() {
        return reservedAmount;
    }

    public void setReservedAmount(String reservedAmount) {
        this.reservedAmount = reservedAmount;
    }
}
