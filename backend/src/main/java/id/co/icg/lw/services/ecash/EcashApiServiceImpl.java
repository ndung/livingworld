package id.co.icg.lw.services.ecash;

import id.co.icg.lw.api.ecash.EcashApi;
import id.co.icg.lw.component.EcashRetrofitClient;
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
public class EcashApiServiceImpl extends EcashRetrofitClient<EcashApi> implements EcashApiService, InitializingBean {

    private String username;
    private String token;

    private String APIKey;
    private String password;
    private  String publicKey;

    @Autowired
    public EcashApiServiceImpl(@Value("${publicKey}") String publicKey,
                               @Value("${eCash.url}") String baseUrl,
                               @Value("${eCash.apiKey}") String APIKey,
                               @Value("${eCash.username}") String userName,
                               @Value("${eCash.password}") String password) {
        super(baseUrl);
        this.username = userName;
        this.APIKey = APIKey;
        this.password = password;
        this.publicKey = publicKey;
    }

    @Override
    public LoginResponse login() throws Exception{
        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Credentials", encrypt(password, publicKey));
        setHeader(EcashApi.class, headers);

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
        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        setHeader(EcashApi.class, headers);
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

    }
}
