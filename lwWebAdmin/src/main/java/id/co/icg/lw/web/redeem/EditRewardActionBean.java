package id.co.icg.lw.web.redeem;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

@UrlBinding("/redeem/editreward.html")
public class EditRewardActionBean extends AddRewardActionBean {

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        if (getReward().getPoint()<0){
            errors.add("point", new LocalizableError("point.larger.than.zero"));
        }
    }

    public Resolution update() {
        rewardManager.editReward(getReward(), fileBean);
        return back();
    }

    @DontValidate
    public Resolution delete() {
        rewardManager.deleteReward(getReward());
        return back();
    }
}

