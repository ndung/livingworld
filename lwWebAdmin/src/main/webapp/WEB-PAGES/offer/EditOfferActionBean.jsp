<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Edit Offer">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.ie.web.offer.EditOfferActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>Title</label>
                            <s:text class="form-control" name="currentOffer.title"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Short Description</label>
                            <s:text class="form-control" name="currentOffer.shortDescription"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Long Description</label>
                            <s:textarea class="form-control" name="currentOffer.longDescription"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Start Date</label>
                            <s:text class="form-control date" name="currentOffer.startDate"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>End Date</label>
                            <s:text class="form-control date" name="currentOffer.endDate"/>
                        </fieldset>
                        <s:submit name="update" class="btn btn-md btn-primary m-t-1 confirm">Update Offer</s:submit>
                        <s:submit name="delete" class="btn btn-md btn-primary m-t-1 confirm">Delete Offer</s:submit>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>