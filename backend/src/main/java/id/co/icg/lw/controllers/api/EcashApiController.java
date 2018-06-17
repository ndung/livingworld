package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.MemberRepository;
import id.co.icg.lw.repositories.UserRepository;
import id.co.icg.lw.services.ecash.EcashApiService;
import id.co.icg.lw.services.ecash.TicketResponse;
import id.co.icg.lw.services.ecash.ValidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Application.API_PATH + "/e-cash")
class EcashApiController extends BaseController {

    @Autowired
    private EcashApiService ecashApiService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemberRepository memberRepository;
    /**
     * @api {get} /e-cash/login Login
     * @apiName Login
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Response> login(@RequestHeader(Application.AUTH) String token) {
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
    public ResponseEntity<Response> validate(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            User user = userRepository.findOne(userId);
            Member member = memberRepository.findByUser(user);
            System.out.println("mobileno:"+member.getMobileNumber());
            ValidateResponse response = ecashApiService.validate(member.getMobileNumber());
            System.out.println("ticket:"+response);
            return getHttpStatus(new Response());
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
    public ResponseEntity<Response> getTicket(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        try {
            String userId = getUserId(token);
            User user = userRepository.findOne(userId);
            Member member = memberRepository.findByUser(user);
            System.out.println("user:"+user);
            System.out.println("mobileno:"+member.getMobileNumber());
            TicketResponse response = ecashApiService.getTicket(member.getMobileNumber());
            System.out.println("ticket:"+response);
            return getHttpStatus(new Response(response));
        } catch (Exception e) {
            e.printStackTrace();
            return getHttpStatus(new Response(e.getMessage()));
        }
    }
}
