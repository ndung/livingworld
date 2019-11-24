<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Edit User">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.user.EditUserActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>User Name (*)</label>
                            <s:text class="form-control" name="appUser.id" readonly="true"/>
                            <s:hidden name="appUser"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Full Name (*)</label>
                            <s:text class="form-control" name="appUser.fullName"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>E-mail (*)</label>
                            <s:text class="form-control" name="appUser.email"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Phone Number (*)</label>
                            <s:text class="form-control" name="appUser.phone"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Role (*)</label>
                            <s:select name="appUser.appRole.id" class="form-control chosen-select" tabindex="2">
                                <s:options-collection collection="${actionBean.roles}" value="value" label="label" group="group"/>
                            </s:select>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Merchant</label>
                            <s:select name="appUser.merchant.id" class="form-control chosen-select" tabindex="2">
                                <s:options-collection collection="${actionBean.merchants}" value="value" label="label" group="group"/>
                            </s:select>
                        </fieldset>
                        <s:submit name="update" class="btn btn-md btn-primary m-t-1 confirm">Update User</s:submit>
                        <s:submit name="delete" class="btn btn-md btn-primary m-t-1 confirm">Delete User</s:submit>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>