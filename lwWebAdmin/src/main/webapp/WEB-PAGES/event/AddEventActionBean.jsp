<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Add Event">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.ie.web.event.AddEventActionBean">
                    <div class="p-x-1 col-md-5">
                        <fieldset class="form-group form-group-md">
                            <label>Title</label>
                            <s:text class="form-control" name="event.name"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Message</label>
                            <s:textarea class="form-control" name="event.description"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Start Date</label>
                            <s:text class="form-control date" name="event.startDate"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>End Date</label>
                            <s:text class="form-control date" name="event.endDate"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label id="label-text">File</label>
                            <s:file class="form-control" name="fileBean" accept="image/*"/>
                        </fieldset>
                        <s:submit name="add" class="btn btn-md btn-primary m-t-1">Add Event</s:submit>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>