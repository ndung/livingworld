package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.message.MessageRequest;
import id.co.icg.lw.services.message.MessageService;
import id.co.icg.lw.services.message.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Application.API_PATH + "/message")
public class MessageApiController extends BaseController {

    @Autowired
    private MessageService messageService;

    /**
     * @api {get} /message/{page}/page Get Message By User
     * @apiName Get Message By User
     * @apiDescription Mengambil message yang terkirim kepada client per page. Satu page terdapat 10 messages. Pesan akan diurutkan berdasarkan tanggal secara descending
     * @apiGroup Message
     * @apiParam {int} page Minimal nilai page = 1
     * @apiPermission USER
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response
     * {
     *     data : [
     *          {
     *              "title" : "Lorem Ipsum",
     *              "message" : "Isi pesan yang akan diterima",
     *              "date" : "2018-01-31"
     *          },
     *          {
     *              "title" : "Lorem Ipsum",
     *              "message" : "Isi pesan yang akan diterima",
     *              "date" : "2018-01-15"
     *          },
     *
     *     ],
     *     message : null
     * }
     * @apiSuccess {String} title
     * @apiSuccess {String} message
     * @apiSuccess {Date} date YYYY-MM-DD
     *
     */
    @RequestMapping("")
    public ResponseEntity<Response> getMessageByUser(@RequestHeader(Application.AUTH) String token,
                                               @PathVariable("page") int page) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        String userId = getUserId(token);
        List<MessageRequest> messageRequests = messageService.getMessagesByUser(page, userId);
        return getHttpStatus(new Response(messageRequests));
    }

    /**
     * @api {get} /message/contact-us Contact Us
     * @apiName Contact Us
     * @apiDescription Menerima pesan dari user
     * @apiGroup Message
     * @apiParam {String} fullName
     * @apiParam {String} email
     * @apiParam {String} comment
     * @apiPermission USER
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Request
     *          {
     *              "fullName" : "Bambang",
     *              "email" : "bambang@mail.com",
     *              "message" : "Isi pesan yang akan diterima"
     *          }
     *
     * @apiExample {json} Response Berhasil
     * {
     *   data: true,
     *   message: null
     * }
     * @apiExample {json} Response Gagal
     * {
     *   data: false,
     *   message: "Your message is invalid"
     * }
     */
    @RequestMapping("/contact-us")
    public ResponseEntity<Response> saveMessageFromUser(@RequestHeader(Application.AUTH) String token,
                                                        @RequestBody SendMessageRequest sendMessageRequest) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        String userId = getUserId(token);
        boolean status = messageService.saveMessageFromUser(userId, sendMessageRequest);
        return getHttpStatus(new Response(status));
    }
}
