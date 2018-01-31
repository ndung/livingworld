package id.co.icg.lw.services.merchant;

import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantOfficeHour;

import java.util.ArrayList;
import java.util.List;

public class MerchantDetailResponse extends MerchantResponse{
    private String merchantDescription;
    private String merchantImage;
    private String merchantPhone;
    private List<MerchantOfficeHourResponse> merchantOfficeHour;

    public MerchantDetailResponse(Merchant merchant) {
        super(merchant);
        if (merchant.getMerchantOfficeHourList() != null) {
            merchantOfficeHour = new ArrayList<>();
            for (MerchantOfficeHour hour : merchant.getMerchantOfficeHourList()) {
                merchantOfficeHour.add(new MerchantOfficeHourResponse(hour));
            }
        }

        merchantDescription = merchant.getDescription();
        merchantImage = merchant.getMerchantImage();
        merchantPhone = merchant.getMerchantPhone();

    }

    public String getMerchantDescription() {
        return merchantDescription;
    }

    public void setMerchantDescription(String merchantDescription) {
        this.merchantDescription = merchantDescription;
    }

    public String getMerchantImage() {
        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {
        this.merchantImage = merchantImage;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public List<MerchantOfficeHourResponse> getMerchantOfficeHour() {
        return merchantOfficeHour;
    }

    public void setMerchantOfficeHour(List<MerchantOfficeHourResponse> merchantOfficeHour) {
        this.merchantOfficeHour = merchantOfficeHour;
    }
}
