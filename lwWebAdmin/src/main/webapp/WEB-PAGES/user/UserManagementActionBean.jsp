<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="User Management">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.user.UserManagementActionBean">
                    <div class="p-x-1 col-lg-5">
                        <fieldset class="form-group form-group-md">
                            <label>User Name</label>
                            <s:text class="form-control" name="id"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Full Name</label>
                            <s:text class="form-control" name="fullName"/>
                        </fieldset>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                        <button type="submit" name="add" class="btn btn-md btn-primary">Add New User</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="1" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="User Name" property="id" sortable="false" style="width: 15%; text-align: center"/>
                            <d:column title="Full Name" property="fullName" sortable="false" style="width: 20%; text-align: center"/>
                            <d:column title="Email" property="email" sortable="false" style="width: 15%; text-align: center"/>
                            <d:column title="Phone" property="phone" sortable="false" style="width: 10%; text-align: center"/>
                            <d:column title="Role" property="appRole.name" sortable="false" style="width: 10%; text-align: center"/>
                            <d:column title="Merchant" property="merchant.name" sortable="false" style="width: 15%; text-align: center"/>
                            <d:column title="Status" property="status" sortable="false" style="width: 5%; text-align: center" decorator="id.co.icg.lw.displaytag.StatusFormatDecorator"/>
                            <d:column title="Action" style="width: 10%; text-align: center">
                                <s:link beanclass="id.co.icg.lw.web.user.EditUserActionBean" class="btn btn-sm">
                                    <s:param name="appUser" value="${obj}"/>
                                    Edit
                                </s:link>
                                <c:if test="${obj.status == 1}">
                                    <s:link beanclass="id.co.icg.lw.web.user.UserManagementActionBean" event="deactivate" class="btn btn-sm confirm">
                                        <s:param name="appUser" value="${obj}"/>
                                        Deactivate
                                    </s:link>
                                </c:if>
                                <c:if test="${obj.status == 0}">
                                    <s:link beanclass="id.co.icg.lw.web.user.UserManagementActionBean" event="activate" class="btn btn-sm confirm">
                                        <s:param name="appUser" value="${obj}"/>
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