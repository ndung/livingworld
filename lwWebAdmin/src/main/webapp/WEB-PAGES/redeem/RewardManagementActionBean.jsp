<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Reward">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form class="form-horizontal" beanclass="id.co.icg.lw.web.redeem.RewardManagementActionBean">
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
                        <button type="submit" name="add" class="btn btn-md btn-primary">Add New Reward</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="1" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Name" property="name" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Description" property="description" sortable="true" style="width: 20%; text-align: left"/>
                            <d:column title="Image" property="image" sortable="false" style="width: 15%; text-align: left" decorator="id.co.icg.lw.displaytag.ImageThumbnailDecorator"/>
                            <d:column title="Point" property="point" sortable="true" style="width: 5%; text-align: left"/>
                            <!--d:column title="Expired Date"  property="expiredDate" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateFormatDecorator"/-->
                            <d:column title="Event" property="event.name" sortable="false" style="width: 20%; text-align: left"/>
                            <d:column title="Merchant" property="merchant.name" sortable="false" style="width: 10%; text-align: left"/>
                            <d:column title="Action" style="width: 10%; text-align: center">
                                <s:link beanclass="id.co.icg.lw.web.redeem.EditRewardActionBean" class="btn btn-sm">
                                    <s:param name="reward" value="${obj}"/>
                                    Edit
                                </s:link>
                                <c:if test="${obj.active == 'Y'}">
                                    <s:link beanclass="id.co.icg.lw.web.redeem.RewardManagementActionBean" event="deactivate" class="btn btn-sm confirm">
                                        <s:param name="reward" value="${obj}"/>
                                        Deactivate
                                    </s:link>
                                </c:if>
                                <c:if test="${obj.active == 'N'}">
                                    <s:link beanclass="id.co.icg.lw.web.redeem.RewardManagementActionBean" event="activate" class="btn btn-sm confirm">
                                        <s:param name="reward" value="${obj}"/>
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