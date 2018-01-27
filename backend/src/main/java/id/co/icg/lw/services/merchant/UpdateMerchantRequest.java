package id.co.icg.lw.services.merchant;

public class UpdateMerchantRequest extends CreateMerchantRequest{
    private long merchantId;

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }
}
