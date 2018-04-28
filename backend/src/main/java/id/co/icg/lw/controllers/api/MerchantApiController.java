//package id.co.icg.lw.controllers.api;
//
//import id.co.icg.lw.Application;
//import id.co.icg.lw.domain.Response;
//import id.co.icg.lw.enums.RoleEnum;
//
//import id.co.icg.lw.services.merchant.MerchantDetailResponse;
//import id.co.icg.lw.services.merchant.MerchantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(Application.API_PATH + "/merchant")
//public class MerchantApiController extends BaseController {
//
//    @Autowired
//    private MerchantService merchantService;
//
//    /**
//     * @api {get} /merchant Get Merchant
//     * @apiName Get Merchant
//     * @apiDescription Mengambil merchant yang tersedia secara alfabetis
//     * @apiGroup Merchant
//     * @apiPermission USER
//     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
//     * @apiHeaderExample {json} Contoh Token Header
//     * {
//     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
//     * }
//     * @apiExample {json} Response
//     * {
//     *     data : [
//     *          {
//     *              "merchantCategoryId" : "10",
//     *              "merchantCategoryName" : "Beauty, Wellness, & Sports",
//     *              "merchantList" : [
//     *                  {
//     *                      "merchantId" : 291,
//     *                      "merchantName" : "Celebrity Fitness",
//     *                      "merchantLogo" : "/merchant/logo/celebrity_fitness.jpg"
//     *                  }
//     *                  {
//     *                      "merchantId" : 10,
//     *                      "merchantName" : "Natasha Care",
//     *                      "merchantLogo" : "/merchant/logo/natasha_care.jpg"
//     *                  },
//     *
//     *              ]
//     *          },
//     *          {
//     *              "merchantCategoryId" : "2",
//     *              "merchantCategoryName" : "Food & Beverages",
//     *              "merchantList" : [
//     *                  {
//     *                      "merchantId" : 5,
//     *                      "merchantName" : "JCO",
//     *                      "merchantLogo" : "/merchant/logo/jco.jpg"
//     *                  },
//     *                  {
//     *                      "merchantId" : 4,
//     *                      "merchantName" : "Pizza Hut",
//     *                      "merchantLogo" : "/merchant/logo/pizza_hut.jpg"
//     *                  }
//     *              ]
//     *          },
//     *
//     *     ],
//     *     message : null
//     * }
//     * @apiSuccess {String} merchantCategoryId
//     * @apiSuccess {String} merchantCategoryName
//     * @apiSuccess {Array} merchantList Daftar merchant yang masuk ke dalam kaegory merchantCategoryId
//     * @apiSuccess {int} merchantId
//     * @apiSuccess {String} merchantName
//     * @apiSuccess {String} merchantLogo
//     *
//     */
//    @RequestMapping("")
//    public ResponseEntity<Response> getMerchantCategory(@RequestHeader(Application.AUTH) String token) {
//        if (!authorize(RoleEnum.USER, token)) {
//            return FORBIDDEN;
//        }
//
//        List<MerchantCategoryResponse> messageRequests = merchantService.findAllOrderByMerchantCategoryName();
//        return getHttpStatus(new Response(messageRequests));
//    }
//
//    /**
//     * @api {get} /merchant/{merchantId}/detail/ Get Merchant Detail by merchantId
//     * @apiName Get Merchant Detail by MerchantId
//     * @apiDescription Mengambil informasi merchant berdasarkan merchantId
//     * @apiGroup Merchant
//     * @apiPermission USER
//     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
//     * @apiHeaderExample {json} Contoh Token Header
//     * {
//     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
//     * }
//     * @apiExample {json} Response
//     * {
//     *     data : {
//     *       "merchantId" : "5",
//     *       "merchantName" : "JCO",
//     *       "merchantImage" : "/merchant/images/jco.jpg",
//     *       "merchantPhone": "021 - 41231231",
//     *       "merchantDescription" : "Lorem Ipsum",
//     *       "merchantLogo" : "/merchant/logo/celebrity_fitness.jpg"
//     *       "merchantOfficeHour" : [
//     *           {
//     *              "day" : 1,
//     *              "open" : "10:00:00",
//     *              "close" : "21:00:00"
//     *           },
//     *           {
//     *              "day" : 2,
//     *              "open" : "10:00:00",
//     *              "close" : "21:00:00"
//     *           },
//     *           {
//     *              "day" : 7,
//     *              "open" : "closed",
//     *              "close" : "closed"
//     *           },
//     *
//     *
//     *       ]
//     *
//     *
//     *    },
//     *     message : null
//     * }
//     * @apiSuccess {String} merchantId
//     * @apiSuccess {String} merchantName
//     * @apiSuccess {String} merchantImage
//     * @apiSuccess {String} merchantPhone
//     * @apiSuccess {String} merchantDescription
//     * @apiSuccess {Array} merchantOfficeHour
//     * @apiSuccess {Array} merchantList Daftar merchant yang masuk ke dalam kaegory merchantCategoryId
//     * @apiSuccess {int} merchantId
//     * @apiSuccess {int} day Hari dengan ketentuan Senin = 1, Selasa = 2, Rabu = 3, Kamis = 4, Juma'at = 5, Sabtu = 6, dan Minggu  = 7
//     * @apiSuccess {String} open Jam buka
//     * @apiSuccess {String} close Jam tutup
//     *
//     */
//    @RequestMapping("/{merchantId}/detail")
//    public ResponseEntity<Response> getMessageByUser(@RequestHeader(Application.AUTH) String token,
//                                                     @PathVariable("merchantId") long merchantId) {
//        if (!authorize(RoleEnum.USER, token)) {
//            return FORBIDDEN;
//        }
//
//        MerchantDetailResponse merchant = new MerchantDetailResponse(merchantService.findOne(merchantId));
//        return getHttpStatus(new Response(merchant));
//    }
//}
