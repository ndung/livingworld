package id.co.icg.lw.api.ecash;

import java.util.List;

public class TransactionHistoryResponse {
    private String status;
    private List<AccountHistoryDetail> accountHistoryDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AccountHistoryDetail> getAccountHistoryDetails() {
        return accountHistoryDetails;
    }

    public void setAccountHistoryDetails(List<AccountHistoryDetail> accountHistoryDetails) {
        this.accountHistoryDetails = accountHistoryDetails;
    }
}
