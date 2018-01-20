package id.co.icg.lw.controllers.api;


import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.member.CreateMemberRequest;


import id.co.icg.lw.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Application.API_PATH + "/transaction")
public class TransactionController extends BaseController {

    @Autowired
    private TransactionService transactionService;

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


}
