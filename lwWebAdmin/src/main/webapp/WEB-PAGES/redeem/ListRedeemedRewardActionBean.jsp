<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>
<jsp:useBean id="now" class="java.util.Date"/>
<s:layout-render name="/WEB-PAGES/index.jsp" title="List Redeemed Reward">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.lw.web.member.MessageManagementActionBean" method="get">
                    <div class="p-x-1 col-lg-9">
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label>Member</label>
                                <s:text class="form-control" name="member" id="member"/>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label>Name</label>
                                <s:text class="form-control" name="name" id="name"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label>Code</label>
                                <s:text class="form-control" name="code" id="code"/>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label>Event</label>
                                <s:select name="event" class="form-control chosen-select" tabindex="2">
                                    <s:options-collection collection="${actionBean.events}" value="value" label="label" group="group"/>
                                </s:select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="7" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Code" property="redeem.code" sortable="true" style="width: 5%; text-align: left"/>
                            <d:column title="Member" property="redeem.member.cardNumber" sortable="true" style="width: 5%; text-align: left"/>
                            <d:column title="Name" property="redeem.member.user.fullName" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Create At" property="redeem.createAt" sortable="true" style="width: 10%; text-align: left" decorator="id.co.icg.lw.displaytag.DateTimeFormatDecorator"/>
                            <d:column title="Expired Date" property="redeem.expiredDate" sortable="true" style="width: 10%; text-align: left" decorator="id.co.icg.lw.displaytag.DateFormatDecorator"/>
                            <d:column title="Reward" property="reward.name" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Qty" property="quantity" sortable="true" style="width: 5%; text-align: left"/>
                            <d:column title="Status" property="approvedStatus" sortable="false" style="width: 5%; text-align: left"  decorator="id.co.icg.lw.displaytag.ApprovalStatusDecorator"/>
                            <d:column title="Action" style="width: 10%; text-align: center">
                                <c:if test="${obj.approvedStatus == 0 && obj.redeem.expiredDate !=null && obj.redeem.expiredDate >= now}">
                                    <s:link beanclass="id.co.icg.lw.web.redeem.ListRedeemedRewardActionBean" event="approve" class="buttonconfirm">
                                        <s:param name="redeem" value="${obj}"/>
                                        <s:label for="approve" />
                                    </s:link>
                                    <s:link beanclass="id.co.icg.lw.web.redeem.ListRedeemedRewardActionBean" event="reject" class="buttonconfirm">
                                        <s:param name="redeem" value="${obj}"/>
                                        <s:label for="reject" />
                                    </s:link>
                                </c:if>
                            </d:column>
                            <d:column title="Action Date" property="approvedDate" sortable="false" style="width: 10%; text-align: left" decorator="id.co.icg.lw.displaytag.DateTimeFormatDecorator"/>
                            <d:column title="Action User" property="approvedUser.fullName" sortable="false" style="width: 10%; text-align: left"/>
                            <d:setProperty name="paging.banner.placement" value="bottom" />
                        </d:table>
                    </div>
                </div>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>