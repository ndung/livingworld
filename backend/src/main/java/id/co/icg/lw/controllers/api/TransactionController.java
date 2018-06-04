package id.co.icg.lw.controllers.api;


import com.google.gson.internal.LinkedTreeMap;
import id.co.icg.lw.Application;
import id.co.icg.lw.api.livingWorld.AddTransactionRequest;
import id.co.icg.lw.api.livingWorld.AddTransactionResponse;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.transaction.History;
import id.co.icg.lw.domain.transaction.LuckyDraw;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.LuckyDrawRepository;
import id.co.icg.lw.repositories.UserRepository;
import id.co.icg.lw.services.livingWorld.LivingWorldApiService;
import id.co.icg.lw.services.member.CreateMemberRequest;


import id.co.icg.lw.services.transaction.TransactionService;
import id.co.icg.lw.services.user.EditUserRequest;
import id.co.icg.lw.services.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Application.API_PATH + "/transaction")
public class TransactionController extends BaseController {

    @Autowired
    private LivingWorldApiService livingWorldApiService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LuckyDrawRepository luckyDrawRepository;

    /**
     * @api {get} /transaction/balance/day Get Transaction Balance in A Day
     * @apiName Get Transaction Balance in A Day
     * @apiGroup Transaction
     * @apiPermission USER
     * @apiDescription Mendapatkan saldo transaksi pada hari mengakses
     *
     * @apiSuccess {long} balance
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *

     * @apiExample {json} Response Berhasil
     * {
     *     data: {
     *         "balance" : 1000000
     *     },
     *
     *     message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "/balance/day", method = RequestMethod.POST)
    public ResponseEntity<Response> getBalanceToday(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            long balance = transactionService.getBalanceToday(userId);
            return getHttpStatus(new Response(balance));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }


    /**
     * @api {get} /transaction/balance/month Get Transaction Balance One Month
     * @apiName Get Transaction Balance in a Month
     * @apiGroup Transaction
     * @apiPermission USER
     * @apiDescription Mendapatkan saldo transaksi pada bulan mengakses
     *
     * @apiSuccess {long} balance
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *

     * @apiExample {json} Response Berhasil
     * {
     *     data: {
     *         "balance" : 1000000
     *     },
     *
     *     message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "/balance/month", method = RequestMethod.POST)
    public ResponseEntity<Response> getBalanceThisMonth(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            long balance = transactionService.getBalanceThisMonth(userId);
            return getHttpStatus(new Response(balance));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response> createTransaction(@RequestHeader(Application.AUTH) String token,
                                                      @RequestBody AddTransactionRequest request) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        try {
            String userId = getUserId(token);
            Object response = livingWorldApiService.addTransaction(request);
            return getHttpStatus(new Response(response));
        } catch (Exception e) {
            e.printStackTrace();
            return getHttpStatus(new Response(e.getMessage()));
        }
    }


    @RequestMapping(value = "/luckyDraws", method = RequestMethod.GET)
    public ResponseEntity<Response> getLuckyDraws(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            User user = userRepository.findOne(userId);
            List<LinkedTreeMap> list = (List<LinkedTreeMap>) livingWorldApiService.getLuckyDraw(user.getCardNumber());
            List<LuckyDraw> luckyDraws = new ArrayList<>();
            for (LinkedTreeMap map : list){
                LuckyDraw draw = new LuckyDraw();
                draw.setNo((String)map.get("no"));
                draw.setTime((String)map.get("time"));
                draw.setUser(user);
                draw.setCreateAt(new Date());
                try {
                    luckyDrawRepository.saveAndFlush(draw);
                }catch(Exception ex){};
                luckyDraws.add(draw);
            }
            return getHttpStatus(new Response(luckyDraws));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ResponseEntity<Response> createTransaction(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        try {
            String userId = getUserId(token);
            User user = userRepository.findOne(userId);
            List<LinkedTreeMap> list = (List<LinkedTreeMap>) livingWorldApiService.getTransactionHistory(user.getCardNumber());
            List<History> histories = new ArrayList<>();
            for (LinkedTreeMap map : list){
                History history = new History();
                history.setId((String)map.get("id"));
                history.setMerchant((String)map.get("merchant"));
                history.setAmount((String)map.get("amount"));
                history.setSourceOfFund((String)map.get("sourceOfFund"));
                history.setTime((String)map.get("time"));
                history.setPoints((String)map.get("points"));
                history.setLuckyDraws((ArrayList)map.get("luckyDraws"));
                histories.add(history);
            }
            return getHttpStatus(new Response(histories));
        } catch (Exception e) {
            e.printStackTrace();
            return getHttpStatus(new Response(e.getMessage()));
        }
    }
}
