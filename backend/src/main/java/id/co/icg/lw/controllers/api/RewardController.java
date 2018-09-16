package id.co.icg.lw.controllers.api;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Redeem;
import id.co.icg.lw.domain.RedeemedReward;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.Reward;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.repositories.MemberRepository;
import id.co.icg.lw.repositories.RedeemRepository;
import id.co.icg.lw.repositories.RewardRepository;
import id.co.icg.lw.repositories.UserRepository;
import id.co.icg.lw.services.reward.RedeemRequest;
import id.co.icg.lw.services.reward.RewardResponse;
import id.co.icg.lw.services.reward.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(Application.API_PATH + "/reward")
public class RewardController extends BaseController {

    @Value("${redeemExpiredHours}")
    private String expiredHours;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private RedeemRepository redeemRepository;

    /**
     * @api {get} /reward/point Get Total Point
     * @apiName Get Total Point
     * @apiGroup Reward
     * @apiPermission USER
     * @apiDescription Mendapatkan total point
     * @apiSuccess {long} balance
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     * "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response Berhasil
     * {
     * data: {
     * "balance" : 1000000
     * },
     * <p>
     * message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     * data: null,
     * message: "Invalid Token"
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
     * @apiSuccess {long} balance Point terakhir setelah melakukan redeem
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     * "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response Berhasil
     * {
     * data: {
     * "balance" : 1000000
     * },
     * <p>
     * message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     * data: null,
     * message: "Invalid Token"
     * }
     */
    @RequestMapping(value = "/redeem", method = RequestMethod.POST)
    public ResponseEntity<Response> redeemReward(@RequestHeader(Application.AUTH) String token,
                                                 @RequestBody RedeemRequest request) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId = getUserId(token);
            User user = userRepository.findOne(userId);
            Member member = memberRepository.findByUser(user);
            List<RedeemedReward> redeemedRewards = new ArrayList<>();
            Gson gson = new Gson();
            Map<String,String> map = gson.fromJson(request.getRewards(), Map.class);
            Redeem redeem = new Redeem();
            redeem.setCode(UUID.randomUUID().toString().substring(0,8).toUpperCase());
            redeem.setMember(member);
            Date date = new Date();
            redeem.setCreateAt(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, Integer.parseInt(expiredHours));
            redeem.setExpiredDate(calendar.getTime());
            for (String key : map.keySet()) {
                Reward reward = rewardRepository.findOne(Long.valueOf(key));
                RedeemedReward obj = new RedeemedReward();
                obj.setRewardId(reward);
                obj.setRedeemId(redeem);
                obj.setQuantity(Integer.parseInt((String) map.get(key)));
                obj.setStatus(0);
                redeemedRewards.add(obj);
            }
            redeem.setRedeemedRewards(redeemedRewards);
            redeemRepository.save(redeem);
            return getHttpStatus(new Response(redeem));
        } catch (Exception e) {
            e.printStackTrace();
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {get} /reward Get Available Rewards
     * @apiName Get Available Rewards
     * @apiGroup Reward
     * @apiPermission USER
     * @apiDescription Mendapatkan daftar reward
     * @apiSuccess {long} rewardId
     * @apiSuccess {String} rewardName
     * @apiSuccess {long} rewardPoint
     * @apiSuccess {long} rewardImage
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     * "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response Berhasil
     * {
     * data: [
     * {
     * "rewardId" : 1
     * "rewardName" : "Bakmi Ayam Spesial"
     * "rewardPoint" : "50000",
     * "rewardImage" : "/image/file/bakmi_spesial.jpg"
     * }
     * <p>
     * ],
     * <p>
     * message: "Success"
     * }
     * @apiExample {json} Response Gagal
     * {
     * data: null,
     * message: "Invalid Token"
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
