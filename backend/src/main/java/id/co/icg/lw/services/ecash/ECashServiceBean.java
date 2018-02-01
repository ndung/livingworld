package id.co.icg.lw.services.ecash;

import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.RestServiceGenerator;
import id.co.icg.lw.services.ecash.mandiriApi.*;
import id.co.icg.lw.services.user.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
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
public class ECashServiceBean implements ECashService {

    @Value("${publicKey}")
    private String publicKey;

    @Value("${eCash.url}")
    private String urlBase;

    @Value("${eCash.apiKey}")
    private String APIKey;

    @Value("${eCash.username}")
    private String userName;

    @Value("${eCash.password}")
    private String password;

    @Autowired
    private UserService userService;


    @Override
    public LoginResponse login() throws Exception{
        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        headers.put("Credentials", encrypt(password));
        ECashApiService eCashApiService = RestServiceGenerator.createService(ECashApiService.class, headers);
        Response<LoginResponse> response = eCashApiService.login(userName).execute();
        LoginResponse loginResponse = response.body();
        return loginResponse;

    }

    @Override
    public LogoutResponse logout(String token) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        ECashApiService eCashApiService = RestServiceGenerator.createService(ECashApiService.class, headers);
        Response<LogoutResponse> response = eCashApiService.logout(token).execute();
        LogoutResponse logoutResponse = response.body();
        return  logoutResponse;
    }


    @Override
    public ValidateResponse validate(String userId, String token) throws Exception{
        User user = userService.findOne(userId);
        if (user.getMobileNumber() == null || user.getMobileNumber().isEmpty()) {
            throw new Exception("Mobile number is required");
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        ECashApiService eCashApiService = RestServiceGenerator.createService(ECashApiService.class, headers);
        Response<ValidateResponse> response = eCashApiService.validate(user.getMobileNumber(), token).execute();
        return response.body();
    }

    @Override
    public TicketResponse requestTicket(String userId, String token) throws Exception{
        User user = userService.findOne(userId);
        if (user.getMobileNumber() == null || user.getMobileNumber().isEmpty()) {
            throw new Exception("Mobile number is required");
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        ECashApiService eCashApiService = RestServiceGenerator.createService(ECashApiService.class, headers);
        Response<TicketResponse> response = eCashApiService.getRegisterTicket(user.getMobileNumber(), token).execute();
        return response.body();
    }

    @Override
    public BalanceInquiryResponse getBalanceInquiry(String userId, String token) throws Exception{
        User user = userService.findOne(userId);
        if (user.getMobileNumber() == null || user.getMobileNumber().isEmpty()) {
            throw new Exception("Mobile number is required");
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("APIKey", APIKey);
        ECashApiService eCashApiService = RestServiceGenerator.createService(ECashApiService.class, headers);
        Response<BalanceInquiryResponse> response = eCashApiService.getBalanceInquiry(user.getMobileNumber(), user.getEcashId(), token).execute();
        return response.body();
    }


    private String encrypt(String text) throws Exception {
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
}
