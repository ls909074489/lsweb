<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<c:set var="theme" value="${fns:getTheme()}" />
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title><sitemesh:title/>-<spring:message code="platform.copyright" /></title>
	
	<link rel="shortcut icon" href="${staticPath}/common/favicon.ico">
	<link href="${staticPath}/vendors/bootstrap/css/bootstrap.min.css?v=v3.2.0" rel="stylesheet">
	<link href="${staticPath}/vendors/font-awesome/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link href="${staticPath}/vendors/animate/css/animate.css?v=3.5.2" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="${staticPath}/vendors/iCheck/skins/all.css">
	<link href="${staticPath}/vendors/datepicker/datepicker3.css" rel="stylesheet">
	<!-- Sweet Alert -->
	<link href="${staticPath}/vendors/sweetalert/sweetalert.css" rel="stylesheet">
	
	<link rel="stylesheet" href="${staticPath}/vendors/layer/skin/layer.css" id="layui_layer_skinlayercss" style=""></head>
	<link rel="stylesheet" href="${staticPath}/common/bootstrap-table/bootstrap-table.min.css" />
	
	<!-- 由于使用了自定义标签，jquery必须在之前 -->
	<!-- 全局js -->
	<script src="${staticPath}/vendors/jquery/js/jquery.min.js?v=2.1.4"></script>
	<script src="${staticPath}/common/js/prototype.js?v=1.0"></script>
	<script src="${staticPath}/common/js/func.js?v=1.0"></script>
	
	<link href="${staticPath}/common/css/style.css?v=4.1.0" rel="stylesheet">		
		
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
</head>
<body class="">
	<div class="wrapper wrapper-content animated fadeInRight" style="padding-top: 10px;">
		<sitemesh:body/>
		<script src="${staticPath}/vendors/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
	</div>
</body>
</html>