package id.co.icg.lw.services.livingWorld;

import id.co.icg.lw.api.livingWorld.AddTransactionRequest;
import id.co.icg.lw.api.livingWorld.AddTransactionResponse;
import id.co.icg.lw.api.livingWorld.CreateMemberRequest;
import id.co.icg.lw.api.livingWorld.RedeemPointRequest;
import id.co.icg.lw.services.member.UpdateMemberRequest;

public interface LivingWorldApiService {

    Object getMemberType() throws Exception;

    Object getReligion() throws Exception ;

    Object getGender() throws Exception;

    Object getMartialStatus() throws Exception;

    Object getNationality() throws Exception;

    Object getCity() throws Exception;

    Object getMerchantCategory() throws Exception;

    Object getMerchant() throws Exception;

    Object getSourceOfFunds() throws Exception;

    Object getMasterReward(String card) throws Exception;

    Object getTransactionHistory(String cardNumber) throws Exception;

    Object getLuckyDraw(String cardNumber) throws Exception;

    Object getMember(String cardNumber) throws Exception;

    String createMember(CreateMemberRequest request) throws Exception;

    boolean updateMember(UpdateMemberRequest request) throws Exception;

    Object addTransaction(AddTransactionRequest request) throws Exception;

    Object redeemPoints(RedeemPointRequest request) throws Exception;
}
