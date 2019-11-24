<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="View QR Code">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.lw.web.offer.ViewMerchantQrCodeActionBean">
                    <fieldset class="form-group form-group-md">
                        <label>Merchant ID</label>
                        <p>${actionBean.merchant.id}</p>
                    </fieldset>
                    <fieldset class="form-group form-group-md">
                        <label>Merchant Name</label>
                        <p>${actionBean.merchant.name}</p>
                    </fieldset>
                    <img src="${actionBean.url}"/>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>