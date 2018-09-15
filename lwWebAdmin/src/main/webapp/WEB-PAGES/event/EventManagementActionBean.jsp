<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Event">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.event.EventManagementActionBean">
                    <div class="p-x-1 col-lg-5">
                        <fieldset class="form-group form-group-md">
                            <label>Name</label>
                            <s:text class="form-control" name="name"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Description</label>
                            <s:text class="form-control" name="description"/>
                        </fieldset>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                        <button type="submit" name="add" class="btn btn-md btn-primary">Add New Event</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="1" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Name" property="name" sortable="true" style="width: 15%; text-align: left"/>
                            <d:column title="Description" property="description" sortable="true" style="width: 30%; text-align: left"/>
                            <d:column title="Image" property="image" sortable="false" style="width: 25%; text-align: left" decorator="id.co.icg.lw.displaytag.ImageThumbnailDecorator"/>
                            <d:column title="Start Date"  property="startDate" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateFormatDecorator"/>
                            <d:column title="End Date"  property="endDate" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateFormatDecorator"/>
                            <d:column title="Action" style="width: 10%; text-align: center">
                                <s:link beanclass="id.co.icg.lw.web.event.EditEventActionBean" class="btn btn-sm">
                                    <s:param name="event" value="${obj}"/>
                                    Edit
                                </s:link>
                                <c:if test="${obj.active == 'Y'}">
                                    <s:link beanclass="id.co.icg.lw.web.event.EventManagementActionBean" event="deactivate" class="btn btn-sm confirm">
                                        <s:param name="event" value="${obj}"/>
                                        Deactivate
                                    </s:link>
                                </c:if>
                                <c:if test="${obj.active == 'N'}">
                                    <s:link beanclass="id.co.icg.lw.web.event.EventManagementActionBean" event="activate" class="btn btn-sm confirm">
                                        <s:param name="event" value="${obj}"/>
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