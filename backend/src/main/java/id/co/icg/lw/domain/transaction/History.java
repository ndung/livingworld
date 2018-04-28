package id.co.icg.lw.domain.transaction;

public class History {
    private String id;
    private String merchant;
    private String amount;
    private String sourceOfFund;
    private String time;
    private int points;
    private String[] luckyDraws;

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String[] getLuckyDraws() {
        return luckyDraws;
    }

    public void setLuckyDraws(String[] luckyDraws) {
        this.luckyDraws = luckyDraws;
    }
}
