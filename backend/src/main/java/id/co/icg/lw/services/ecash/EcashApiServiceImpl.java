package id.co.icg.lw.services.ecash;

import id.co.icg.lw.api.ecash.ECashApi;
import id.co.icg.lw.component.RetrofitClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.crypto.Cipher;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

@Service
public class ECashApiServiceImpl extends RetrofitClient<ECashApi> implements ECashApiService, InitializingBean {

    private String username;
    private String token;

    @Autowired
    public ECashApiServiceImpl(@Value("${publicKey}") String publicKey,
                               @Value("${eCash.url}") String baseUrl,
                               @Value("${eCash.apiKey}") String APIKey,
                               @Value("${eCash.username}") String userName,
                               @Value("${eCash.password}") String password) {
        super(baseUrl);
        this.username = userName;

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("APIKey", APIKey);
            headers.put("Credentials", encrypt(password, publicKey));
            setHeader(ECashApi.class, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoginResponse login() throws Exception{
        Response<LoginResponse> response = service.login(username).execute();
        token = response.body().getToken();
        return response.body();
    }

    @Override
    public LogoutResponse logout(String token) throws Exception {
        Response<LogoutResponse> response = service.logout(token).execute();
        return response.body();
    }


    @Override
    public ValidateResponse validate(String phoneNumber) throws Exception{
        Response<ValidateResponse> response = service.validateAccount(phoneNumber, token).execute();
        return response.body();
    }

    @Override
    public TicketResponse getTicket(String mobileNumber) throws Exception{
        Response<TicketResponse> response = service.getRegisterTicket(mobileNumber, token).execute();
        return response.body();
    }

    @Override
    public BalanceInquiryResponse getBalanceInquiry(String userId, String token) throws Exception {
        return null;
    }

    private String encrypt(String text, String publicKey) throws Exception {
        byte[] cipherText = null;
        final Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublic(publicKey));
        byte[] textB = cipher.doFinal(text.getBytes("UTF-8"));
        cipherText = Base64.encodeBase64(textB);
        return new String(cipherText);
    }


    private PublicKey getPublic(String filename) throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f); DataInputStream dis = new DataInputStream(fis); byte[] keyBytes = new byte[(int) f.length()]; dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes); KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoginResponse login = this.login();
        System.out.println("login:"+login);
        ValidateResponse response = this.validate("08131234567");
        System.out.println("response1:"+response);
        ValidateResponse response2 = this.validate("08132224554");
        System.out.println("response2:"+response2);

    }
}
