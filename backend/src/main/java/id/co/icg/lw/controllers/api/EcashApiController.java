package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.ecash.EcashApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Application.API_PATH + "/e-cash")
public class EcashApiController extends BaseController{

    @Autowired
    private EcashApiService ecashApiService;

    /**
     * @api {get} /e-cash/token Get Token
     * @apiName Get Token
     * @apiGroup ECash
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "6hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk56hka2osxj73f9s8jc3ertqf43k8xq2xsypvk"
     * }
     *
     * @apiExample {json} Response
     * {
     *
     *   data : {
     *      "token" : "esr5656hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk",
     *      "status" : "PROCESSED"
     *      },
     *   message: null
     *
     * }
     *
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseEntity<Response> getToken(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        try {
            return getHttpStatus(new Response(ecashApiService.login()));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {POST} /e-cash/logout Logout
     * @apiName Logout
     * @apiGroup ECash
     *
     * @apiDescription Logout ecash
     *
     * @apiParam {String} Token Nilai token diperoleh dari /e-cash/token
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "6hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk56hka2osxj73f9s8jc3ertqf43k8xq2xsypvk"
     * }
     *
     * @apiExample {json} Request
     * {
     *      esr5656hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk
     * }
     * @apiExample {json} Response
     * {
     *      "data" : {
     *          "status" : "PROCEED"
     *      },
     *     "message" : null
     *
     * }
     *
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Response> logout(@RequestHeader(Application.AUTH) String token,
                                              @RequestBody String ecashToken) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            return getHttpStatus(new Response(ecashApiService.logout(ecashToken)));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {POST} /e-cash/validateAccount Account Validation
     * @apiName Account Validation
     * @apiDescription Validasi apakah user sudah terdaftar dengan e-cash atau belum
     * @apiGroup ECash
     *
     * @apiDescription Logout
     *
     * @apiParam {String} Token Nilai token diperoleh dari /e-cash/token
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "6hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk56hka2osxj73f9s8jc3ertqf43k8xq2xsypvk"
     * }
     * @apiParam {String} Token Nilai token diperoleh dari /e-cash/token
     * @apiExample {json} Request
     * {
     *      esr5656hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk
     * }
     * @apiExample {json} Response
     * {
     *
     *   "ticket" : "Budi",
     *   "status" : "132131231231",
     *   "mobileno" : 1
     *
     * }
     *
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ResponseEntity<Response> validate(@RequestHeader(Application.AUTH) String token,
                                              @RequestBody String ecashToken) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            return getHttpStatus(new Response(ecashApiService.validate(userId, ecashToken)));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {POST} /e-cash/ticket Request Ticket
     * @apiName Request Ticket
     * @apiGroup ECash
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "6hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk56hka2osxj73f9s8jc3ertqf43k8xq2xsypvk"
     * }
     * @apiParam {String} Token Nilai token diperoleh dari /e-cash/token
     * @apiExample {json} Request
     * {
     *      esr5656hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk
     * }
     * @apiExample {json} Response
     * {
     *
     *   data : {
     *      "token" : "esr5656hka2osxj73f9s8jcw53b1d3ertqf43k8xq2xsypvk",
     *      "status" : "PROCESSED"
     *      },
     *   message: null
     *
     * }
     *
     */
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public ResponseEntity<Response> getTicket(@RequestHeader(Application.AUTH) String token,
                                              @RequestBody String ecashToken) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        try {
            String userId = getUserId(token);
            return getHttpStatus(new Response(ecashApiService.getTicket(userId, ecashToken)));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }
}
