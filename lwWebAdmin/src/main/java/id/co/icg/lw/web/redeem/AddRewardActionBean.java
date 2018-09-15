package id.co.icg.lw.web.redeem;

import id.co.icg.lw.dao.model.app.Event;
import id.co.icg.lw.dao.model.app.Merchant;
import id.co.icg.lw.dao.model.app.Reward;
import id.co.icg.lw.manager.EventManager;
import id.co.icg.lw.manager.MerchantManager;
import id.co.icg.lw.manager.RewardManager;
import id.co.icg.lw.util.SelectValue;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

import java.util.ArrayList;
import java.util.List;

@UrlBinding("/redeem/addreward.html")
public class AddRewardActionBean extends ActionBeanClass {

    protected FileBean fileBean;

    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) { this.fileBean = fileBean; }

    protected RewardManager rewardManager;
    protected EventManager eventManager;
    protected MerchantManager merchantManager;
    
    @ValidateNestedProperties({
        @Validate(field = "name", required = true),
        @Validate(field = "description", required = true),
        @Validate(field = "point", required = true),
        @Validate(field = "event.id", required = true),
        @Validate(field = "merchant.id", required = true)
    })

    private Reward reward;

    @SpringBean("rewardManager")
    public void setRewardManager(RewardManager rewardManager) {
        this.rewardManager = rewardManager;
    }

    @SpringBean("eventManager")
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @SpringBean("merchantManager")
    public void setMerchantManager(MerchantManager merchantManager) {
        this.merchantManager = merchantManager;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
        if (fileBean == null) {
            errors.add("fileBean", new LocalizableError("file.not.found"));
        } else if (!(fileBean.getFileName().endsWith(".jpeg")||fileBean.getFileName().endsWith(".jpg")||fileBean.getFileName().endsWith(".png"))) {
            errors.add("fileBean", new LocalizableError("file.wrong.format"));
        }
    }
    
    @DontValidate
    public Resolution back() {
        return new RedirectResolution(RewardManagementActionBean.class);
    }
    
    public Resolution save() {
        if(rewardManager.saveReward(reward, fileBean)) {
            getContext().getMessages().add(new LocalizableMessage("success"));
        }
        else {
            getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        }
        return new RedirectResolution(RewardManagementActionBean.class);
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public List<SelectValue> getEvents() {
        List<SelectValue> pcs = new ArrayList();
        for (Event event : eventManager.getEvents()) {
            pcs.add(new SelectValue(event.getId(), event.getName()));
        }
        return pcs;
    }

    public List<SelectValue> getMerchants() {
        List<SelectValue> pcs = new ArrayList();
        for (Merchant merchant : merchantManager.getMerchants()) {
            pcs.add(new SelectValue(merchant.getId(), merchant.getName()));
        }
        return pcs;
    }
}

