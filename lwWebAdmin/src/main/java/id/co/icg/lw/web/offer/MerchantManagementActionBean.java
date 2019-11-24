package id.co.icg.lw.web.offer;

import id.co.icg.lw.dao.model.app.Merchant;
import id.co.icg.lw.dao.util.ParameterDao;
import id.co.icg.lw.manager.BaseHibernateManager;
import id.co.icg.lw.manager.MerchantManager;
import id.co.icg.lw.web.ActionBeanClass;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.displaytag.pagination.PaginatedList;

@UrlBinding("/merchant/merchantmanagement.html")
public class MerchantManagementActionBean extends ActionBeanClass {

    private Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private BaseHibernateManager baseHibernateManager;

    @SpringBean("baseHibernateManager")
    public void setBaseHibernateManager(BaseHibernateManager baseHibernateManager) {
        this.baseHibernateManager = baseHibernateManager;
    }

    private MerchantManager merchantManager;

    @SpringBean("merchantManager")
    public void setMerchantManager(MerchantManager merchantManager) {
        this.merchantManager = merchantManager;
    }

    @ValidationMethod
    public void otherCheck(ValidationErrors errors) {
    }

    public PaginatedList getList() {
        ParameterDao parameter = new ParameterDao(Merchant.class);
        if (getTitle() != null) {
            parameter.setEqualsOrLikes("id", "%" + getTitle() + "%");
        }
        if (getDescription() != null) {
            parameter.setEqualsOrLikes("name", "%" + getDescription() + "%");
        }
        parameter.setMaxRows(10);
        return baseHibernateManager.getList(parameter, getExtendedPaginated());
    }

}

