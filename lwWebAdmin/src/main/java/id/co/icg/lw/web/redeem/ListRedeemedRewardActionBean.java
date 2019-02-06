package id.co.icg.lw.web.redeem;

import id.co.icg.lw.dao.model.app.Event;
import id.co.icg.lw.dao.model.app.Merchant;
import id.co.icg.lw.dao.model.app.RedeemedReward;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.EventManager;
import id.co.icg.lw.manager.MerchantManager;
import id.co.icg.lw.manager.RedeemManager;
import id.co.icg.lw.util.SelectValue;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import org.apache.log4j.Logger;
import org.displaytag.pagination.PaginatedList;

import java.util.ArrayList;
import java.util.List;

@UrlBinding("/redeem/listredeemed.html")
public class ListRedeemedRewardActionBean extends ActionBeanClass {

    Logger log = Logger.getLogger(ListRedeemedRewardActionBean.class);

    private BaseHibernateManager baseHibernateManager;
    private RedeemManager redeemManager;
    private String member;
    private String name;
    private String code;
    private Long event;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getEvent() {
        return event;
    }

    public void setEven(Long event) {
        this.event = event;
    }

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    @SpringBean("redeemManager")
    public void setRedeemManager(RedeemManager redeemManager) {
        this.redeemManager = redeemManager;
    }

    @Before
    public void init() {
    }

    private RedeemedReward redeem;

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(RedeemedReward.class);
        parameter.setAlias("redeemedReward");
        if (getUserSession().getMerchant() != null) {
            parameter.setEqualsOrLikes("reward.merchant.id", getUserSession().getMerchant().getId());
        }
        if (getCode() != null) {
            parameter.setEqualsOrLikes("redeem.code", "%" + getCode() + "%");
        }
        if (getMember() != null) {
            parameter.setEqualsOrLikes("redeem.member.cardNumber", "%" + getMember() + "%");
        }
        if (getName() != null) {
            parameter.setEqualsOrLikes("redeem.member.user.fullName", "%" + getName() + "%");
        }
        if (getEvent() != null && getEvent()>0) {
            parameter.setEqualsOrLikes("reward.event.id", getEvent());
        }
        parameter.setDescOrders("createAt");
        parameter.setMaxRows(10);

        return baseHibernateManager.getList2(parameter, getExtendedPaginated());
    }

    @DontValidate
    public Resolution approve() {
        if (redeem != null) {
            redeemManager.approve(redeem, 1, getUserSession());
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }

    @DontValidate
    public Resolution reject() {
        if (redeem != null) {
            redeemManager.approve(redeem, 2, getUserSession());
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }

    public RedeemedReward getRedeem() {
        return redeem;
    }

    public void setRedeem(RedeemedReward redeem) {
        this.redeem = redeem;
    }

    protected EventManager eventManager;
    protected MerchantManager merchantManager;

    @SpringBean("eventManager")
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @SpringBean("merchantManager")
    public void setMerchantManager(MerchantManager merchantManager) {
        this.merchantManager = merchantManager;
    }

    public List<SelectValue> getEvents() {
        List<SelectValue> pcs = new ArrayList();
        pcs.add(new SelectValue(0L, "ALL"));
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
