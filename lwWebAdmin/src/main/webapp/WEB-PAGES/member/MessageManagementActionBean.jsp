<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="List Member">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.ie.web.member.MessageManagementActionBean" method="get">
                    <div class="p-x-1 col-lg-9">
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label>Title</label>
                                <s:text class="form-control" name="title" id="title"/>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label>Content</label>
                                <s:text class="form-control" name="text" id="text"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 form-group">
                                <label>Date From</label>
                                <s:text class="form-control date" name="dateFrom"/>
                            </div>
                            <div class="col-sm-2 form-group">
                                <label>Date To</label>
                                <s:text class="form-control date" name="dateTo"/>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                        <button type="submit" name="add" class="btn btn-md btn-primary">Add Message</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="7" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Title" property="title"  sortable="true" style="width: 20%; text-align: left"/>
                            <d:column title="Message" property="message" sortable="false" style="width: 60%; text-align: left"/>
                            <d:column title="Create At" property="createAt" sortable="true" style="width: 10%; text-align: left" decorator="id.co.icg.ie.displaytag.DateTimeFormatDecorator"/>
                            <d:column title="Action" style="width: 10%; text-align: center">
                                <s:link beanclass="id.co.icg.ie.web.member.EditMessageActionBean" class="btn btn-sm">
                                    <s:param name="message" value="${obj}"/>
                                    Edit
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