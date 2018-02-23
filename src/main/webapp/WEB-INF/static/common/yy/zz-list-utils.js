
//var serviceurl="";//相对地址
var _editParam = '1=1';
var _addParam = '1=1';
var _detailParam = '1=1';
var _queryData;
var btPageSize='10';
var btSortName = 'id';
var btSortOrder = 'desc';
var pageList=[10 , 20, 50, 100,500];
var TableInit = function (url) {
var oTableInit = new Object();

$(document).ready(function() {
	//按回车查询
	$('.queryform').on('keyup', function(event){
		if(event.keyCode == "13") {
			onQuery();
        }
	});
	
	$("#yy-btn-add").bind('click', onAdd);//新增
	$("#yy-btn-edit").bind('click', onEdit);//编辑		
	$("#yy-btn-remove").bind('click', onRemove);//删除
	$("#yy-btn-refresh").bind('click', onRefreshTable);//刷新
	$("#yy-btn-search").bind('click', onQuery);//查询
});
//==================bootstrapTable start=======================================================================================
      //初始化Table
      oTableInit.Init = function () {
    	  $('#ls-table').bootstrapTable({
				url: url,
				method: 'post',
				contentType : 'application/x-www-form-urlencoded',//post提交需要设置编码
				//height: '650',
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
				queryParams: oTableInit.queryParams,//传递参数
				pageSize: btPageSize,
				pageList: pageList,
				silentSort: false,
				smartDisplay: false,
				escape: true,
				searchOnEnterKey: true,
				idField: 'id',
				maintainSelected: true,
				sortName: btSortName,
				sortOrder: btSortOrder,
				toolbar: '#toolbar',
				columns: _tableCols,
				onDblClickRow:onDblClickRow
			});
      };
      //得到查询的参数
      oTableInit.queryParams = function (params) {
		   return queryParams(params);
      };
      return oTableInit;
  };
  
  
  //封装查询
  function queryParams(params){
	  console.info("default params===========");
	  var tempParms = {
	           'page.limit': params.limit,   //页面大小
	           'page.offset': params.offset,  //偏移量
	            sort: params.sort,  //排序列名  
	            order: params.order,//排位命令（desc，asc）  
	            gridtype:'jqgrid'//设置gridtype后台才能转换sort
	        };
	  $.each(_queryData, function(index) {
			if (this['value'] == null || this['value'] == ""){
				return;
			}
			tempParms[this['name']] = this['value'];
	  });
	  return tempParms;
  }
  
  //双击行事件
  function onDblClickRow(row){
	  //openlsDialogView("查看",serviceurl+"/onDetail?id="+row.id+ '&' + _detailParam,"ls-table","90%", "90%");
	  openCoverDialog(serviceurl+"/onDetail?id="+row.id+ '&' + _detailParam);
  }
  
  	//数据表格展开内容
	function detailFormatter(index, row) {
		var html = [];
		$.each(row, function (key, value) {
			html.push('<p><b>' + key + ':</b> ' + value + '</p>');
		});
		return html.join('');
	}
	
	//增加
	function onAdd() {
		if (!onAddBefore()) {
			return false;
		}
		openCoverDialog(serviceurl+"/create"+ _addParam);
		onAddAfter();
	}
	
	//编辑
	function onEdit(){
		var pks = getSelectPks();
		if (!pks || pks.length==0) {
		    top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
			return; 
		}
		if (pks.length>1) {
			top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
			return;
		}
		//openlsDialog("修改",serviceurl+"/onEdit?id="+pks.toString()+ '&' + _editParam,"ls-table","90%", "90%");
		openCoverDialog(serviceurl+"/onEdit?id="+pks.toString()+ '&' + _editParam);
	}
	
	//删除
	function onRemove() {
		var pks = getSelectPks();
		if (doBeforeRemove(pks)) {
			removeRecord(serviceurl+'/batch/delete', pks, onRefreshTable);
		}
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
						"data" : {"ids" : pks.toString()},
						"success" : function(data) {
							if (data.ret==0) {
								top.layer.close(listview);
								YYUI.succMsg("删除成功", {icon : 1});
								if (typeof (fnCallback) != "undefined"){
									fnCallback(data);
								}
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
	
	//刷新列表
	function onRefreshTable(){
		console.info("onRefreshTable===============");
		$('#ls-table').bootstrapTable("refresh", {});  
	}
	
	//查询
	function onQuery(){
		console.info("onQuery");
		_queryData = $("#yy-form-query").serializeArray();
		 $('#ls-table').bootstrapTable('refresh'); 
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
	
	
	window.operateEvents = {
		'click .row-view': function (e, value, row, index) {
			//openlsDialogView("查看",serviceurl+"/onDetail?id="+row.id+ '&' + _detailParam,"ls-table","90%", "90%");   
			openCoverDialog(serviceurl+"/onDetail?id="+row.id+ '&' + _detailParam);
         },
         'click .row-edit': function (e, value, row, index) {
        	 //openlsDialog("修改",serviceurl+"/onEdit?id="+row.id+ '&' + _editParam,"ls-table","90%", "90%");  
        	 openCoverDialog(serviceurl+"/onEdit?id="+row.id+ '&' + _editParam);
         },
         'click .row-delete': function (e, value, row, index) {
        	 removeRecord(serviceurl+'/batch/delete', row.id, onRefreshTable);       
         }
     };
	
	//查看-编辑-删除
	function renderActionCol(value, row, index) {
        return [
            '<div class="yy-btn-actiongroup">',
				'<button class="btn btn-xs btn-success row-view" data-rel="tooltip" title="查看"><i class="fa fa-search-plus"></i></button>&nbsp;',
	            '<button class="btn btn-xs btn-info  row-edit" data-rel="tooltip" title="编辑"><i class="fa fa-edit"></i></button>&nbsp;',
	            '<button class="btn btn-xs btn-danger  row-delete" data-rel="tooltip" title="删除"><i class="fa fa-trash-o"></i></button>',
            '</div>'
        ].join('');
    }
	
	//查看-编辑
	function renderViewEditActionCol(value, row, index) {
		 return [
            '<div class="yy-btn-actiongroup">',
				'<button class="btn btn-xs btn-success row-view" data-rel="tooltip" title="查看"><i class="fa fa-search-plus"></i></button>&nbsp;',
	            '<button class="btn btn-xs btn-info  row-edit" data-rel="tooltip" title="编辑"><i class="fa fa-edit"></i></button>',
            '</div>'
        ].join('');
	};
	
	//查看
	function renderViewActionCol(value, row, index) {
        return [
            '<div class="yy-btn-actiongroup">',
				'<button class="btn btn-xs btn-success row-view" data-rel="tooltip" title="查看"><i class="fa fa-search-plus"></i></button>',
            '</div>'
        ].join('');
    }
//==================bootstrapTable start=======================================================================================