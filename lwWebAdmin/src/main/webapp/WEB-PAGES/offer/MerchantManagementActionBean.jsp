<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="List Merchants">
    <s:layout-component name="contents">
        <div class="panel">
            <div class="panel-body">
                <s:form beanclass="id.co.icg.lw.web.offer.MerchantManagementActionBean" method="get">
                    <div class="p-x-1 col-lg-5">
                        <fieldset class="form-group form-group-md">
                            <label>ID</label>
                            <s:text class="form-control" name="title"/>
                        </fieldset>
                        <fieldset class="form-group form-group-md">
                            <label>Name</label>
                            <s:text class="form-control" name="description"/>
                        </fieldset>
                        <button type="submit" class="btn btn-md btn-primary">Find</button>
                    </div>
                </s:form>
            </div>
            <div class="panel-body">
                <div class="table-primary">
                    <div class="dataTables_table_wrapper">
                        <d:table class="table table-striped table-bordered dataTable text-center" name="${actionBean.list}" id="obj" requestURI="" defaultsort="7" partialList="true" size="${actionBean.list.fullListSize}" pagesize="${actionBean.list.objectsPerPage}">
                            <d:column title="ID" property="id" sortable="false" style="width: 20%; text-align: left"/>
                            <d:column title="Name"  property="name" sortable="false" style="width: 35%; text-align: center" />
                            <d:column title="Category" property="category" sortable="false" style="width: 30%; text-align: center"/>
                            <d:column title="Action" style="width: 15%; text-align: center">
                                <s:link beanclass="id.co.icg.lw.web.offer.ViewMerchantQrCodeActionBean" class="btn btn-sm">
                                    <s:param name="merchant" value="${obj}"/>
                                    View QR Code
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
