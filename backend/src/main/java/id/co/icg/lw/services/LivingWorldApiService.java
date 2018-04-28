package id.co.icg.lw.services;

import id.co.icg.lw.api.livingWorld.LivingWorldApi;
import id.co.icg.lw.component.RetrofitClient;
import id.co.icg.lw.domain.IFabulaResponse;
import id.co.icg.lw.services.member.CreateMemberResponse;
import id.co.icg.lw.services.member.UpdateMemberResponse;
import id.co.icg.lw.services.member.CreateMemberRequest;
import id.co.icg.lw.services.member.UpdateMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
public class LivingWorldApiService extends RetrofitClient<LivingWorldApi> {

    @Autowired
    public LivingWorldApiService(@Value("${iFabula.url}") String baseUrl) {
        super(baseUrl, LivingWorldApi.class);
    }

    public Object getMemberType() throws Exception {
        return getMasterData("member_type");
    }

    public Object getReligion() throws Exception {
        return getMasterData("religion");
    }

    public Object gender() throws Exception {
        return getMasterData("gender");
    }

    public Object getMartialStatus() throws Exception {
        return getMasterData("martial_status");
    }

    public Object getNationality() throws Exception {
        return getMasterData("nationality");
    }

    public Object getCity() throws Exception {
        return getMasterData("city");
    }

    public Object getMerchantCategory() throws Exception {
        return getMasterData("merchant_categories");
    }

    public Object getMerchant() throws Exception {
        return getMasterData("merchants");
    }

    public Object getSourceOfFunds() throws Exception {
        return getMasterData("sof");
    }

    public Object getTransactionHistory(String cardNumber) throws Exception {
        return getTransactionData("history", cardNumber);
    }

    public Object getLuckyDraw(String cardNumber) throws Exception {
        return getTransactionData("luckyDraws", cardNumber);
    }

    public Object getMember(String cardNumber) throws Exception {
        return getMember("read", cardNumber);
    }

    public String createMember(CreateMemberRequest request) throws Exception{
        Call<CreateMemberResponse> callSync = service.createMember(request);
        Response<CreateMemberResponse> response = callSync.execute();
        CreateMemberResponse iFabulaResponse = response.body();
        return iFabulaResponse.getCardNumber();
    }

    public boolean updateMember(UpdateMemberRequest request) throws Exception {
        Call<UpdateMemberResponse> callSync = service.updateMember(request);
        Response<UpdateMemberResponse> response = callSync.execute();
        UpdateMemberResponse iFabulaResponse = response.body();
        if (!iFabulaResponse.getStatus().equals("success")) {
            throw new Exception(iFabulaResponse.getFailedReason());
        }

        return true;
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

    private Object getMember(String type, String cardNumber) throws Exception {
        Call<IFabulaResponse> callSync = service.getMember(type, cardNumber);
        Response<IFabulaResponse> response = callSync.execute();
        IFabulaResponse iFabulaResponse = response.body();
        return iFabulaResponse.getList();
    }



}
