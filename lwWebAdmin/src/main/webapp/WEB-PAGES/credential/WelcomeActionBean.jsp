<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Hi, ${actionBean.userSession.fullName}!">
    <s:layout-component name="contents">
    </s:layout-component>
</s:layout-render>