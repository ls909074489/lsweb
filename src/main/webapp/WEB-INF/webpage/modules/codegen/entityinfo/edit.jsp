<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<c:set var="serviceurl" value="${adminPath}/codegen/entityinfo" />
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>11111</title>
    
     <link href="${staticPath}/common/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${staticPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <link href="${staticPath}/vendors/animate/css/animate.css" rel="stylesheet">
    <link href="${staticPath}/common/css/style.css?v=4.1.0" rel="stylesheet">
    
    <link rel="stylesheet" href="${staticPath}/vendors/layer/skin/layer.css" id="layui_layer_skinlayercss" style="">
	
	<script src="${staticPath}/vendors/jquery/js/jquery.min.js?v=2.1.4"></script>
	<script type="text/javascript" src="${staticPath}/vendors/formjs/jquery.form.js?v=20160801" type="text/javascript" ></script>
	<script src="${staticPath}/vendors/layer/layer.min.js"></script>
	<script type="text/javascript" src="${staticPath}/vendors/formjs/jquery.form.js?v=20160801" type="text/javascript" ></script>
    <script type="text/javascript" src="${staticPath}/vendors/jquery-validation/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${staticPath}/vendors/jquery-validation/js/localization/messages_zh.js"></script>
    
    <script src="${staticPath}/vendors/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${staticPath}/vendors/jquery-ui/jquery-ui-1.10.4.min.js"></script>
    <script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".sortable-list").sortable({connectWith:".connectList"}).disableSelection()});
    </script>

    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    
	<!-- 自定义js --> 
    <script src="${staticPath}/common/yy/yy-ui-utils.js?v=35" type="text/javascript"></script> 
    <style type="text/css">
    	input{
    		border-top: none;border-left: none;border-right: none;
    	}
    	select{
    		border-top: none;border-left: none;border-right: none;
    	}
    </style>
</head>
<body class="white-bg">
	<div id="yy-page-edit" class="container-fluid page-container page-content">
		<div class="row yy-toolbar">
			<button id="yy-btn-save" class="btn btn-sm btn-success">
				<i class="fa fa-save"></i> 保存
			</button>
			<button id="yy-btn-cancel" class="btn btn-sm btn-success">
				<i class="fa fa-rotate-left"></i> 取消
			</button>
		</div>
		<form id="yy-form-edit" class="form-horizontal yy-form-edit">
			<input name="id" id="id" type="hidden" value="${entity.id}"/>
			<div class="row">
	            <div class="col-sm-12">
	                <div class="ibox">
	                    <div class="ibox-content">
	                        <span>
	                        	<span style="font-size: 24px;">ls2008代码生成</span>
	                             <button type="button" class="btn btn-sm btn-success" onclick="addPannel();"> 
	                             <i class="fa fa-plus"></i> 添加</button>
	                             
	                              <button type="button" class="btn btn-sm btn-success" onclick="addPannel();" style="float: right;"> 
	                             <i class="fa fa-plus"></i> 添加</button>
	                        </span>
	                        <div style="border-style:solid;border-color: #e7eaec;padding-bottom: 10px;padding-left: 10px;">
	                        	<div class="">
									模板类型
									<label><input type="radio" name="genType" value="1" disabled="disabled"/>普通列表（前端分页）</label>
									<label><input type="radio" name="genType" value="2" checked="checked"/>普通列表（服务器分页）</label>
									<label><input type="radio" name="genType" value="3"/>树状结构</label>
									<label><input type="radio" name="genType" value="4"/>左树右列表</label>
									<label><input type="radio" name="genType" value="5"/>主子表（服务器分页）</label>
									<label><input type="radio" name="genType" value="6"/>主子表（前端分页）</label>
									
									<label><input type="radio" name="genType" value="11"/>列表单选</label>
									<label><input type="radio" name="genType" value="12"/>树单选</label>
								</div>
		                       	<table>
		                       		<tr>
		                       			<td style="width: 750px;">
		                       				<div class="">
												package
												<input type="text" name="packagePath" value="${entity.packagePath}" class="copyEle" style="width: 285px;"/>;
												&nbsp;&nbsp;&nbsp;&nbsp;//所在包路径,例如&nbsp;&nbsp;com.yy.modules.sys.user
											</div>
					                        <div class="">
												@RequestMapping(value="
												<input type="text" name="reqMappingPath" value="${entity.reqMappingPath}" class="copyEle" style="width: 285px;"/>
												")&nbsp;&nbsp;&nbsp;&nbsp;//对应访问url地址,例如&nbsp;&nbsp;/sys/user
											</div>
											<div class="">
												return "
												<input type="text" name="jspPath" value="${entity.jspPath}" class="copyEle" style="width: 285px;"/>
												";&nbsp;&nbsp;&nbsp;&nbsp;//返回的页面,例如 &nbsp;&nbsp;return "modules/sys/user/user_main";
											</div>
		                       			</td>
		                       			<td style="width: 20px;">
		                       			</td>
		                       			<td style="">
		                       				<textarea rows="3" cols="100" style="width: 100%;"></textarea>
		                       			</td>
		                       		</tr>
		                       	</table>
							</div>
							
							<div class="">
								@MetaData(value = "
								<input type="text" name="title" value="${entity.title}" class="copyEle" style="width: 200px;"/>
								")&nbsp;&nbsp;&nbsp;&nbsp;//开发作者&nbsp;&nbsp;<input type="text" name="author" value="${entity.author}" class="copyEle" style="width: 200px;"/>
							</div>
							<div class="">
								@Table(name = "
								<input type="text" name="tableName" id="tableName" value="${entity.tableName}" class="copyEle" style="width: 223px;"/>
								")
							</div>
							<div class="">
								public class 
								<input type="text" name="entityName" value="${entity.entityName}" class="copyEle" style="width: 270px;"  onkeydown="changeEntity(this);"/>
								extends 
								<select name="extendsEntity">
									<option value="BaseEntity">BaseEntity</option>
									<option value="SuperEntity">SuperEntity</option>
									<option value="TreeEntity">TreeEntity</option>
								</select>
							</div>
	                        <ul class="sortable-list connectList agile-list" id="pannelContent">
	                        	<c:forEach items="${colList}" var="list">
	                           <li class="warning-element" id="${list.id}">
	                           		@Column(name="
	                        		 <input type="text" name="colDbName" id="colDbNameModelId" value="${list.colDbName}" class="copyEle" style="width: 25%;"/>
		                        	",length = 
		                        	<select name="colLenth" style="width: 60px;" class="copyEle">
										<option value="250">250</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="10">10</option>
										<option value="36">36</option>
										<option value="50">50</option>
										<option value="100">100</option>
										<option value="250">250</option>
										<option value="1000">1000</option>
										<option value="2000">2000</option>
									</select>
		                        		)
		                        		
									
									<div style="display: inline-block;float: right;">&nbsp;&nbsp;明细显示&nbsp;&nbsp;
										<select name="isDetailVisiable" style="width: 35px;float: right;" class="detailCopy">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										&nbsp;&nbsp;
									</div>
									<div style="display: inline-block;float: right;">&nbsp;&nbsp;列表显示&nbsp;&nbsp;
		                        		<select name="isListVisiable" style="width: 35px;float: right;" class="detailCopy">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										&nbsp;&nbsp;
									</div>
									<div style="display: inline-block;float: right;">&nbsp;&nbsp;列表查询&nbsp;&nbsp;
										<select name="isSearch" style="width: 35px;float: right;" class="detailCopy">
											<option value="false">否</option>
											<option value="true">是</option>
										</select>
										&nbsp;&nbsp;
									</div>
									<div style="display: inline-block;float: right;">是否主表&nbsp;&nbsp;
		                        		<select name="isMain" style="width: 35px;float: right;" class="detailCopy">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										&nbsp;&nbsp;
									</div>
	                                <div class="agile-detail">
	                                    <i class="fa fa-clock-o"></i> private
		                           		<select name="colType" style="width: 70px;" class="copyEle">
												<option value="String">String</option>
												<option value="Long">Long</option>
												<option value="Integer">Integer</option>
												<option value="Date">Date</option>
												<option value="Lob">Lob</option>
											</select>
		                           		<input type="text" name="colName" id="colNameModelId" value="${list.colName}" class="inputChange copyEle" style="width: 20%;"/>
										;//<input type="text" name="colAnno" id="colAnnoModelId" value="${list.colAnno}" class="copyEle" style="width: 20%;"/>
	                               
	                               		<div style="display: inline-block;float: right;">最大长度&nbsp;&nbsp;
											<select name="maxLength" style="width: ;" class="detailCopy">
												<option value="250">250</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="10">10</option>
												<option value="36">36</option>
												<option value="50">50</option>
												<option value="100">100</option>
												<option value="250">250</option>
												<option value="1000">1000</option>
												<option value="2000">2000</option>
											</select>
											&nbsp;&nbsp;
										</div>
	                               		<div style="display: inline-block;float: right;">是否必填&nbsp;&nbsp;
											<select name="isRequired" style="width: 35px;" class="detailCopy">
												<option value="true">是</option>
												<option value="false">否</option>
											</select>
											&nbsp;&nbsp;
										</div>
		                               <div style="display: inline-block;float: right;">显示类型&nbsp;&nbsp;
											<select name="eleType" style="width: 80px;" onchange="changeEleType(this);" class="detailCopy">
												<option value="text">input框</option>
												<option value="select">下拉选择</option>
												<option value="date">日期选择</option>
												<option value="datetime">datetime</option>
												<option value="textarea">textare框</option>
												<option value="ref">参照</option>
											</select>
											&nbsp;&nbsp;
										</div>
	                                </div>
	                            </li>
	                           </c:forEach>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	        </div>
		</form>
		<div class="input-group">
                <div id="pannelForm" style="display: none;">
                    		 @Column(name="
                    		 <input type="text" name="colDbName" id="colDbNameModelId" value="" class="copyEle" style="width: 25%;"/>
                     	",length = 
                     	<select name="colLenth" style="width: 60px;" class="copyEle">
					<option value="250">250</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="10">10</option>
					<option value="36">36</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="250">250</option>
					<option value="1000">1000</option>
					<option value="2000">2000</option>
				</select>
                     		)
				
				<div style="display: inline-block;float: right;">&nbsp;&nbsp;明细显示&nbsp;&nbsp;
					<select name="isDetailVisiable" style="width: 35px;float: right;" class="detailCopy">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					&nbsp;&nbsp;
				</div>
				<div style="display: inline-block;float: right;">&nbsp;&nbsp;列表显示&nbsp;&nbsp;
                     		<select name="isListVisiable" style="width: 35px;float: right;" class="detailCopy">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					&nbsp;&nbsp;
				</div>
				<div style="display: inline-block;float: right;">&nbsp;&nbsp;列表查询&nbsp;&nbsp;
					<select name="isSearch" style="width: 35px;float: right;" class="detailCopy">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
					&nbsp;&nbsp;
				</div>
				<div style="display: inline-block;float: right;">是否主表&nbsp;&nbsp;
                     		<select name="isMain" style="width: 35px;float: right;" class="detailCopy">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					&nbsp;&nbsp;
				</div>
                   <div class="agile-detail">
                               <i class="fa fa-clock-o"></i> private
                       		<select name="colType" style="width: 70px;" class="copyEle">
						<option value="String">String</option>
						<option value="Long">Long</option>
						<option value="Integer">Integer</option>
						<option value="Date">Date</option>
						<option value="Lob">Lob</option>
					</select>
                       		<input type="text" name="colName" id="colNameModelId" value="" class="inputChange copyEle" style="width: 20%;"/>
				;//<input type="text" name="colAnno" id="colAnnoModelId" value="" class="copyEle" style="width: 20%;"/>
                          
                          
                    <div style="display: inline-block;float: right;">最大长度&nbsp;&nbsp;
					<select name="maxLength" style="width: ;" class="detailCopy">
						<option value="250">250</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="10">10</option>
						<option value="36">36</option>
						<option value="50">50</option>
						<option value="100">100</option>
						<option value="250">250</option>
						<option value="1000">1000</option>
						<option value="2000">2000</option>
					</select>
					&nbsp;&nbsp;
				</div>
				<div style="display: inline-block;float: right;">是否必填&nbsp;&nbsp;
					<select name="isRequired" style="width: 35px;" class="detailCopy">
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
					&nbsp;&nbsp;
				</div>
				 <div style="display: inline-block;float: right;">显示类型&nbsp;&nbsp;
					<select name="eleType" style="width: 80px;" onchange="changeEleType(this);" class="detailCopy">
						<option value="text">input框</option>
						<option value="select">下拉选择</option>
						<option value="date">日期选择</option>
						<option value="datetime">datetime</option>
						<option value="textarea">textare框</option>
						<option value="ref">参照</option>
					</select>
					&nbsp;&nbsp;
				</div>
                </div>
           </div>
        </div>
    </div>
    <script type="text/javascript">
	    //初始化表单
		var validateForm;
		var callFunc;
		//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		function doLsSubmit(func){
			console.info("dosubmit===111");
			callFunc=func;
			//callFunc();
		    //validateForm.ajaxPost();
			if (!$('#yy-form-edit').valid()) return false;
		    
			var editview = layer.load(2);
			
			var posturl = "${serviceurl}/create";
			var pk = $("input[name='id']").val();
			console.info(pk);
			if (pk != "" && typeof (pk) != "undefined") {
				posturl = "${serviceurl}/"+pk+"/update";
			}
			var opt = {
				url : posturl,
				type : "post",
				success : function(data) {
					console.info(data);
					if (data.ret==0) {
						layer.close(editview);
							YYUI.succMsg('保存成功');
							//window.parent.onRefresh(true);
							callFunc();
					} else {
						YYUI.failMsg(' 保存失败：' + data.msg);
						layer.close(editview);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					YYUI.promAlert('保存出错：HTTP错误 ');
					layer.close(editview);
				}
			}
			$("#yy-form-edit").ajaxSubmit(opt);
		}
		
		
		$(document).ready(function() {
			
			$("#yy-btn-save").bind("click", function() {
				onSave(true);
			});
			$("#yy-btn-cancel").bind('click', onCancel);//新增
			inputChangeKeyup();
			
			setValue();
		});
		
		
		//设置默认值
		function setValue() {
			if ('${openstate}' == 'add') {
				//$("select[name='is_use']").val('1');
			} else if ('${openstate}' == 'edit') {
				var jsonResp = jQuery.parseJSON('${colListJson}');
				console.info(jsonResp);
				if(jsonResp!=null&&jsonResp.length>0){
					for(var i=0;i<jsonResp.length;i++){
						var sub=jsonResp[i];
						console.info(sub);
						//$("#"+sub.id).find()
						//$("#"+sub.id).find('[name='+sub.+']').val($(this).val());
						for (var key in sub) {
						console.info(key);
						console.info(jsonResp[key]);
					}
					}
				}
			}
		}
		
		function addPannel(){
    		var s=$("#pannelForm").html();
    		var timestampid = new Date().getTime();
    		$("#pannelContent").append('<li class="warning-element" id="'+timestampid+'">'+s+'</li>');
    		inputChangeKeyup();
    	}
    	
    	//样式inputChange绑定，大写转换为下横线加小写的
    	function inputChangeKeyup(){
    		console.info("inputChangeKeyup===");
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
    			//$(this).next().next().val(newVal);
    			//console.info($(this).parent().html());
    			//console.info($(this).parent().closest("input[name='colDbName']").html());
    			$(this).parent().parent().find("input[name='colDbName']").val(newVal);
    		});
    	}
    	
    	
    	//
    	function changeEleType(){
    		console.info("xxxxxxxxxxxxxx");
    	}
    	
    	function changeEntity(t){
    		var tVal=$(t).val();
			var newVal="";
			for(var i=0;i<tVal.length;i++){
				var c=tVal.charAt(i);
				if(c<'A' || c>'Z'){
					newVal+=c;
				}else{
					newVal+="_"+c.toLocaleLowerCase();
				}
			}
			$("#tableName").val("yy_"+newVal);
    	}
		
    	
    	function onSave(){
			if (!$('#yy-form-edit').valid()) return false;
		    
			var editview = layer.load(2);
			
			var posturl = "${serviceurl}/genCode";
			var pk = $("input[name='id']").val();
			console.info(pk);
			if (pk != "" && typeof (pk) != "undefined") {
				posturl = "${serviceurl}/genCode";
			}
			
			//保存新增的子表记录 
	        var subList = new Array();
	        
	    	var data = $('#pannelContent').find(".warning-element").each(function(){
	    		subList.push($(this).find('input, select').serialize());
	    	});
	        
			console.info(subList);
			var opt = {
				url : posturl,
				type : "post",
				data : {"subList" : subList},
				success : function(data) {
					console.info(data);
					if (data.ret==0) {
						layer.close(editview);
						YYUI.succMsg('保存成功');
					} else {
						YYUI.failMsg(' 保存失败：' + data.msg);
						layer.close(editview);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					YYUI.promAlert('保存出错：HTTP错误 ');
					layer.close(editview);
				}
			}
			$("#yy-form-edit").ajaxSubmit(opt);
		}
    </script>
</body>
</html>
