package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.CurrentOffer;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.currentOffer.CurrentOfferDetailRequest;
import id.co.icg.lw.services.currentOffer.CurrentOfferRequest;
import id.co.icg.lw.services.currentOffer.CurrentOfferService;
import id.co.icg.lw.services.message.MessageResponse;
import id.co.icg.lw.services.message.MessageService;
import id.co.icg.lw.services.message.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Application.API_PATH + "/current-offer")
public class CurrentOfferApiController extends BaseController {

    @Autowired
    private CurrentOfferService currentOfferService;

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
    @RequestMapping("/{page}/page")
    public ResponseEntity<Response> getCurrentOffer(@RequestHeader(Application.AUTH) String token,
                                               @PathVariable("page") int page) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        List<CurrentOfferRequest> requests = null;
        String message;
        try {
            requests = currentOfferService.findAllCurrentOffer(page);
            message = "Success";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return getHttpStatus(new Response(requests, message));
    }

    /**
     * @api {get} /current-offer/{id}/detail Get Detail Current Offer
     * @apiName Get Detail Current Offer
     * @apiDescription Memanggil detail current offer
     * @apiGroup Current_Offer
     *
     * @apiPermission USER
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response Berhasil
     * {
     *   data: {
     *       "title" : "Judul",
     *       "description" : "Lorem ipsum lalalal",
     *       "startDate" : "2016-12-31",
     *       "endDate" : "2019-01-31",
     *       "id" : "9",
     *       "images" : [
     *             "12313123.jpg",
     *             "sdfasdf-asdfasdf.jpg",
     *             "dafasdfasdf.jpg",
     *             "fsfsfsdf.jpg"
     *       ]
     *   },
     *   message: null
     * }

     */
    @RequestMapping("/{id}/detail")
    public ResponseEntity<Response> getDetail(@RequestHeader(Application.AUTH) String token,
                                              @PathVariable("id") long id) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }


        CurrentOfferDetailRequest detail = currentOfferService.findCurrentOffer(id);
        return getHttpStatus(new Response(detail));
    }
}
