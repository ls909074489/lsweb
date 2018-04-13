<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.online.title" /></title>
  <meta name="decorator" content="list"/>
  
  <script type="text/javascript">
	  $.ajax({
			type : "POST",
			data :{"uuid": '${entity.uuid}'},
			url : "${adminPath}/sys/online/getOnLineUser",
			async : true,
			dataType : "json",
			success : function(data) {
				var str="<tr><th>登陆名</th><th>用户名</th><th>用户IP</th><th>登陆时间</th><th>最后访问时间</th><th>回话到期 ttl(ms)</th><th>会话ID</th></tr>";
				for(var i=0;i<data.length;i++){
					console.info(data[i]);
					str+="<tr>"+
						  "<td>"+data[i].username+"</td>"+
						  "<td>"+data[i].realname+"</td>"+
						  "<td>"+data[i].host+"</td>"+
						  "<td>"+data[i].startTime+"</td>"+
						  "<td>"+data[i].lastLoginTime+"</td>"+
						  "<td>"+data[i].timeout+"</td>"+
						  "<td>"+data[i].sessionId+"</td>"+
						  "</tr>";
				}
				$("#newOnlineTable").html(str);
			},
			error : function(data) {
				//YYUI.promAlert(YYMsg.alertMsg('sys-submit-http',null));
			}
		});
  	
  </script>
</head>
<body title="<spring:message code="sys.online.title" />">
<div>
	<table  id="newOnlineTable" class="ui-jqgrid-htable ui-common-table table table-bordered"></table>
</div>
<grid:grid id="onlineGridId" url="${adminPath}/sys/online/ajaxList" sortname="startTimestamp" sortorder="desc">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="用户"  name="username"  query="true"  />
    <grid:column label="用户主机IP"  name="host" query="true" />
    <grid:column label="系统主机IP"  name="systemHost"  />
    <grid:column label="登录时间"  name="startTimestamp" width="140"  dateformat='yyyy-MM-dd hh:mm:ss'/>
    <grid:column label="最后访问时间"  name="lastAccessTime"  width="140"  dateformat='yyyy-MM-dd hh:mm:ss' />
    <grid:column label="状态"  name="status" dict="onlinestatus" />
    <grid:column label="User-Agent"  name="userAgent"  />
    <grid:column label="用户会话ID"  name="id"  />
 
	<grid:toolbar title="强制退出" btnclass="btn-danger" icon="fa-trash-o" function="toolbarSelectConfirm"  url="${adminPath}/sys/online/forceLogout"  tipMsg="您确定要强制退出这些信息么，请谨慎操作！"/>
 	
	<grid:toolbar  function="search"  />
	<grid:toolbar  function="reset" />
</grid:grid>
</body>
</html>