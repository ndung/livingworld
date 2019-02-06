<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="List Special Offers">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.lw.web.offer.OfferManagementActionBean" method="get">
                    <div class="p-x-1 col-lg-5">
                        <fieldset class="form-group form-group-md">
                            <label>Title</label>
                            <s:text class="form-control" name="title"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Description</label>
                            <s:text class="form-control" name="description"/>
                        </fieldset>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                        <button type="submit" name="add" class="btn btn-md btn-primary">Add Message</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="7" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Title"              property="title"         sortable="false" style="width: 10%; text-align: left"/>
                            <d:column title="Short Description"  property="shortDescription" sortable="false" style="width: 15%; text-align: center" />
                            <d:column title="Long Description"   property="longDescription" sortable="true" style="width: 30%; text-align: center"/>
                            <d:column title="Create At"  property="createAt" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateTimeFormatDecorator"/>
                            <d:column title="Start Date"         property="startDate" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateFormatDecorator"/>
                            <d:column title="End Date"           property="endDate" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateFormatDecorator"/>
                            <d:column title="Action" style="width: 15%; text-align: center">

                                <s:link beanclass="id.co.icg.lw.web.offer.EditOfferActionBean" class="btn btn-sm">
                                    <s:param name="currentOffer" value="${obj}"/>
                                    Edit
                                </s:link>
                                <s:link beanclass="id.co.icg.lw.web.offer.ListImageOfferActionBean" class="btn btn-sm">
                                    <s:param name="currentOffer" value="${obj}"/>
                                    Images
                                </s:link>
                                <c:if test="${obj.active == 'Y'}">
                                    <s:link beanclass="id.co.icg.lw.web.offer.OfferManagementActionBean" event="deactivate" class="btn btn-sm confirm">
                                        <s:param name="currentOffer" value="${obj}"/>
                                        Deactivate
                                    </s:link>
                                </c:if>
                                <c:if test="${obj.active == 'N'}">
                                    <s:link beanclass="id.co.icg.lw.web.offer.OfferManagementActionBean" event="activate" class="btn btn-sm confirm">
                                        <s:param name="currentOffer" value="${obj}"/>
                                        Activate
                                    </s:link>
                                </c:if>
                            </d:column>
                            <d:setProperty name="paging.banner.placement" value="bottom" />
                        </d:table>
                    </div>
                </div>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>
