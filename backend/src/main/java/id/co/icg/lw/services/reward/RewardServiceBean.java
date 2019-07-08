package id.co.icg.lw.services.reward;

import com.google.gson.internal.LinkedTreeMap;
import id.co.icg.lw.domain.Redeem;
import id.co.icg.lw.domain.Reward;
import id.co.icg.lw.domain.master.MemberType;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.repositories.MemberRepository;
import id.co.icg.lw.repositories.MemberTypeRepository;
import id.co.icg.lw.repositories.RedeemRepository;
import id.co.icg.lw.repositories.RewardRepository;
import id.co.icg.lw.services.livingWorld.LivingWorldApiService;
import id.co.icg.lw.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardServiceBean implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private RedeemRepository redeemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberTypeRepository memberTypeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LivingWorldApiService livingWorldApiService;


    @Override
    public List<Reward> getRewardList(String userId) {
        try {
            User user = userService.findOne(userId);
            Member member = memberRepository.findByUser(user);
            MemberType memberType = memberTypeRepository.findOne(member.getMemberType());
            List<LinkedTreeMap> objects = (List<LinkedTreeMap>) livingWorldApiService.getMasterReward
                    (memberType.getName().toLowerCase());
            for (LinkedTreeMap obj : objects) {
                Reward reward = new Reward();
                reward.setId((String) obj.get("rewardId"));
                reward.setActive("Y");
                reward.setRewardName((String) obj.get("rewardName"));
                reward.setRewardDescription((String) obj.get("description"));
                reward.setRewardImage((String) obj.get("image"));
                reward.setRewardPoint(Integer.parseInt((String) obj.get("point")));
                reward.setStock(Integer.parseInt((String) obj.get("stock")));
                try { rewardRepository.save(reward); }catch (Exception ex){}
            }
            return rewardRepository.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Redeem> getRewards(String userId) {
        User user = userService.findOne(userId);
        Member member = memberRepository.findByUser(user);
        return redeemRepository.findRedeemsByCardNumber(member.getCardNumber());
    }

    @Override
    public long redeemPoint(String userId, RedeemRequest redeemRequest) {
        return 0;
    }
}
