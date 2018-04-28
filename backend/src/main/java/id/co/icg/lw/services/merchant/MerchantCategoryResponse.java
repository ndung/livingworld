//package id.co.icg.lw.services.merchant;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MerchantCategoryResponse {
//    private long merchantCategoryId;
//    private String merchantCategoryName;
//    private List<MerchantResponse> merchantList;
//
//    public MerchantCategoryResponse(){}
//
//    public MerchantCategoryResponse(MerchantCategory merchantCategory) {
//        this.merchantCategoryId = merchantCategory.getMerchantCategoryId();
//        this.merchantCategoryName = merchantCategory.getMerchantCategoryName();
//
//        if (merchantCategory.getMerchantList() != null) {
//            merchantList = new ArrayList<>();
//            for (id.co.icg.lw.domain.master.Merchant.Merchant merchant : merchantCategory.getMerchantList()) {
//                merchantList.add(new MerchantResponse(merchant));
//            }
//        }
//    }
//
//    public long getMerchantCategoryId() {
//        return merchantCategoryId;
//    }
//
//    public void setMerchantCategoryId(long merchantCategoryId) {
//        this.merchantCategoryId = merchantCategoryId;
//    }
//
//    public String getMerchantCategoryName() {
//        return merchantCategoryName;
//    }
//
//    public void setMerchantCategoryName(String merchantCategoryName) {
//        this.merchantCategoryName = merchantCategoryName;
//    }
//
//    public List<MerchantResponse> getMerchantList() {
//        return merchantList;
//    }
//
//    public void setMerchantList(List<MerchantResponse> merchantList) {
//        this.merchantList = merchantList;
//    }
//}
