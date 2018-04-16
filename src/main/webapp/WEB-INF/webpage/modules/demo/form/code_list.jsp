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
    
    <style type="text/css">
    	input{
    		border-top: none;border-left: none;border-right: none;
    	}
    	select{
    		border-top: none;border-left: none;border-right: none;
    	}
    </style>
</head>


<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <span>
                        	<span style="font-size: 24px;">ls2008代码生成</span>
                             <button type="button" class="btn btn-sm btn-white" onclick="addPannel();"> 
                             <i class="fa fa-plus"></i> 添加</button>
                             
                              <button type="button" class="btn btn-sm btn-white" onclick="addPannel();" style="float: right;"> 
                             <i class="fa fa-plus"></i> 添加</button>
                        </span>
                        <div class="input-group">
                        	<span id="pannelForm" style="display: none;">
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
								
								<div style="width:;display: inline-block;float: right;">&nbsp;&nbsp;明细显示&nbsp;&nbsp;
									<select name="isDetailVisiable" style="width: 35px;float: right;" class="detailCopy">
										<option value="true">是</option>
										<option value="false">否</option>
									</select>
									&nbsp;&nbsp;
								</div>
								<div style="width:;display: inline-block;float: right;">&nbsp;&nbsp;列表显示&nbsp;&nbsp;
	                        		<select name="isListVisiable" style="width: 35px;float: right;" class="detailCopy">
										<option value="true">是</option>
										<option value="false">否</option>
									</select>
									&nbsp;&nbsp;
								</div>
								<div style="width:;display: inline-block;float: right;">&nbsp;&nbsp;列表查询&nbsp;&nbsp;
									<select name="isSearch" style="width: 35px;float: right;" class="detailCopy">
										<option value="false">否</option>
										<option value="true">是</option>
									</select>
									&nbsp;&nbsp;
								</div>
								<div style="width:;display: inline-block;float: right;">是否主表&nbsp;&nbsp;
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
                               
                               
                            	   <div style="width:;display: inline-block;float: right;">最大长度&nbsp;&nbsp;
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
									<div style="width:;display: inline-block;float: right;">是否必填&nbsp;&nbsp;
										<select name="isRequired" style="width: 35px;" class="detailCopy">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										&nbsp;&nbsp;
									</div>
									 <div style="width:;display: inline-block;float: right;">显示类型&nbsp;&nbsp;
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
                        	</span>
                        </div>
						<div class="">
							package
							<input type="text" name="packagePath" value="" class="copyEle" style="width: 285px;"/>;
						</div>
						<div class="">
							@MetaData(value = "
							<input type="text" name="title" value="" class="copyEle" style="width: 200px;"/>
							")
						</div>
						<div class="">
							@Table(name = "
							<input type="text" name="tableName" id="tableName" value="" class="copyEle" style="width: 223px;"/>
							")
						</div>
						<div class="">
							public class 
							<input type="text" name="entityName" value="" class="copyEle" style="width: 270px;"  onkeydown="changeEntity(this);"/>
							extends 
							<select name="extendsEntity">
								<option value="BaseEntity">BaseEntity</option>
								<option value="SuperEntity">SuperEntity</option>
								<option value="TreeEntity">TreeEntity</option>
							</select>
						</div>
                        <ul class="sortable-list connectList agile-list" id="pannelContent">
                           <li class="warning-element" id="1523598150207">
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
	                        		
								
								<div style="width:;display: inline-block;float: right;">&nbsp;&nbsp;明细显示&nbsp;&nbsp;
									<select name="isDetailVisiable" style="width: 35px;float: right;" class="detailCopy">
										<option value="true">是</option>
										<option value="false">否</option>
									</select>
									&nbsp;&nbsp;
								</div>
								<div style="width:;display: inline-block;float: right;">&nbsp;&nbsp;列表显示&nbsp;&nbsp;
	                        		<select name="isListVisiable" style="width: 35px;float: right;" class="detailCopy">
										<option value="true">是</option>
										<option value="false">否</option>
									</select>
									&nbsp;&nbsp;
								</div>
								<div style="width:;display: inline-block;float: right;">&nbsp;&nbsp;列表查询&nbsp;&nbsp;
									<select name="isSearch" style="width: 35px;float: right;" class="detailCopy">
										<option value="false">否</option>
										<option value="true">是</option>
									</select>
									&nbsp;&nbsp;
								</div>
								<div style="width:;display: inline-block;float: right;">是否主表&nbsp;&nbsp;
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
                               
                               		<div style="width:;display: inline-block;float: right;">最大长度&nbsp;&nbsp;
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
                               		<div style="width:;display: inline-block;float: right;">是否必填&nbsp;&nbsp;
										<select name="isRequired" style="width: 35px;" class="detailCopy">
											<option value="true">是</option>
											<option value="false">否</option>
										</select>
										&nbsp;&nbsp;
									</div>
	                               <div style="width:;display: inline-block;float: right;">显示类型&nbsp;&nbsp;
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
                        </ul>
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
	    	
	    });
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
    </script>
</body>

</html>