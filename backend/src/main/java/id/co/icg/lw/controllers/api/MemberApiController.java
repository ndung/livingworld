package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.member.CreateMemberRequest;
import id.co.icg.lw.services.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Application.API_PATH + "/member")
public class MemberApiController extends BaseController {

    @Autowired
    private MemberService memberService;


    /**
     * @api {post} /user/registration/member Member Registration
     * @apiName Member Registration
     * @apiGroup Member
     * @apiPermission USER
     * @apiDescription Daftar untuk menjadi member living world
     * @apiParam {String} cardNumber
     * @apiParam {String} password
     *
     * @apiSuccess {String} fullName
     * @apiSuccess {String} ktpNo
     * @apiSuccess {int} gender `1` Male; `2` Female; `3` Other
     * @apiSuccess {int} martialStatus `1` Single; `2` Married; `3` Other
     * @apiSuccess {Date} dateOfBirth YYYY-MM-DD
     * @apiSuccess {int} nationality `1` Indonesian; `2` Other
     * @apiSuccess {String} address
     * @apiSuccess {int} city `1` Jakarta; `2` Other
     * @apiSuccess {String} zipCode
     * @apiSuccess {String} homeNumber
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {json} Request
     * {
     *
     *   "fullName" : "Budi",
     *   "ktpNo" : "132131231231",
     *   "gender" : 1,
     *   "martialStatus" : 1,
     *   "dateOfBirth" : "1920-01-31",
     *   "nationality" : 1,
     *   "address" : "Jl. Jendral Sudirman No 16 Kota Bogor",
     *   "city" : 2,
     *   "zipCode" : 16911,
     *   "homeNumber" : "(0251) 7571231"
     *
     * }
     * @apiExample {json} Response Berhasil
     * {
     *     data: true,
     *     message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: false,
     *      message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Response> memberRegistration(@RequestBody CreateMemberRequest createMemberRequest) {
        try {
            boolean status = memberService.createMember(createMemberRequest);
            return getHttpStatus(new Response(status));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

}
