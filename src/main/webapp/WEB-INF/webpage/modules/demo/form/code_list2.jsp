<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<c:set var="serviceurl" value="${adminPath}/sms/smssendlog" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>bootstrapTable</title>
    
     <link href="${staticPath}/common/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${staticPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <link href="${staticPath}/vendors/animate/css/animate.css" rel="stylesheet">
    <link href="${staticPath}/common/css/style.css?v=4.1.0" rel="stylesheet">
    
    
</head>


<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>任务列表</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 在列表之间拖动任务面板</p>

                        <div class="input-group">
                        	<span id="pannelForm">
	                        	 <div class="agile-detail">
	                           		<i class="fa fa-clock-o"></i> 属性名
	                           		<input type="text" name="colName" id="colNameModelId" value="" class="inputChange copyEle" style="width: 100px;"/>
									<input type="text" name="colDbName" id="colDbNameModelId" value="" class="copyEle" style="width: 100px;"/>
									<input type="text" name="colAnno" id="colAnnoModelId" value="" class="copyEle" style="width: 100px;"/>
	                            </div>
                        	</span>
                            <span class="input-group-btn">
                                    <button type="button" class="btn btn-sm btn-white" onclick="addPannel();"> 
                                    <i class="fa fa-plus"></i> 添加</button>
                            </span>
                        </div>

                        <ul class="sortable-list connectList agile-list" id="pannelContent">
                           <li class="warning-element" id="1523598150207">
                            	<!-- 字段 -->
                                <div class="agile-detail">
                                    <!-- <a href="#" class="pull-right btn btn-xs btn-white">标签</a> -->
                             		<i class="fa fa-clock-o"></i> 属性名
                             		<input type="text" name="colName" placeholder="userName" class="inputChange copyEle" style="width: 100px;"/>
									<input type="text" name="colDbName" placeholder="user_name" class="copyEle" style="width: 100px;"/>
									<input type="text" name="colAnno" placeholder="用户名" class="copyEle" style="width: 100px;"/>
									
									<input type="button" value="删除" onclick="delRow(this);" style="float: right;"/>
									
									<div>
		                        		<div style="width:90px;display: inline-block;">列表是否显示</div>
		                        		<select name="isListVisiable" style="width: 15%;" class="copyEle">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										<div style="width:90px;display: inline-block;">列表是否查询</div>
										<select name="isSearch" style="width: 15%;" class="copyEle">
											<option value="false">否</option>
											<option value="true">是</option>
										</select>
										<div style="width:90px;display: inline-block;">明细是否显示</div>
										<select name="isDetailVisiable" style="width: 15%;" class="copyEle">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
		                        	</div>
		                        	<div>
		                        		<div style="width:90px;display: inline-block;">是否主表</div>
		                        		<select name="isMain" style="width: 15%;" class="copyEle">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										<div style="width:90px;display: inline-block;">显示类型</div>
										<select name="eleType" style="width: 15%;" onchange="changeEleType(this);" class="copyEle">
											<option value="text">普通文本文本框</option>
											<option value="select">下拉选择</option>
											<option value="date">日期选择</option>
											<option value="datetime">日期时间选择</option>
											<option value="textarea">多行文本输入框</option>
											<option value="ref">参照</option>
										</select>
										<div style="width:90px;display: inline-block;">是否必填</div>
										<select name="isRequired" style="width: 15%;" class="copyEle">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
		                        	</div>
									<div>
										<div style="width:90px;display: inline-block;">是否单独一行</div>
										<select name="isRow" style="width: 15%;" class="copyEle">
											<option value="false">否</option>
											<option value="true">是</option>
										</select>
										<div style="width:90px;display: inline-block;">实名类型</div>
										<select name="colType" style="width: 15%;" class="copyEle">
											<option value="String">String</option>
											<option value="Long">Long</option>
											<option value="Integer">Integer</option>
											<option value="Date">Date</option>
											<option value="Lob">Lob</option>
										</select>
										<div style="width:90px;display: inline-block;">实体长度</div>
										<select name="colLenth" style="width: 15%;" class="copyEle">
											<option value="250">250</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="10">10</option>
											<option value="36">36</option>
											<option value="50">50</option>
											<option value="100">100</option>
											<option value="250">250</option>
											<option value="1000">1000</option>
											<option value="2000">"2000"</option>
										</select>
									</div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>进行中</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 在列表之间拖动任务面板</p>
                        <div id="eleDetailDiv" liEleId="">
                        	<div>
                        		<div style="width:90px;display: inline-block;">实体名</div>
                        		<input type="text" name="colName" value="userName" class="inputChange detailCopy" style="width: 15%;"/>
								
								<div style="width:90px;display: inline-block;">字段名</div>
								<input type="text" name="colDbName" value="user_name" class="detailCopy" style="width: 15%;"/>
								<div style="width:90px;display: inline-block;">注释</div>
								<input type="text" name="colAnno" value="用户名" class="detailCopy" style="width: 15%;"/>
                        	</div>
                        	<div>
                        		<div style="width:90px;display: inline-block;">列表是否显示</div>
                        		<select name="isListVisiable" style="width: 15%;" class="detailCopy">
									<option value="true">是</option>
									<option value="false">否</option>
								</select>
								<div style="width:90px;display: inline-block;">列表是否查询</div>
								<select name="isSearch" style="width: 15%;" class="detailCopy">
									<option value="false">否</option>
									<option value="true">是</option>
								</select>
								<div style="width:90px;display: inline-block;">明细是否显示</div>
								<select name="isDetailVisiable" style="width: 15%;" class="detailCopy">
									<option value="true">是</option>
									<option value="false">否</option>
								</select>
                        	</div>
                        	<div>
                        		<div style="width:90px;display: inline-block;">是否主表</div>
                        		<select name="isMain" style="width: 15%;" class="detailCopy">
									<option value="true">是</option>
									<option value="false">否</option>
								</select>
								<div style="width:90px;display: inline-block;">显示类型</div>
								<select name="eleType" style="width: 15%;" onchange="changeEleType(this);" class="detailCopy">
									<option value="text">普通文本文本框</option>
									<option value="select">下拉选择</option>
									<option value="date">日期选择</option>
									<option value="datetime">日期时间选择</option>
									<option value="textarea">多行文本输入框</option>
									<option value="ref">参照</option>
								</select>
								<div style="width:90px;display: inline-block;">是否必填</div>
								<select name="isRequired" style="width: 15%;" class="detailCopy">
									<option value="true">是</option>
									<option value="false">否</option>
								</select>
                        	</div>
							<div>
								<div style="width:90px;display: inline-block;">是否单独一行</div>
								<select name="isRow" style="width: 15%;" class="detailCopy">
									<option value="false">否</option>
									<option value="true">是</option>
								</select>
								<div style="width:90px;display: inline-block;">实名类型</div>
								<select name="colType" style="width: 15%;" class="detailCopy">
									<option value="String">String</option>
									<option value="Long">Long</option>
									<option value="Integer">Integer</option>
									<option value="Date">Date</option>
									<option value="Lob">Lob</option>
								</select>
								<div style="width:90px;display: inline-block;">实体长度</div>
								<select name="colLenth" style="width: 15%;" class="detailCopy">
									<option value="250">250</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="10">10</option>
									<option value="36">36</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="250">250</option>
									<option value="1000">1000</option>
									<option value="2000">"2000"</option>
								</select>
							</div>
                        </div>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <script src="${staticPath}/vendors/jquery/js/jquery.min.js?v=2.1.4"></script>
    <script src="${staticPath}/vendors/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${staticPath}/vendors/jquery-ui/jquery-ui-1.10.4.min.js"></script>
    <script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".sortable-list").sortable({connectWith:".connectList"}).disableSelection()});
    </script>

    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    
    <script type="text/javascript">
	    $(document).ready(function() {
	    	inputChangeKeyup();
	    	
	    	$(".warning-element").click(function(){
	    		console.info("click===111====");
    			showDetail(this);
    		});
	    	
	    	$(".copyEle").change(function(){
	    		console.info("change===11111====");
    			showDetail($(this).closest("li"));
    		});
	    	
	    	
	    	$(".detailCopy").change(function(){
	    		console.info("change===detailCopy====");
    			editDetail(this);
    		});
	    	
	    });
    	function addPannel(){
    		var s='<div class="agile-detail">'+
		       		'<i class="fa fa-clock-o"></i> 属性名'+
		       		'<input type="text" name="colName" value="'+$("#colNameModelId").val()+'" class="inputChange copyEle" style="width: 100px;"/>'+
					'<input type="text" name="colDbName" value="'+$("#colDbNameModelId").val()+'" class="copyEle" style="width: 100px;"/>'+
					'<input type="text" name="colAnno" value="'+$("#colAnnoModelId").val()+'" class="copyEle" style="width: 100px;"/>'+
		        '</div>'
    		var timestampid = new Date().getTime();
    		$("#pannelContent").append('<li class="warning-element" id="'+timestampid+'">'+s+'</li>');
    		
    		$(".warning-element").click(function(){
    			showDetail(this);
    		});
    		$(".copyEle").change(function(){
    			showDetail($(this).closest("li"));
    		});
    	}
    	
    	//样式inputChange绑定，大写转换为下横线加小写的
    	function inputChangeKeyup(){
    		$(".inputChange").bind('keyup',function(){
    			var tVal=$(this).val();
    			var newVal="";
    			for(var i=0;i<tVal.length;i++){
    				var c=tVal.charAt(i);
    				if(c<'A' || c>'Z'){
    					newVal+=c;
    				}else{
    					newVal+="_"+c.toLocaleLowerCase();
    				}
    			}
    			$(this).next().val(newVal);
    		});
    	}
    	
    	//查看明细
    	function showDetail(t){
    		console.info("showDetail===");
    		$("#eleDetailDiv").attr("liEleId",$(t).attr("id"))
    		$(t).find(".copyEle").each(function(){
    			var tName=$(this).attr("name");
    			$("#eleDetailDiv").find('[name='+tName+']').val($(this).val());
    		});
    	}
    	//修改明细
    	function editDetail(t){
    		$("#eleDetailDiv").find(".detailCopy").each(function(){
    			var tName=$(this).attr("name");
    			$("#"+$("#eleDetailDiv").attr("liEleId")).find('[name='+tName+']').val($(this).val());
    		});
    	}
    </script>
</body>

</html>