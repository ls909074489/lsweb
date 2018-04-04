<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<c:set var="serviceurl" value="${adminPath}/sms/smssendlog" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="list-bt"/>
    <title>bootstrapTable</title>
    
</head>

<body class="gray-bg">
   <script type="text/javascript">
		var serviceurl = "${serviceurl}";
	</script>
	<div class="yy-toolbar">
	      <button id="yy-btn-add" class="btn btn-sm btn-primary"><i class="fa fa-plus"></i> 添加</button>
	      <button id="yy-btn-edit" class="btn btn-sm btn-success"><i class="fa fa-file-text-o"></i> 修改</button>
	      <button id="yy-btn-remove" class="btn btn-sm btn-danger"><i class="fa fa-trash-o"></i> 删除</button>
	      <button id="yy-btn-refresh" class="btn btn-sm btn-info"><i class="fa fa-refresh"></i> 刷新</button>
	</div>
	<div role="form" class="yy-searchbar form-inline">
		<form id="yy-form-query">
			<div class="">
				<label for="query.phone||like" class="control-label">联系电话 </label>
				<input type="text" autocomplete="on" name="query.phone||like" id="query.phone||like" class="form-control input-sm queryform" value="">
				
				<label for="search_LIKE_senddata" class="control-label">发送数据 </label>
				<input type="text" autocomplete="on" name="search_LIKE_senddata" id="search_LIKE_senddata" class="form-control input-sm queryform">
				
				<button id="yy-btn-search" type="button" class="btn btn-sm btn-info">
					<i class="fa fa-search"></i>查询
				</button>
				<button id="yy-btn-clear" type="reset" class="btn btn-sm btn-info btn-warning">
					<i class="fa fa-trash-o"></i>清空
				</button>
			</div>
		</form>
	</div>
	<div>
		<table id=ls-table></table>
	</div>
	



	<script>
		var _tableCols= [
		{field: 'Number',title: '序号',align: 'center',formatter: function (value, row, index) {return index+1;}},
		{checkbox: true},
		{field: 'operate',title: '操作',align: 'center',clickToSelect:false,events: operateEvents,formatter: renderActionCol},
		{field: 'id', title: 'ID', sortable: true, align: 'center', halign: 'center'},
		{field: 'phone', title: '联系电话', align: 'center', halign: 'center'},
		{field: 'templateId', title: '模板ID', halign: 'center'},
		{field: 'templateContent', title: '模板内容', align: 'center', sortable: true, halign: 'center'},
		{field: 'senddata', title: '发送数据', halign: 'center'},
		{field: 'code', title: '返回码', align: 'center', halign: 'center'},
		{field: 'msg', title: '返回消息', halign: 'center'},
		{field: 'responseDate', title: '响应时间', align: 'center', halign: 'center'}
		 ];
		 
		  $(document).ready(function() {
			    _queryData = $("#yy-form-query").serializeArray();
				//1.初始化Table
			    var oTable = new TableInit('${serviceurl}/ajaxListNew');
			    oTable.Init();
			    
		  });
	
	</script> 
</body>

</html>