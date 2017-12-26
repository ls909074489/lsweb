<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="decorator" content="single"/>
    <title>文件上传</title>
    <link rel="stylesheet" href="${staticPath}/common/bootstrap-table/bootstrap-table.min.css" />
    
    <!-- 全局js -->
	<html:js name="Validform" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
	      <div class="ibox float-e-margins">
	        <!-- <div class="ibox-title">
	          <h5>管理员列表</h5>
	          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
	        </div> -->
	        
	        <div class="ibox-content">
				<div class="row">
					<div class="pull-left">
						      <button id="yy-btn-add" class="btn btn-sm btn-blue"><i class="fa fa-plus"></i> 添加</button>
						      <button id="yy-btn-edit" class="btn btn-sm btn-success"><i class="fa fa-file-text-o"></i> 修改</button>
						      <button id="yy-btn-remove" class="btn btn-sm btn-danger"><i class="fa fa-trash-o"></i> 删除</button>
					</div>
					<div class="pull-right">
					      <button class="btn btn-sm btn-info" onclick="search('roleGridIdGrid')"><i class="fa fa-search"></i> 搜索</button>
					      <button class="btn btn-sm btn-warning" onclick="reset('roleGridIdGrid')"><i class="fa fa-refresh"></i> 重置</button>
					</div>
				</div>
				<div class="row">
					<table id=ls-table></table>
				</div>
			</div>
	      </div>
		</div>
	</div>
	<myfooter> 
	  <!-- Bootstrap table --> 
	  <script src="${staticPath}/common/bootstrap-table/bootstrap-table.min.js"></script> 
	  <script src="${staticPath}/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script> 
	  <script src="${staticPath}/common/bootstrap-table/tableExport.js"></script> 
	  <script src="${staticPath}/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script> 
	  <!-- 自定义js --> 
	  <script src="${staticPath}/common/yy/yy-ui-utils.js?v=333"></script> 
	  <script>
	  
		  $(document).ready(function() {
			  var $table = $('#ls-table');
				$(function() {
					$table.bootstrapTable({
						url: '${adminPath}/sms/smssendlog/ajaxListNew',
						height: '650',
						striped: true,
						search: false,
						showRefresh: false,
						showColumns: false,
						minimumCountColumns: 2,
						clickToSelect: true,
						detailView: true,
						detailFormatter: 'detailFormatter',
						pagination: true,
						paginationLoop: false,
						sidePagination: 'server',
						pageSize: '20',
						silentSort: false,
						smartDisplay: false,
						escape: true,
						searchOnEnterKey: true,
						idField: 'logId',
						maintainSelected: true,
						sortName: 'logId',
						sortOrder: 'desc',
						toolbar: '#toolbar',
						columns: [
							{checkbox: true},
							{field: 'id', title: 'ID', sortable: true, align: 'center', halign: 'center'},
							{field: 'phone', title: '联系电话', align: 'center', halign: 'center'},
				            {field: 'templateId', title: '模板ID', halign: 'center'},
							{field: 'templateContent', title: '模板内容', align: 'center', sortable: true, halign: 'center'},
							{field: 'senddata', title: '发送数据', halign: 'center'},
							{field: 'code', title: '返回码', align: 'center', halign: 'center'},
							{field: 'msg', title: '返回消息', halign: 'center'},
							{field: 'responseDate', title: '响应时间', align: 'center', halign: 'center'}
						]
					});
				});
				
				
				$("#yy-btn-add").bind('click', onAdd);//新增
				$("#yy-btn-edit").bind('click', onEditDetail);//编辑		
				$("#yy-btn-remove").bind('click', onRemove);//删除
		  });
			
			
			// 数据表格展开内容
			function detailFormatter(index, row) {
				var html = [];
				$.each(row, function (key, value) {
					html.push('<p><b>' + key + ':</b> ' + value + '</p>');
				});
				return html.join('');
			}
			
			
			//刷新
			function onRefresh(_isServerPage) {
				if(typeof(_isServerPage) == "undefined") {
					_isServerPage = this._isServerPage;
				}
				doBeforeRefresh();
				_queryData = $("#yy-form-query").serializeArray();
				if (_isServerPage) {
					_tableList.draw(); //服务器分页
				} else {
					loadList(); //非服务器分页
				}
			}
			
			//增加
			function onAdd() {
				var _addParam;
				if (!onAddBefore()) {
					return false;
				}
				/* top.layer.open({
					type : 2,
					title : false,//标题
					shadeClose : false,//是否点击遮罩关闭
					shade : 0.8,//透明度
					closeBtn : 0,//关闭按钮
					area : [ "90%", "90%"],
					content : '${ctx}/admin/demo/form/bootstrapTable?'+ _addParam, //iframe的url
				}); */
				openDialog("添加","${ctx}/admin/demo/form/bootstrapTable","ls-table","90%", "90%");
				onAddAfter();
			}
			
			//编辑
			function onEditDetail(){
				var rowsData  = $('#ls-table').bootstrapTable('getSelections');
				console.info(rowsData);
				if (!rowsData || rowsData.length==0) {
				    top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
					return; 
				}
				if (rowsData.length>1) {
					top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
					return;
				}
	    		
				openDialog("修改","${ctx}/admin/demo/form/bootstrapTable","ls-table","90%", "90%");
			}
			
			//删除
			function onRemove() {
				var pks = getSelectPks();//YYDataTableUtils.
				if (doBeforeRemove(pks)) {
					removeRecord('${serviceurl}/delete', pks, onRefresh);
				}
			}
			
			function doBeforeRemove(pks) {
				return true;
			}
			
			function onAddBefore() {
				return true;
			}

			function onAddAfter() {
				return true;
			}
			/**
			 * 删除记录 url 后台删除的url pks 主键数组 fnCallback 成功后回调函数
			 */
			function removeRecord(url, pks, fnCallback, isConfirm, isSuccess) {
				if (checkDelete(pks)) {
					if (typeof (isConfirm) == "undefined") {
						isConfirm = true;
					}
					if (typeof (isSuccess) == "undefined") {
						isSuccess = false;
					}
					top.layer.confirm("确实要删除吗？", function() {
						var listview = top.layer.load(2);
						$.ajax({
									"dataType" : "json",
									"type" : "POST",
									"url" : url,
									"data" : {
										"pks" : pks.toString()
									},
									"success" : function(data) {
										if (data.success) {
											top.layer.close(listview);
											
											YYUI.succMsg("删除成功", {icon : 1});
											if (typeof (fnCallback) != "undefined")
												fnCallback(data);
										} else {
											top.layer.close(listview);
											YYUI.failMsg(YYMsg.alertMsg('sys-delete-fail') + data.msg);//删除失败，原因：
										}
									},
									"error" : function(XMLHttpRequest, textStatus,
											errorThrown) {
										top.layer.close(listview);
										YYUI.failMsg(YYMsg.alertMsg('sys-delete-http'));//"删除失败，HTTP错误。"
									}
								});
					});
				}
			};
			
			//删除前检查
			function checkDelete(pks) {
				if (pks.length < 1) {
					YYUI.promMsg("请选择需要删除的记录");
					return false;
				}else{
					/* for (var i = 0; i < pks.length; i++) {
						var row = $("input[value='" + pks[i] + "']").closest("tr");
						var billstatus = _tableList.row(row).data().billstatus;
						if (billstatus == undefined) {
							return true;
						}
						if (billstatus != 1 && billstatus != 4) {
							YYUI.failMsg("存在不能删除的记录！");
							return false;
						}
					} */
					return true;
				}
			}
			
			//获取选中的id
			function getSelectPks(otableId){
				if (otableId == null){
					otableId="ls-table";
				}
				var pks = new Array();
				var rowsData  = $('#'+otableId).bootstrapTable('getSelections');
				if (rowsData.length>0) {
					for (var i = 0; i < rowsData.length; i++) {
						pks.push(rowsData[i].id);
					}
				}
				console.info("getSelectPks==============="+pks);
				return pks;
			}
			
			
			
			
	  </script> 
	</myfooter>
</body>

</html>