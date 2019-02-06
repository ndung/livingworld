<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="List Member">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.lw.web.member.MemberManagementActionBean" method="get">
                    <div class="p-x-1 col-lg-5">
                        <fieldset class="form-group form-group-md">
                            <label>Card Number</label>
                            <s:text class="form-control" name="id"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Full Name</label>
                            <s:text class="form-control" name="fullName"/>
                        </fieldset>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="7" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="Card Number" property="cardNumber" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Member Name" property="user.fullName" sortable="false" style="width: 20%; text-align: left"/>
                            <d:column title="Member Type" property="memberType.name" sortable="true" style="width: 15%; text-align: left"/>
                            <d:column title="Gender" property="gender.name" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Date of Birth" property="dateOfBirth" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Email" property="email" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Mobile Number" property="mobileNumber" sortable="true" style="width: 10%; text-align: left"/>
                            <d:column title="Create At"  property="createAt" sortable="false" style="width: 10%; text-align: center" decorator="id.co.icg.lw.displaytag.DateTimeFormatDecorator"/>
                            <d:setProperty name="paging.banner.placement" value="bottom" />
                        </d:table>
                    </div>
                </div>
            </div>
        </div>
    </s:layout-component>
</s:layout-render>