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
import org.displaytag.pagination.PaginatedList;

import java.util.ArrayList;
import java.util.List;

@UrlBinding("/redeem/listredeemed.html")
public class ListRedeemedRewardActionBean extends ActionBeanClass {
    private BaseHibernateManager baseHibernateManager;
    private RedeemManager redeemManager;

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
        if (getUserSession().getMerchant()!=null){
            parameter.setEqualsOrLikes("reward.merchant", getUserSession().getMerchant());
        }
        parameter.setMaxRows(10);

        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

    @DontValidate
    public Resolution approve() {
        if(redeem!=null){
            redeemManager.approve(redeem, 1, getUserSession());
            getContext().getMessages().add(new LocalizableMessage("success"));
        } else getContext().getValidationErrors().addGlobalError(new LocalizableError("failed"));
        return new RedirectResolution(this.getClass());
    }

    @DontValidate
    public Resolution reject() {
        if(redeem!=null){
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
