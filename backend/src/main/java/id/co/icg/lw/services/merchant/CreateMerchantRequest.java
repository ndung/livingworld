package id.co.icg.lw.services.merchant;

import org.springframework.web.multipart.MultipartFile;

public class CreateMerchantRequest {
    private String merchantName;
    private long merchantCategoryId;
    private String merchantPhone;
    private MultipartFile merchantLogo;
    private MultipartFile merchantImage;
    private String[] openingTime;
    private String[] closingTime;
    private String description;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public long getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(long merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

    public String[] getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String[] openingTime) {
        this.openingTime = openingTime;
    }

    public String[] getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String[] closingTime) {
        this.closingTime = closingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(MultipartFile merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    public MultipartFile getMerchantImage() {
        return merchantImage;
    }

    public void setMerchantImage(MultipartFile merchantImage) {
        this.merchantImage = merchantImage;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }
}
