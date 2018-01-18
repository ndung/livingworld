package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Application.API_PATH + "/user")
public class UserApiController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * @api {post} /user/check/card-number Check Card Number
     * @apiName Check Card Number
     * @apiGroup User
     * @apiPermission none
     * @apiDescription Memeriksa status kartu: Sudah terdaftar, belum terdaftar, invalid number.
     * @apiParam {String} cardNumber Disimpan di dalam body post sebagai text
     *
     * @apiSuccess {int} data Nilai `1` untuk terdaftar. Nilai `0` belum terdaftar. Nilai `-1` invalid number
     * @apiSuccess {String} message
     *
     * @apiExample {body} Contoh Body Request
     * 883123121231
     * @apiExample {json} Response Kartu Terdaftar
     * {
     *      data: 1,
     *      message: null
     * }
     *
     * @apiExample {json} Response Kartu Belum Terdaftar
     * {
     *      data: 0,
     *      message: null
     * }
     * @apiExample {json} Response Invalid Number
     * {
     *      data: -1,
     *      message: null
     * }
     */
    @RequestMapping(value = "/check/card-number", method = RequestMethod.POST)
    public ResponseEntity<Response> checkCardNumber(@RequestBody String phoneNumber) {
        boolean result = userService.checkCardNumber(phoneNumber);
        return getHttpStatus(new Response(result));
    }

    /**
     * @api {post} /user/sign-in Sign In
     * @apiName Sign In
     * @apiGroup User
     * @apiPermission none
     * @apiDescription Meminta token dan informasi user berdasarkan nomor kartu
     * @apiParam {String} cardNumber
     * @apiParam {String} password
     *
     * @apiSuccess {String} fullName
     * @apiSuccess {String} email
     * @apiSuccess {String} mobileNumber
     * @apiSuccess {int} gender `1` Male; `2` Female; `3` Other
     * @apiSuccess {int} martialStatus `1` Single; `2` Married; `3` Other
     * @apiSuccess {Date} dateOfBirth YYYY-MM-DD
     * @apiSuccess {int} nationality `1` Indonesian; `2` Other
     * @apiSuccess {String} address
     * @apiSuccess {int} city `1` Jakarta; `2` Other
     * @apiSuccess {String} zipCode
     * @apiSuccess {String} homeNumber
     *
     * @apiHeader {String} Token Jika Sign In berhasil, maka akan dibuatkan token yang akan disimpan di header response.
     *                           Token ini digunakan sebagai authentication key untuk mengakses beberapa api.
     * @apiHeaderExample {json} Contoh Response Header
     * {
     *     "Token" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {json} Request
     * {
     *     'cardNumber': '888123123'
     *     'password': 'katasandi'
     * }
     * @apiExample {json} Response Berhasil Sign In
     * {
     *      data: {
     *          "fullName" : "Budi",
     *          "email" : "budi@mail.com",
     *          "mobileNumber: "08123131313",
     *          "gender" : 1,
     *          "martialStatus" : 1,
     *          "dateOfBirth" : "1920-01-31",
     *          "nationality" : 1,
     *          "address" : "Jl. Jendral Sudirman No 16 Kota Bogor",
     *          "city" : 2,
     *          "zipCode" : 16911,
     *          "homeNumber" : "(0251) 7571231"
     *      },
     *      message: null
     * }
     *
     * @apiExample {json} Response Gagal Sign In
     * {
     *      data: "",
     *      message: "Card number or password is invalid"
     * }
     */
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<Response> signIn(@RequestBody RequestLogin requestSignIn) {
        try {
            User user = userService.login(requestSignIn);
            String token = createToken(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Token", token);
            return getHttpStatus(new Response(true), responseHeaders);
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /user/sign-up Sign Up
     * @apiName Sign Up
     * @apiGroup User
     * @apiPermission none
     * @apiDescription Daftar untuk bergabung dengan program Living World
     * @apiParam {String} cardNumber
     * @apiParam {String} password
     *
     * @apiSuccess {String} fullName
     * @apiSuccess {String} email
     * @apiSuccess {String} mobileNumber
     * @apiSuccess {Date} dateOfBirth YYYY-MM-DD
     *
     * @apiHeader {String} Token Jika Sign In berhasil, maka akan dibuatkan token yang akan disimpan di header response.
     *                           Token ini digunakan sebagai authentication key untuk mengakses beberapa api.
     * @apiHeaderExample {json} Contoh Response Header
     * {
     *     "Token" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {body} Request
     * {
     *     'cardNumber': '888123123'
     *     'password': 'katasandi'
     * }
     * @apiExample {json} Response Berhasil Sign In
     * {
     *      data: {
     *          "fullName" : "Budi",
     *          "email" : "budi@mail.com",
     *          "mobileNumber: "08123131313",
     *          "dateOfBirth" : "1920-01-31"
     *      },
     *      message: null
     * }
     *
     * @apiExample {json} Response Gagal Sign In
     * {
     *      data: "",
     *      message: "Card number or password is invalid"
     * }
     */
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<Response> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            User user = userService.signUp(signUpRequest);
            String token = createToken(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Token", token);
            return getHttpStatus(new Response(true), responseHeaders);
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /user/payment/registration Payment Registration
     * @apiName Registration
     * @apiGroup User
     * @apiPermission CLIENT
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Token" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiParam {String} fullName
     * @apiParam {String} email
     * @apiParam {String} pin
     * @apiParam {String} pinConfirmation
     * @apiParam {String} phoneNumber
     * @apiParam {int} securityQuestion
     * @apiParam {String} securityAnswer
     * @apiExample {json} Request
     * {
     *     'fullName' : 'Lorem Ipsum',
     *     'email' : 'mail@mail.com',
     *     'pin' : '123456'
     *     'pinConfirmation' : '123456',
     *     'securityQuestion' : 1,
     *     'securityAnswer' : 'Arsenal'
     * }
     * @apiExample {json} Response Berhasil
     * {
     *      data: true,
     *      message: null
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: false,
     *      message: "Pin and comfirmation pin is not match"
     * }
     */

    @RequestMapping(value = "/registration/payment", method = RequestMethod.POST)
    public ResponseEntity<Response> paymentRegistration(@RequestBody RequestNewUser requestNewUser) {
        try {
            User user = userService.paymentRegistration(requestNewUser);
            String token = createToken(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Token", token);
            return getHttpStatus(new Response(true), responseHeaders);
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {put} /user/edit Edit User
     * @apiName Edit User
     * @apiGroup User
     * @apiPermission none
     * @apiHeader {String} Authorization Jika registrasi berhasil, maka akan dibuatkan token yang akan disimpan di header response.
     *                           Token ini digunakan sebagai authentication key untuk mengakses beberapa api.
     * @apiHeaderExample {json} Contoh Response Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiParam {String} fullName
     * @apiParam {String} email
     * @apiParam {String} pin
     * @apiParam {String} pinConfirmation
     * @apiParam {String} phoneNumber
     * @apiParam {int} securityQuestion
     * @apiParam {String} securityAnswer
     * @apiExample {json} Request
     * {
     *     'fullName' : 'Lorem Ipsum',
     *     'email' : 'mail@mail.com',
     *     'pin' : '123456'
     *     'pinConfirmation' : '123456',
     *     'securityQuestion' : 1,
     *     'securityAnswer' : 'Arsenal'
     * }
     * @apiExample {json} Response Berhasil
     * {
     *      data: true,
     *      message: null
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: false,
     *      message: "Pin and comfirmation pin is not match"
     * }
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<Response> edit(@RequestBody RequestEditUser requestEditUser) {
        try {
            User user = userService.edit(requestEditUser);
            String token = createToken(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Token", token);
            return getHttpStatus(new Response(true), responseHeaders);
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }




}
