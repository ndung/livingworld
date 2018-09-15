/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.lw.web;

import com.github.jscookie.javacookie.ConverterException;
import com.github.jscookie.javacookie.CookieSerializationException;
import com.github.jscookie.javacookie.Cookies;
import com.github.jscookie.javacookie.Expiration;
import id.co.icg.lw.dao.model.app.AppAdminMenu;
import id.co.icg.lw.dao.model.app.User;
import id.co.icg.lw.filter.ConstantsUtil;
import id.co.icg.lw.util.PaginatedListImpl;
import id.co.icg.lw.util.SelectValue;
import id.co.icg.lw.util.StaticField;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.codehaus.jackson.map.ObjectMapper;
import org.displaytag.properties.SortOrderEnum;

/**
 *
 * @author Hatta Palino
 */
public class ActionBeanClass extends BaseActionBean {

    protected Long    ALL_FOR_LONG_VALUE   = Long.MAX_VALUE;
    protected Integer ALL_FOR_INT_VALUE    = Integer.MAX_VALUE;
    protected String  ALL_FOR_STRING_VALUE = "ALL_FOR_STRING";
    protected String  ALL_LABEL            = "ALL";
    protected String  UNDEFINED_LABEL      = "UNDEFINED";
    protected Integer pageSize             = 20;
    protected String  query;

    protected Object getSession(String key) {
        return getContext().getSession(key);
    }
    
    public User getUserSession() {
        User user = (User) getContext().getSession(ConstantsUtil.SESSION_USER);
        return user;
    }
    
    protected void setUserSession(User user) {
        getContext().setSession(ConstantsUtil.SESSION_USER, user);
    }
    
    protected void setMenuSession(List<AppAdminMenu> user) {
        getContext().setSession(ConstantsUtil.SESSION_LINK, user);
    }

    public List<AppAdminMenu> getUserMenus() {
        List<AppAdminMenu> menus = (List<AppAdminMenu>) getContext().getSession(ConstantsUtil.SESSION_LINK);
        return menus;
    }
    
    public String getNameClass() {
        return getClass().getName();
    }
    
    public void setPageSize(Integer page) {
        this.pageSize = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    protected String getValueFromAutocomplete(String value) {
        String[] vas = value.split("-");
        if(vas.length > 1) return vas[0].trim();       
        else               return vas[0].trim();
    }
    
    public String getCurrentUrl() {
        String uri = StripesFilter.getConfiguration().getActionResolver().getUrlBinding(getClass());
        if (getContext().getRequest().getQueryString() != null) {
            uri += "?" + getContext().getRequest().getQueryString().replace("&page=" + pageSize, "").replace("page=" + pageSize, "");
        }
        return uri;
    }

    public URL getCurrentUrlComplete() {
        URL url = null;
        String tmp = "";
        try {
            if(getContext().getRequest().getQueryString()!=null){
                tmp = "?" + getContext().getRequest().getQueryString().replace("&page=" + pageSize, "").replace("page=" + pageSize, "");
            }
            url = new URL(getContext().getRequest().getRequestURL() + tmp);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ActionBeanClass.class.getName()).log(Priority.ERROR, null, ex);
        }
        return url;
    }

    public String getLink() {
        String menu = "";
        List<AppAdminMenu> menus = getUserMenus();
        if(menus == null) {
            return menu;
        }
        for (AppAdminMenu menuz : menus) {
            if (menuz.getId().endsWith("0000") && menuz.getUrlPath()!= null) {
                menu   += "<li class=\"px-nav-item\">"
                        + " <a href=\"" + StaticField.rootPath + menuz.getUrlPath()+ "\">"
                        + "     <i class=\"" + menuz.getIcon() + "\"></i>"
                        + "     <span class=\"px-nav-label\">" + menuz.getName()+ "</span>"
                        + " </a>"
                        + "</li>";
            } else if (menuz.getId().endsWith("0000") && menuz.getUrlPath()== null) {
                menu   += "<li class=\"px-nav-item px-nav-dropdown\">"
                        + " <a href=\"#\">"
                        + "     <i class=\"" + menuz.getIcon() + "\"></i>"
                        + "     <span class=\"px-nav-label\">" + menuz.getName()+ "</span>"
                        + " </a>"
                        + " <ul class=\"px-nav-dropdown-menu\">";
                for (AppAdminMenu men : menus) {
                    if(men.getId().startsWith(menuz.getId().substring(0,2))&&!men.getId().equals(menuz.getId())){
                        menu += "<li class=\"px-nav-item\">"
                              + "   <a href=\"" + StaticField.rootPath + men.getUrlPath() + "\">"
                              + "       <span class=\"px-nav-label\">" + men.getName() + "</span>"
                              + "   </a>"
                              + "</li>";
                    }
                }
                menu += "</ul></li>";
            }
        }
        return menu;
    }

    
    protected String getView(Class clazz) {
        String packageName = clazz.getPackage().getName();
        String[] packages = packageName.split("\\.");
        String lastOne = packages [packages.length - 1];
        return getView(lastOne, clazz);
    }
    
    protected String getView(String group, Class clazz) {
        return getView(group, null, clazz);
    }
    
    protected String getView(String group, String fitur, Class clazz) {
        if (fitur == null) {
            return "/WEB-PAGES/" + group + "/" + clazz.getSimpleName() + ".jsp";
        } else {
            return "/WEB-PAGES/" + group + "/" + fitur + "/" + clazz.getSimpleName() + ".jsp";
        }
    }

    protected ValidationErrors getLocalizableError(String localizable, String field) {
        ValidationErrors errors = new ValidationErrors();
        errors.add(field, new LocalizableError(localizable));
        return errors;
    }

    protected String jSon(List lists) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object[] array = lists.toArray(new Object[lists.size()]);
            return mapper.writeValueAsString(array);
        } catch (IOException e) {
            return null;
        }
    }

    protected String jSon(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }
    }

    protected Cookies getCookies() {
        Cookies cookies = Cookies.initFromServlet(getContext().getRequest(), getContext().getResponse());
        return cookies.withConverter(new Cookies.Converter() {
            @Override
            public String convert(String value, String name) throws ConverterException {
                return value;
            }
        });
    }
    
    protected void setCookies(String name, String value) {
        getCookies().set(name, value);
    }
    
    protected String getCookies(String name) {
        return getCookies().get(name);
    }
    
    protected void setCookies(String name, List value) {
        try {
            Cookies.Attributes attributes = Cookies.Attributes.empty();
            attributes.expires(Expiration.days(1));
            attributes.path("");
            getCookies().set(name, value, attributes);
        } catch (CookieSerializationException e) {}
    }

    public List<SelectValue> getPageSizes() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(20, "10"));
        svs.add(new SelectValue(30, "20"));
        svs.add(new SelectValue(40, "30"));
        svs.add(new SelectValue(50, "50"));
        svs.add(new SelectValue(ALL_FOR_INT_VALUE, ALL_LABEL));
        return svs;
    }
    
    public List<SelectValue> getStatusActiveAndAlls() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(ALL_FOR_INT_VALUE, ALL_LABEL));
        svs.addAll(getStatusActives());
        return svs;
    }
    
    public List<SelectValue> getStatusActives() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(1, "Active"));
        svs.add(new SelectValue(0, "Inactive"));
        return svs;
    }
    
    public List<SelectValue> getStatusYNActivesAndAlls() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(ALL_FOR_STRING_VALUE, ALL_LABEL));
        svs.addAll(getStatusYNActives());
        return svs;
    }
    
    public List<SelectValue> getStatusYNActives() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue("Y", "Active"));
        svs.add(new SelectValue("N", "Inactive"));
        return svs;
    }
    
    public List<SelectValue> getStatusApprovalsAndAlls() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(ALL_FOR_INT_VALUE, ALL_LABEL));
        svs.addAll(getStatusApprovals());
        return svs;
    }
    
    public List<SelectValue> getStatusApprovals() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(1, "Approved"));
        svs.add(new SelectValue(2, "Rejected"));
        svs.add(new SelectValue(0, "Pending"));
        return svs;
    }
    
    public List<SelectValue> getCheckBalanceRequestTypes() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue("USSD", "USSD"));
        svs.add(new SelectValue("SMS", "SMS"));
        return svs;
    }
    
    public List<SelectValue> getCheckBalanceRequestTypeAndAlls() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(ALL_FOR_STRING_VALUE, ALL_LABEL));
        svs.addAll(getCheckBalanceRequestTypes());
        return svs;
    }
    
    public List<SelectValue> getSuppliyerTypeAndAlls() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(ALL_FOR_INT_VALUE, ALL_LABEL));
        svs.addAll(getSuppliyerTypes());
        return svs;
    }
    
    public List<SelectValue> getSuppliyerTypes() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(1, "Multi"));
        svs.add(new SelectValue(0, "Single"));
        return svs;
    }
    
    public List<SelectValue> getSwitcherTypeAlls() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(ALL_FOR_INT_VALUE, ALL_LABEL));
        svs.add(new SelectValue(0, "Chip"));
        svs.addAll(getSwitcherTypeNonChipAndHttps());
        svs.addAll(getSwitcherTypeHttps());
        return svs;
    }
    
    public List<SelectValue> getSwitcherTypeNonChipAndHttps() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(1, "Jabber"));
        svs.add(new SelectValue(2, "Telegram"));
        return svs;
    }
    
    public List<SelectValue> getSwitcherTypeHttps() {
        List<SelectValue> svs = new ArrayList<>();
        svs.add(new SelectValue(3, "HttpGet"));
        svs.add(new SelectValue(4, "HttpPost"));
        return svs;
    }
    
    protected PaginatedListImpl getExtendedPaginated() {
        PaginatedListImpl paginatedList = new PaginatedListImpl();
        String sortCriterion = null;
        String thePage = null;
        if (getContext().getRequest() != null) {
            sortCriterion = getContext().getRequest().getParameter(PaginatedListImpl.IRequestParameters.SORT);
            paginatedList.setSortDirection(PaginatedListImpl.IRequestParameters.DESC.equals(getContext().getRequest().getParameter(PaginatedListImpl.IRequestParameters.DIRECTION)) ? SortOrderEnum.DESCENDING : SortOrderEnum.ASCENDING);
            thePage = getContext().getRequest().getParameter(PaginatedListImpl.IRequestParameters.PAGE);
        }
        paginatedList.setSortCriterion(sortCriterion);
        if (thePage != null) {
            int index = Integer.parseInt(thePage) - 1;
            paginatedList.setIndex(index);
        } else {
            paginatedList.setIndex(0);
        }
        return paginatedList;
    }

    @DontValidate
    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution(getView(this.getClass()));
    }
    
    public String getClassName() {
        return this.getClass().getName();
    }
    
}
