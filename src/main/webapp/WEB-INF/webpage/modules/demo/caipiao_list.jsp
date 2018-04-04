<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<c:set var="serviceurl" value="${ctx}/caipiao" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="decorator" content="list-bt"/>
    <title>bootstrapTable</title>
    
    <!-- 图表相关  --> 
	<script src="${staticPath}/common/echarts/echarts.min.js?v=20160801" type="text/javascript"></script>
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
	
	<div class="row">
		<table id="yy-table-countlist"  class="table">
		
		</table>
	</div>
	<div class="row">
		   <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		    <div id="main1" style="width: 100%;height:400px;"></div>
	</div>	


	<script>
		var _tableCols= [
		{field: 'Number',title: '序号',align: 'center',formatter: function (value, row, index) {return index+1;}},
		{checkbox: true},
		{field: 'operate',title: '操作',align: 'center',clickToSelect:false,events: operateEvents,formatter: renderActionCol},
		{field: 'qiHao', title: '期号', sortable: true, align: 'center', halign: 'center'},
		{field: 'haoMa', title: '号码', align: 'center', halign: 'center'},
		{field: 'wan', title: '万', halign: 'center'},
		{field: 'qian', title: '千', align: 'center', sortable: true, halign: 'center'},
		{field: 'bai', title: '百', halign: 'center'},
		{field: 'shi', title: '十', align: 'center', halign: 'center'},
		{field: 'ge', title: '个', halign: 'center'},
		{field: 'openTime', title: '时间', align: 'center', halign: 'center'}
		 ];
		 
		  $(document).ready(function() {
			    _queryData = $("#yy-form-query").serializeArray();
				//1.初始化Table
			    var oTable = new TableInit('${serviceurl}/ajaxListNew');
			    oTable.Init();
			    
		  });
	
		  
		  
		  // 基于准备好的dom，初始化echarts实例
	        var myChart1 = echarts.init(document.getElementById('main1'));
	
	        var xAxisData=new Array();
	        var seriesArr=new Array();
	    	var InfoLoading = layer.load(2);
			$.ajax({
				type : "POST",
				data :{"uuid": '${entity.uuid}'},
				url : "${serviceurl}/numStatis",
				async : true,
				dataType : "json",
				success : function(data) {
					layer.close(InfoLoading);
					var tDays=data.days;
					for(var i=0;i<tDays.length;i++){
						xAxisData.push(tDays[i]);
					}
					
					var tResult=data.result;
					
					var countListStr="<tr><td>日期</td>";
					for(var i=1;i<=16;i++){
						countListStr+="<td>"+i+"</td>"
					}
					countListStr+="</tr>";
					for(var i=0;i<tResult.length;i++){
						var tScoreArry=new Array();
						var tRow=tResult[i];
						countListStr+="<tr><td rowspan='5'>"+tRow[0].day+"</td>";
						var qianRow="<tr>";
						var baiRow="<tr>";
						var shiRow="<tr>";
						var geRow="<tr>";
						for(var j=0;j<tRow.length;j++){
							countListStr+="<td>"+tRow[j].wan+"</td>";
							qianRow+="<td>"+tRow[j].qian+"</td>";
							baiRow+="<td>"+tRow[j].bai+"</td>";
							shiRow+="<td>"+tRow[j].shi+"</td>";
							geRow+="<td>"+tRow[j].ge+"</td>";
						}
						countListStr+="</tr>";
						qianRow+="</tr>";
						baiRow+="</tr>";
						shiRow+="</tr>";
						geRow+="</tr>";
						countListStr+=qianRow;
						countListStr+=baiRow;
						countListStr+=shiRow;
						countListStr+=geRow;
					}
					
					$("#yy-table-countlist").html(countListStr);
					
					console.info(tResult);
					for(var i=0;i<tResult.length;i++){
						var tScoreArry=new Array();
						var tRow=tResult[i];
						for(var j=0;j<tRow.length;j++){
							tScoreArry.push(tRow[j].wan);
						}
						seriesArr.push({name:i,type:'bar',data:tScoreArry});
					}
					 // 指定图表的配置项和数据
			        var option1 = {
			        	    title : {
			        	        text: '统计',
			        	        subtext: '纯属虚构'
			        	    },
			        	    tooltip : {
			        	        trigger: 'axis'
			        	    },
			        	    legend: {
			        	        data:['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15']
			        	    },
			        	    toolbox: {
			        	        show : true,
			        	        feature : {
			        	            mark : {show: true},
			        	            dataView : {show: true, readOnly: false},
			        	            magicType : {show: true, type: ['line', 'bar']},
			        	            restore : {show: true},
			        	            saveAsImage : {show: true}
			        	        }
			        	    },
			        	    calculable : true,
			        	    xAxis : [
			        	        {
			        	            type : 'category',
			        	            data : xAxisData
			        	        }
			        	    ],
			        	    yAxis : [
			        	        {
			        	            type : 'value'
			        	        }
			        	    ],
			        	    series : seriesArr
			        	};
			        	                    
			        // 使用刚指定的配置项和数据显示图表。
			        myChart1.setOption(option1);
				},
				error : function(data) {
					layer.close(InfoLoading);
					YYUI.promAlert(YYMsg.alertMsg('sys-submit-http',null));
				}
			});
	</script> 
</body>

</html>