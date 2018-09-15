<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="My Profile">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.user.MyAccountActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>User Name</label>
                            <s:text class="form-control" name="appUser.id" readonly="true"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Full Name</label>
                            <s:text class="form-control" name="fullName" value="${actionBean.appUser.fullName}"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>E-Mail</label>
                            <s:text class="form-control" name="email" value="${actionBean.appUser.email}"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Phone Number</label>
                            <s:text class="form-control" name="phone" value="${actionBean.appUser.phone}"/>
                        </fieldset>
                        <s:submit name="update" class="btn btn-md btn-primary m-t-1 confirm">Update Profile</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>