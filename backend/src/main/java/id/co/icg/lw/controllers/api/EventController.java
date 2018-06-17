package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Event;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Application.API_PATH + "/event")
public class EventController extends BaseController {

    @Autowired
    private EventRepository eventRepository;

    /**
     * @api {get} /current-offer/{page}/page Get Current Offer
     * @apiName Get Current Offer
     * @apiDescription Menggambil current offer. Satu page terdapat 10 messages. Pesan akan diurutkan berdasarkan tanggal secara descending
     * @apiGroup Current_Offer
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
     *              "id" : "9",
     *              "title" : "Lorem Ipsum",
     *              "shortDescription" : "Isi pesan yang akan diterima",
     *              "thumbnail" : "3123-daf-23dsf.jpg"
     *          }
     *
     *     ],
     *     message : null
     * }
     * @apiSuccess {long} id
     * @apiSuccess {String} title
     * @apiSuccess {String} shortDescription
     * @apiSuccess {String} thumbnail image
     *
     */
    @RequestMapping("")
    public ResponseEntity<Response> getCurrentEvent(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        List<Event> list = null;
        String message;
        try {
            list = eventRepository.findAllActive();
            message = "Success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return getHttpStatus(new Response(list, message));
    }
}
