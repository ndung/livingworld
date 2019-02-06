package id.co.icg.lw.web.redeem;

import id.co.icg.lw.dao.model.app.Reward;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.RewardManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/redeem/rewardmanagement.html")
public class RewardManagementActionBean extends ActionBeanClass {

    private String name;
    private String description;
    private Reward reward;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    private BaseHibernateManager baseHibernateManager;

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    private RewardManager rewardManager;

    @SpringBean("rewardManager")
    public void setRewardManager(RewardManager rewardManager) {
        this.rewardManager = rewardManager;
    }

    @Before
    public void init() {
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
    }

    public Resolution add() {
        return new ForwardResolution(AddRewardActionBean.class);
    }

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(Reward.class);
        if (getName() != null) {
            parameter.setEqualsOrLikes("name", "%" + getName() + "%");
        }
        if (getDescription() != null) {
            parameter.setEqualsOrLikes("description", "%" + getDescription() + "%");
        }
        parameter.setMaxRows(10);
        parameter.setDescOrders("createAt");
        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

    @DontValidate
    public Resolution activate() {
        if (reward != null) {
            boolean bool = rewardManager.activateReward("Y", reward);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }

    @DontValidate
    public Resolution deactivate() {
        if (reward != null) {
            boolean bool = rewardManager.activateReward("N", reward);
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }

}

