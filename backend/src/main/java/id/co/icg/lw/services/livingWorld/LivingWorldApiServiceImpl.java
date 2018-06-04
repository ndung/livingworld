package id.co.icg.lw.services.livingWorld;

import id.co.icg.lw.api.livingWorld.*;
import id.co.icg.lw.component.RetrofitClient;
import id.co.icg.lw.domain.IFabulaResponse;
import id.co.icg.lw.services.member.UpdateMemberResponse;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Component
public class LivingWorldApiServiceImpl extends RetrofitClient<LivingWorldApi> implements LivingWorldApiService {

    private Logger logger = LoggerFactory.getLogger(LivingWorldApiServiceImpl.class);

    String baseUrl;

    @Autowired
    public LivingWorldApiServiceImpl(@Value("${iFabula.url}") String baseUrl) {
        super(baseUrl, LivingWorldApi.class);
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
        System.out.println(body);
        System.out.println(body.message());
        System.out.println(body.raw());
        System.out.println(body.toString());

        return true;
    }

    @Override
    public Object addTransaction(AddTransactionRequest request) throws Exception {
        System.out.println("before");
        Call<ResponseBody> callSync = service.addTransaction(request);
        Response body = callSync.execute();
        System.out.println(body);
        System.out.println(body.message());
        System.out.println(body.raw());
        System.out.println(body.toString());
        System.out.println(body.errorBody());
        return null;
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



}
