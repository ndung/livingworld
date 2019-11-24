package id.co.icg.lw.services.livingWorld;

import id.co.icg.lw.api.livingWorld.*;
import id.co.icg.lw.component.RetrofitClient;
import id.co.icg.lw.domain.IFabulaResponse;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class LivingWorldApiServiceImpl extends RetrofitClient<LivingWorldApi> implements LivingWorldApiService {

    private Logger logger = LoggerFactory.getLogger(LivingWorldApiServiceImpl.class);

    String baseUrl;

    @Autowired
    public LivingWorldApiServiceImpl(@Value("${iFabula.url}") String baseUrl,
                                     @Value("${proxy.host}") String proxyHost,
                                     @Value("${proxy.port}") int proxyPort) {
        super(baseUrl, LivingWorldApi.class, proxyHost, proxyPort);
        this.baseUrl = baseUrl;
    }

    @Override
    public Object getMemberType() throws Exception {
        return getMasterData("member_type");
    }

    @Override
    public Object getReligion() throws Exception {
        return getMasterData("religion");
    }

    @Override
    public Object getGender() throws Exception {
        return getMasterData("gender");
    }

    @Override
    public Object getMartialStatus() throws Exception {
        return getMasterData("marital_status");
    }

    @Override
    public Object getNationality() throws Exception {
        return getMasterData("nationality");
    }

    @Override
    public Object getCity() throws Exception {
        return getMasterData("city");
    }

    @Override
    public Object getMerchantCategory() throws Exception {
        return getMasterData("merchant_categories");
    }

    @Override
    public Object getMerchant() throws Exception {
        return getMasterData("merchants");
    }

    @Override
    public Object getSourceOfFunds() throws Exception {
        return getMasterData("sof");
    }

    @Override
    public Object getTransactionHistory(String cardNumber) throws Exception {
        return getTransactionData("history", cardNumber);
    }

    @Override
    public Object getLuckyDraw(String cardNumber) throws Exception {
        return getTransactionData("luckyDraws", cardNumber);
    }

    public Object getMember(String cardNumber) throws Exception {
        Call<IFabulaResponse> callSync = service.getMember(cardNumber);
        Response<IFabulaResponse> response = callSync.execute();
        IFabulaResponse iFabulaResponse = response.body();
        return iFabulaResponse.getList();
    }

    public Object getMemberByNoKtp(String ktp) throws Exception {
        Call<IFabulaResponse> callSync = service.getMemberByNoKtp(ktp);
        Response<IFabulaResponse> response = callSync.execute();
        IFabulaResponse iFabulaResponse = response.body();
        return iFabulaResponse.getList();
    }

    private Retrofit mRetrofit = null;

    @Override
    public String createMember(CreateMemberRequest request) throws Exception{
        Call<CreateMemberResponse> callSync = service.createMember(request);
        Response<CreateMemberResponse> response = callSync.execute();
        CreateMemberResponse iFabulaResponse = response.body();
        return iFabulaResponse.getCardNumber();
    }

    @Override
    public boolean updateMember(UpdateMemberRequest request) throws Exception {
        Call<ResponseBody> callSync = service.updateMember(request);
        Response<ResponseBody> body = callSync.execute();

        return true;
    }

    @Override
    public Object addTransaction(AddTransactionRequest request) throws Exception {
        Call<AddTransactionResponse> callSync = service.addTransaction(request);
        Response<AddTransactionResponse> response = callSync.execute();
        return response.body();
    }

    @Override
    public Object redeemPoints(RedeemPointRequest request) throws Exception {
        Call<ResponseBody> callSync = service.redeemPoints(request);
        Response<ResponseBody> response = callSync.execute();
        return response.body();
    }

//    public AddTransactionResponse addMemberTransaction(AddTransactionRequest request) throws Exception {
//
//    }

    private Object getMasterData(String type) throws Exception {
        Call<IFabulaResponse> callSync = service.getMaster(type);
        Response<IFabulaResponse> response = callSync.execute();
        IFabulaResponse iFabulaResponse = response.body();
        return iFabulaResponse.getList();
    }

    private Object getTransactionData(String type, String cardNumber) throws Exception{
        Call<IFabulaResponse> callSync = service.getTransaction(type, cardNumber);
        Response<IFabulaResponse> response = callSync.execute();
        IFabulaResponse iFabulaResponse = response.body();
        return iFabulaResponse.getList();
    }

    public Object getMasterReward(String card) throws Exception {
        Call<IFabulaResponse> callSync = service.getReward("reward", card);
        Response<IFabulaResponse> response = callSync.execute();
        IFabulaResponse iFabulaResponse = response.body();
        return iFabulaResponse.getList();
    }

}
