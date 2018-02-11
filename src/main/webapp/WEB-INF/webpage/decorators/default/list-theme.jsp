<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title><sitemesh:title/>-<spring:message code="platform.copyright" /></title>
	<%@include file="list-header.jsp" %>		
	<sitemesh:head/>
</head>
<body class="">
	<div class="wrapper wrapper-content animated fadeInRight" style="padding-top: 10px;">
		<sitemesh:body/>
	</div>
    <%@include file="list-footer.jsp" %>		
</body>
</html>