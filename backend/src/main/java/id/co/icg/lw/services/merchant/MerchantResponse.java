package id.co.icg.lw.services.merchant;

public class MerchantResponse {
    private String merchantId;
    private String merchantName;
    private String merchantLogo;

    public MerchantResponse(id.co.icg.lw.domain.merchant.Merchant merchant) {
        merchantId      = merchant.getMerchantId();
        merchantLogo    = merchant.getMerchantLogo();
        merchantName    = merchant.getMerchantName();
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }
}
