package id.co.icg.lw.web.offer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nokieng17.emvcoqr.*;
import com.nokieng17.emvcoqr.encoding.PayloadEncoding;
import com.nokieng17.emvcoqr.iso.Iso3166Countries;
import com.nokieng17.emvcoqr.iso.Iso4217Currency;
import id.co.icg.lw.dao.model.app.Merchant;
import id.co.icg.lw.util.SpringPropertiesUtil;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.UrlBinding;

import java.io.File;

@UrlBinding("/merchant/viewqrcode.html")
public class ViewMerchantQrCodeActionBean extends ActionBeanClass {

    Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Before
    public void init() {
        try {
            String path = SpringPropertiesUtil.getProperty("app.file.upload.path")+ merchant.getId() + ".png";
            MerchantPayload payload = MerchantPayloadFunc.CreateStatic(
                    new MerchantAccountInformationMap()
                            .add(26, new MerchantAccountInformation()
                                    .setGlobalUniqueIdentifier("ID.CO.LIVINGWORLD.WWW"))
                                    //.addPaymentNetworkSpecifics(1, "936005030000003081")
                            .add(50, new MerchantAccountInformation()
                                    .setGlobalUniqueIdentifier(merchant.getId().replaceAll("\\-","")))
                            .add(51, new MerchantAccountInformation()
                                    .setGlobalUniqueIdentifier("ID.CO.QRIS.WWW")),
                                    //.addPaymentNetworkSpecifics(2, "ID8200000000227")
                                    //.addPaymentNetworkSpecifics(3, "UBE")),
                    5499,
                    new Iso4217Currency.Indonesiaextends().getNumericCode(),
                    Iso3166Countries.INDONESIA ,
                    merchant.getName().toUpperCase(),
                    "LIVING WORLD","15325");
            payload.setTransactionCurrency(new Iso4217Currency.Indonesiaextends().getNumericCode());
            payload.setAdditionalData(
                    new MerchantAdditionalData()
                            .setReferenceLabel("TANGERANG SELATAN"));
                            //.setCustomerLabel("478408")
                            //.setTerminalLabel("A01"));
            PayloadEncoding encode = new PayloadEncoding();
            String qr = encode.GeneratePayload(payload);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qr, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", new File(path));
            this.url = SpringPropertiesUtil.getProperty("app.file.images.url")+merchant.getId()+".png";
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
