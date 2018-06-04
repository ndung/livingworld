package id.co.icg.lw.domain.transaction;

import java.util.ArrayList;

public class History {
    private String id;
    private String merchant;
    private String amount;
    private String sourceOfFund;
    private String time;
    private String points;
    private ArrayList luckyDraws;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public ArrayList getLuckyDraws() {
        return luckyDraws;
    }

    public void setLuckyDraws(ArrayList luckyDraws) {
        this.luckyDraws = luckyDraws;
    }
}
