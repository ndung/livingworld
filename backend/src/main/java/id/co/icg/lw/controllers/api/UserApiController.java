package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Response> checkCardNumber(@RequestBody String cardNumber) {
        int result = userService.checkCardNumber(cardNumber);
        return getHttpStatus(new Response(result));
    }

    /**
     * @api {post} /user/sign-in Sign In
     * @apiName Sign In
     * @apiGroup User
     * @apiPermission none
     * @apiDescription Meminta token dan informasi user berdasarkan nomor kartu
     * @apiParam {String} cardNumber
     * @apiParam {String} publicKey Hash md5 dengan ketentuan: md5([cardNumber][md5(password)][Tanggal akses dengan format YYYYMMDD][app-key]). contoh: md5(8877665574EE55083A714AA3791F8D594FEA00C920180118app-key)
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
     * @apiExample {json} Request
     * {
     *     'cardNumber': '88776655'
     *     'publicKey': '8246EC66759DB8B8F3EF6230911610EE'
     * }
     * @apiExample {json} Response Berhasil Registrasi
     * {
     *      data: {
     *          "fullName" : "Budi",
     *          "email" : "budi@mail.com",
     *          "mobileNumber": "08123131313",
     *          "dateOfBirth" : "1920-01-31",
     *          "photoProfileUrl" : "/files/filename.jpg"
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
    public ResponseEntity<Response> signIn(@RequestBody SignInRequest requestSignIn) {
        try {
            User user = userService.signIn(requestSignIn);
            String token = createToken(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Token", token);
            return getHttpStatus(new Response(new UserResponse(user)), responseHeaders);
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
     * @apiHeader {String} Token Jika Sign Up berhasil, maka akan dibuatkan token yang akan disimpan di header response.
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
     * @apiExample {json} Response Berhasil Registrasi
     * {
     *      data: {
     *          "fullName" : "Budi",
     *          "email" : "budi@mail.com",
     *          "mobileNumber": "08123131313",
     *          "dateOfBirth" : "1920-01-31",
     *          "photoProfileUrl" : "/files/filename.jpg"
     *      },
     *      message: null
     * }
     *
     * @apiExample {json} Response Gagal Sign Up
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
            return getHttpStatus(new Response(new UserResponse(user)), responseHeaders);
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /user/registration Registration
     * @apiName Registration
     * @apiGroup User
     * @apiPermission USER
     * @apiDescription Daftar untuk bergabung dengan program Living World
     * @apiParam {String} firstName
     * @apiParam {String} email
     * @apiParam {Date} dateOfBirth YYYY-MM-DD
     * @apiParam {String} mobileNumber
     *
     * @apiSuccess {String} fullName
     * @apiSuccess {String} email
     * @apiSuccess {String} mobileNumber
     * @apiSuccess {Date} dateOfBirth YYYY-MM-DD
     *
     * @apiHeader {String} Token Jika Sign Up berhasil, maka akan dibuatkan token yang akan disimpan di header response.
     *                           Token ini digunakan sebagai authentication key untuk mengakses beberapa api.
     * @apiHeaderExample {json} Contoh Response Header
     * {
     *     "Token" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {body} Request
     * {
     *     'fullName': 'Budi',
     *     'email': 'budi@mail.com',
     *     'mobileNumber': '08123131313',
     *     'dateOfBirth' : '1920-01-31'
     * }
     * @apiExample {json} Response Berhasil Registrasi
     * {
     *      data: {
     *          "fullName" : "Budi",
     *          "email" : "budi@mail.com",
     *          "mobileNumber": "08123131313",
     *          "dateOfBirth" : "1920-01-31",
     *          "photoProfileUrl" : "/files/filename.jpg"
     *      },
     *      message: null
     * }
     *
     * @apiExample {json} Response Gagal Registrasi
     * {
     *      data: "",
     *      message: "Date format is invalid"
     * }
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Response> registration(@RequestHeader(Application.AUTH) String token,
                                                 @RequestBody UserRequest userRequest) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        try {
            String userId = getUserId(token);
            User user = userService.register(userId, userRequest);
            return getHttpStatus(new Response(new UserResponse(user)));
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
     * @apiParam {String} mobileNumber
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
    public ResponseEntity<Response> paymentRegistration(@RequestBody UserRequest userRequest) {
        try {
            User user = userService.paymentRegistration(userRequest);
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
     * @apiParam {String} mobileNumber
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
    public ResponseEntity<Response> edit(@RequestHeader(Application.AUTH) String token,
                                         @RequestBody EditUserRequest editUserRequest) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            User user = userService.edit(userId, editUserRequest);
            return getHttpStatus(new Response(new UserResponse(user)));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /user/upload/photo-profile Upload Photo Profile
     * @apiName Upload Photo Profile
     * @apiGroup User
     * @apiPermission USER
     * @apiParam {File} photo Dikirim dengan format MultipartFile
     * @apiHeader {String} Authorization Jika registrasi berhasil, maka akan dibuatkan token yang akan disimpan di header response.
     *                           Token ini digunakan sebagai authentication key untuk mengakses beberapa api.
     * @apiHeaderExample {json} Contoh Response Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response Berhasil Registrasi
     * {
     *      data: {
     *          "fullName" : "Budi",
     *          "email" : "budi@mail.com",
     *          "mobileNumber": "08123131313",
     *          "dateOfBirth" : "1920-01-31",
     *          "photoProfileUrl" : "/files/filename.jpg"
     *      },
     *      message: null
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: false,
     *      message: "Format must be in jpg or jpeg"
     * }
     */
    @RequestMapping(value = "/upload/photo-profile", method = RequestMethod.POST)
    public ResponseEntity<Response> uploadPhotoProfile(@RequestHeader(Application.AUTH) String token,
                                                       @RequestParam("photo") MultipartFile photo) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            User user = userService.uploadPhotoProfile(userId, photo);
            return getHttpStatus(new Response(user));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }




}
