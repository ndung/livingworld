<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Add User">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.user.AddUserActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>User Name</label>
                            <s:text class="form-control" name="appUser.id"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Full Name</label>
                            <s:text class="form-control" name="appUser.fullName"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Password</label>
                            <s:password id="myInput1" class="form-control" name="appUser.password"/>
                            <input type="checkbox" onclick="myFunction1()">Show
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Confirm Password</label>
                            <s:password id="myInput2" class="form-control" name="rePassword"/>
                            <input type="checkbox" onclick="myFunction2()">Show
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>E-Mail</label>
                            <s:text class="form-control" name="appUser.email"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Phone Number</label>
                            <s:text class="form-control" name="appUser.phone"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Status</label>
                            <s:select name="appUser.status" class="form-control chosen-select" tabindex="2">
                                <s:options-collection collection="${actionBean.statusActives}" value="value" label="label" group="group"/>
                            </s:select>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Role</label>
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
                        <s:submit name="save" class="btn btn-md btn-primary m-t-1 confirm">Save User</s:submit>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>


<script>
    function myFunction1() {
        var x = document.getElementById("myInput1");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
    function myFunction2() {
        var x = document.getElementById("myInput2");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
</script>