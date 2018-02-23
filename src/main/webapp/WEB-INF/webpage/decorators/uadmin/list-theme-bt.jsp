<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <title><sitemesh:title/>-<spring:message code="platform.copyright" /></title>
	<%@include file="header.jsp" %>
	
	<script type="text/javascript" src="${staticPath}/vendors/Validform_v5.3.2/js/Validform_v5.3.2.js"></script>
	<%-- <link href="${staticPath}/vendors/bootstrap/css/bootstrap.min.css?v=v3.2.0" rel="stylesheet"> --%>
	<link rel="stylesheet" href="${staticPath}/vendors/layer/skin/layer.css" id="layui_layer_skinlayercss" style=""></head>
	<link rel="stylesheet" href="${staticPath}/common/bootstrap-table/bootstrap-table.min.css" />
	
	<script src="${staticPath}/vendors/layer/layer.min.js"></script>
	
	<!-- Bootstrap table --> 
	<script src="${staticPath}/common/bootstrap-table/bootstrap-table.min.js"></script> 
	<script src="${staticPath}/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script> 
	<script src="${staticPath}/common/bootstrap-table/tableExport.js"></script> 
	<script src="${staticPath}/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script> 
	<!-- 自定义js --> 
	<script src="${staticPath}/common/yy/yy-ui-utils.js?v=46"></script> 
	<script src="${staticPath}/common/yy/zz-list-utils.js?v=51"></script> 
	
	<sitemesh:head/>
	<style>
	   .uadmin-grid-margin{
	   	   margin-top:8px;
	   }
	</style>
</head>

<body class=" ">
    <div>
        <!--BEGIN BACK TO TOP--><a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <%@include file="topbar.jsp"%>
        <div id="wrapper">
            <%@include file="left.jsp"%>
            <!--BEGIN PAGE WRAPPER-->
            <div id="page-wrapper">
                <!--BEGIN TITLE & BREADCRUMB PAGE-->
                <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
                    <div class="page-header pull-left">
                        <div class="page-title"><sitemesh:getProperty property='body.title'/></div>
                    </div>
                    <ol class="breadcrumb page-breadcrumb">
                        <li><i class="fa fa-home"></i>&nbsp;<a href="${adminPath}">首页</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        <c:forEach items="${fn:split(pmenuids, '/')}" var="pmenuid">
                        <c:set var="menu" value="${fns:getMenuById(pmenuid)}" />
                        <li><a href="#">${menu.name}</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                        </c:forEach>
                        <li class="active">${currentMenu.name}</li>
                    </ol>
                    <div class="btn btn-blue reportrange hide"><i class="fa fa-calendar"></i>&nbsp;<span></span>&nbsp;report&nbsp;<i class="fa fa-angle-down"></i>
                        <input type="hidden" name="datestart" />
                        <input type="hidden" name="endstart" />
                    </div>
                    <div class="clearfix"></div>
                </div>
                <!--END TITLE & BREADCRUMB PAGE-->
                <!--BEGIN CONTENT-->
                <div class="page-content">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="portlet box">
                                <div class="portlet-body">
                                    <sitemesh:body/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
			    <!--END CONTENT-->
    </div>
    <!--BEGIN FOOTER-->
    <div id="footer">
        <div class="copyright"><spring:message code="sys.site.bottom.copyright" /></div>
    </div>
    <!--END FOOTER-->
    <!--END PAGE WRAPPER-->
    </div>
    </div>
    <%@include file="footer.jsp" %>
    <!-- list新增  -->
</body>

</html>