package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.reward.RedeemRequest;
import id.co.icg.lw.services.reward.RewardResponse;
import id.co.icg.lw.services.reward.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Application.API_PATH + "/reward")
public class RewardController extends BaseController {

    @Autowired
    private RewardService rewardService;

    /**
     * @api {get} /reward/point Get Total Point
     * @apiName Get Total Point
     * @apiGroup Reward
     * @apiPermission USER
     * @apiDescription Mendapatkan total point
     *
     * @apiSuccess {long} balance
     *
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *

     * @apiExample {json} Response Berhasil
     * {
     *     data: {
     *         "balance" : 1000000
     *     },
     *
     *     message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "/point", method = RequestMethod.GET)
    public ResponseEntity<Response> getUserPoint(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            long balance = rewardService.getPoint(userId);
            return getHttpStatus(new Response(balance));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /reward/redeem Redeem Reward
     * @apiName Redeem Reward
     * @apiGroup Reward
     * @apiPermission USER
     * @apiDescription Melakukan penukaran point
     *
     * @apiSuccess {long} balance Point terakhir setelah melakukan redeem
     *
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *

     * @apiExample {json} Response Berhasil
     * {
     *     data: {
     *         "balance" : 1000000
     *     },
     *
     *     message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "/redeem", method = RequestMethod.POST)
    public ResponseEntity<Response> redeemReward(@RequestHeader(Application.AUTH) String token,
                                                 @RequestBody RedeemRequest redeemRequest) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            long balance = rewardService.redeemPoint(userId, redeemRequest);
            return getHttpStatus(new Response(balance));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {get} /reward Get Available Rewards
     * @apiName Get Available Rewards
     * @apiGroup Reward
     * @apiPermission USER
     * @apiDescription Mendapatkan daftar reward
     *
     * @apiSuccess {long} rewardId
     * @apiSuccess {String} rewardName
     * @apiSuccess {long} rewardPoint
     * @apiSuccess {long} rewardImage
     *
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *

     * @apiExample {json} Response Berhasil
     * {
     *     data: [
     *              {
     *                  "rewardId" : 1
     *                  "rewardName" : "Bakmi Ayam Spesial"
     *                  "rewardPoint" : "50000",
     *                  "rewardImage" : "/image/file/bakmi_spesial.jpg"
     *              }
     *
     *     ],
     *
     *     message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Response> getReward(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            List<RewardResponse> rewards = rewardService.getRewards();
            return getHttpStatus(new Response(rewards));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

}
