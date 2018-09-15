<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Change Password">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.user.ChangePinActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>Old Password</label>
                            <s:password class="form-control" name="password" placeholder="Enter old password.."/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>New Password</label>
                            <s:password class="form-control" name="newPassword" placeholder="Enter new password.."/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Confirm New Password</label>
                            <s:password class="form-control" name="reNewPassword" placeholder="Enter confirm new password.."/>
                        </fieldset>
                        <s:submit name="update" class="btn btn-md btn-primary m-t-1">Update Password</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>