<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="List Image">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.ie.web.offer.ListImageOfferActionBean">
                    <s:hidden name="currentOffer"/>
                    <fieldset class="form-group form-group-md">
                        <label id="label-text">File</label>
                        <s:file class="form-control" name="fileBean" accept="image/*" id="text-fileBean"/>
                    </fieldset>
                    <s:submit name="add" class="btn btn-md btn-primary m-t-1 confirm">Add File</s:submit>
                    <s:submit name="back" class="btn btn-md btn-primary m-t-1">Back</s:submit>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="7" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Image" property="id" sortable="false" style="width: 90%; text-align: left" decorator="id.co.icg.ie.displaytag.ImageThumbnailDecorator"/>
                            <d:column title="Action" style="width: 10%; text-align: center">
                                <s:link beanclass="id.co.icg.ie.web.offer.ListImageOfferActionBean" event="delete" class="btn btn-sm confirm">
                                    <s:param name="currentOfferImage" value="${obj}"/>
                                    Delete
                                </s:link>
                            </d:column>
                            <d:setProperty name="paging.banner.placement" value="bottom" />
                        </d:table>
                    </div>
                </div>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>