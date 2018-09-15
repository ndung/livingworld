<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Add Offer">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.offer.AddOfferActionBean">
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
                        <fieldset class="form-group form-group-md">
                            <label id="label-text">Image File 1</label>
                            <s:file class="form-control" name="fileBean1" accept="image/*" id="fileBean1"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label id="label-text">Image File 2</label>
                            <s:file class="form-control" name="fileBean2" accept="image/*" id="fileBean2"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label id="label-text">Image File 3</label>
                            <s:file class="form-control" name="fileBean3" accept="image/*" id="fileBean3"/>
                        </fieldset>
                        <s:submit name="save" class="btn btn-md btn-primary m-t-1 confirm">Save Offer</s:submit>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>
