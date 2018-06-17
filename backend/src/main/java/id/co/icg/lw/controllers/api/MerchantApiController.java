package id.co.icg.lw.controllers.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.merchant.Merchant;
import id.co.icg.lw.domain.merchant.MerchantCategory;
import id.co.icg.lw.domain.merchant.MerchantOfficeHour;
import id.co.icg.lw.enums.RoleEnum;

import id.co.icg.lw.services.livingWorld.LivingWorldApiService;
import id.co.icg.lw.services.merchant.MerchantCategoryResponse;
import id.co.icg.lw.services.merchant.MerchantDetailResponse;
import id.co.icg.lw.services.merchant.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Application.API_PATH + "/merchant")
public class MerchantApiController extends BaseController {

    int i = 1;

    @Autowired
    private LivingWorldApiService livingWorldApiService;

    @Autowired
    private MerchantService merchantService;

    /**
     * @api {get} /merchant Get Merchant
     * @apiName Get Merchant
     * @apiDescription Mengambil merchant yang tersedia secara alfabetis
     * @apiGroup Merchant
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
     *              "merchantCategoryId" : "10",
     *              "merchantCategoryName" : "Beauty, Wellness, & Sports",
     *              "merchantList" : [
     *                  {
     *                      "merchantId" : 291,
     *                      "merchantName" : "Celebrity Fitness",
     *                      "merchantLogo" : "/merchant/logo/celebrity_fitness.jpg"
     *                  }
     *                  {
     *                      "merchantId" : 10,
     *                      "merchantName" : "Natasha Care",
     *                      "merchantLogo" : "/merchant/logo/natasha_care.jpg"
     *                  },
     *
     *              ]
     *          },
     *          {
     *              "merchantCategoryId" : "2",
     *              "merchantCategoryName" : "Food & Beverages",
     *              "merchantList" : [
     *                  {
     *                      "merchantId" : 5,
     *                      "merchantName" : "JCO",
     *                      "merchantLogo" : "/merchant/logo/jco.jpg"
     *                  },
     *                  {
     *                      "merchantId" : 4,
     *                      "merchantName" : "Pizza Hut",
     *                      "merchantLogo" : "/merchant/logo/pizza_hut.jpg"
     *                  }
     *              ]
     *          },
     *
     *     ],
     *     message : null
     * }
     * @apiSuccess {String} merchantCategoryId
     * @apiSuccess {String} merchantCategoryName
     * @apiSuccess {Array} merchantList Daftar merchant yang masuk ke dalam kaegory merchantCategoryId
     * @apiSuccess {int} merchantId
     * @apiSuccess {String} merchantName
     * @apiSuccess {String} merchantLogo
     *
     */
    @RequestMapping("")
    public ResponseEntity<Response> getMerchantCategory(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }
        i=i+1;
        List<MerchantCategory> categoryMap = new ArrayList<>();
        Map<String, List<Merchant>> merchantMap = new LinkedTreeMap<>();
        try {
            List<LinkedTreeMap> list = (List<LinkedTreeMap>) livingWorldApiService.getMerchantCategory();

            for (LinkedTreeMap map : list){
                MerchantCategory category = new MerchantCategory();
                category.setMerchantCategoryId((String)map.get("id"));
                category.setMerchantCategoryName((String)map.get("name"));
                if (i%10==0){
                    merchantService.createCategory(category.getMerchantCategoryId(), category.getMerchantCategoryName());
                }
                categoryMap.add(category);
            }

            list = (List<LinkedTreeMap>) livingWorldApiService.getMerchant();
            for (LinkedTreeMap map : list){
                String category = ((String)map.get("category")).trim();
                Merchant request = new Merchant();
                request.setMerchantId(((String)map.get("id")).trim());
                request.setMerchantName(((String)map.get("name")).trim());
                request.setDescription(((String)map.get("description")).trim());
                request.setMerchantPhone(((String)map.get("phone")).trim());
                request.setMerchantLogo(((String)map.get("icon")).trim());
                request.setMerchantImage(((String)map.get("image")).trim());

                if (i%5==0) {
                    request.setMerchantCategory(merchantService.findOne(category));
                    merchantService.createMerchant(request);
                }

                String[] openingTime = new String[]{"10:00","10:00","10:00","10:00","10:00","10:00","10:00"};
                String[] closingTime = new String[]{"22:00","22:00","22:00","22:00","22:00","22:00","00:00"};

                // set working hours
                List<MerchantOfficeHour> hours = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    MerchantOfficeHour hour = new MerchantOfficeHour();
                    hour.setDay(i);
                    hour.setStartTime(openingTime[i]);
                    hour.setEndTime(closingTime[i]);
                    //hour.setMerchantId(merchant);
                    hours.add(hour);
                }
                request.setMerchantOfficeHourList(hours);
                List<Merchant> merchants = new ArrayList<>();
                if (merchantMap.containsKey(category)){
                    merchants = merchantMap.get(category);
                }
                merchants.add(request);
                merchantMap.put(category, merchants);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        List<MerchantCategory> responses = new ArrayList<>();
        for (MerchantCategory merchantCategory : categoryMap){
            List<Merchant> merchants = merchantMap.get(merchantCategory.getMerchantCategoryName());
            if (merchants==null){
                merchants = new ArrayList<>();
            }
            merchantCategory.setMerchantList(merchants);
            responses.add(merchantCategory);
        }
        //List<MerchantCategoryResponse> messageRequests = merchantService.findAllOrderByMerchantCategoryName();
        return getHttpStatus(new Response(responses));
    }

    /**
     * @api {get} /merchant/{merchantId}/detail/ Get Merchant Detail by merchantId
     * @apiName Get Merchant Detail by MerchantId
     * @apiDescription Mengambil informasi merchant berdasarkan merchantId
     * @apiGroup Merchant
     * @apiPermission USER
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response
     * {
     *     data : {
     *       "merchantId" : "5",
     *       "merchantName" : "JCO",
     *       "merchantImage" : "/merchant/images/jco.jpg",
     *       "merchantPhone": "021 - 41231231",
     *       "merchantDescription" : "Lorem Ipsum",
     *       "merchantLogo" : "/merchant/logo/celebrity_fitness.jpg"
     *       "merchantOfficeHour" : [
     *           {
     *              "day" : 1,
     *              "open" : "10:00:00",
     *              "close" : "21:00:00"
     *           },
     *           {
     *              "day" : 2,
     *              "open" : "10:00:00",
     *              "close" : "21:00:00"
     *           },
     *           {
     *              "day" : 7,
     *              "open" : "closed",
     *              "close" : "closed"
     *           },
     *
     *
     *       ]
     *
     *
     *    },
     *     message : null
     * }
     * @apiSuccess {String} merchantId
     * @apiSuccess {String} merchantName
     * @apiSuccess {String} merchantImage
     * @apiSuccess {String} merchantPhone
     * @apiSuccess {String} merchantDescription
     * @apiSuccess {Array} merchantOfficeHour
     * @apiSuccess {Array} merchantList Daftar merchant yang masuk ke dalam kaegory merchantCategoryId
     * @apiSuccess {int} merchantId
     * @apiSuccess {int} day Hari dengan ketentuan Senin = 1, Selasa = 2, Rabu = 3, Kamis = 4, Juma'at = 5, Sabtu = 6, dan Minggu  = 7
     * @apiSuccess {String} open Jam buka
     * @apiSuccess {String} close Jam tutup
     *
     */
    @RequestMapping("/{merchantId}/detail")
    public ResponseEntity<Response> getMessageByUser(@RequestHeader(Application.AUTH) String token,
                                                     @PathVariable("merchantId") long merchantId) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        MerchantDetailResponse merchant = new MerchantDetailResponse(merchantService.findOne(merchantId));
        return getHttpStatus(new Response(merchant));
    }
}
