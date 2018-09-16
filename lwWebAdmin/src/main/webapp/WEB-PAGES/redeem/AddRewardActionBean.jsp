<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Add Reward">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.redeem.AddRewardActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>Name</label>
                            <s:text class="form-control" name="reward.name"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Description</label>
                            <s:textarea class="form-control" name="reward.description"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Point</label>
                            <s:text class="form-control" name="reward.point"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label id="label-text">Image File</label>
                            <s:file class="form-control" name="fileBean" accept="image/*"/>
                        </fieldset>
                        <!--fieldset class="form-group form-group-md">
                            <label>Expired Date</label>
                            <!--s:text class="form-control date" name="reward.expiredDate"/>
                        </fieldset-->
                        <fieldset class="form-group form-group-md">
                            <label>Event</label>
                            <s:select name="reward.event.id" class="form-control chosen-select" tabindex="2">
                                <s:options-collection collection="${actionBean.events}" value="value" label="label" group="group"/>
                            </s:select>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Merchant</label>
                            <s:select name="reward.merchant.id" class="form-control chosen-select" tabindex="2">
                                <s:options-collection collection="${actionBean.merchants}" value="value" label="label" group="group"/>
                            </s:select>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Status</label>
                            <s:select name="active" class="form-control chosen-select" tabindex="2">
                                <s:options-collection collection="${actionBean.statusYNActives}" value="value" label="label" group="group"/>
                            </s:select>
                        </fieldset>
                        <s:submit name="save" class="btn btn-md btn-primary m-t-1">Save Reward</s:submit>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>