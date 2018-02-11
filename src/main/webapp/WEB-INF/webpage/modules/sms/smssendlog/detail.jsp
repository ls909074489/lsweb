<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<c:set var="serviceurl" value="${adminPath}/sms/smssendlog"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>11111</title>
     <meta name="decorator" content="form"/>
     
     <script type="text/javascript" src="${staticPath}/vendors/layer/layer.min.js"></script>
     <script type="text/javascript" src="${staticPath}/vendors/formjs/jquery.form.js?v=20160801" type="text/javascript" ></script>
     <script type="text/javascript" src="${staticPath}/vendors/jquery-validation/js/jquery.validate.min.js"></script>
     <script type="text/javascript" src="${staticPath}/vendors/jquery-validation/js/localization/messages_zh.js"></script>
     
    <!-- 自定义js --> 
    <script src="${staticPath}/common/yy/yy-ui-utils.js?v=34" type="text/javascript"></script> 
</head>
<body class="white-bg">
	<div id="yy-page-edit" class="container-fluid page-container page-content" style="margin-top: 15px;">
		<form id="yy-form-edit" class="form-horizontal yy-form-edit">
			<fieldset disabled="disabled">
			<input name="id" id="id" type="hidden" value="${entity.id}"/>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4 required" >业务类型</label>
						<div class="col-md-8" >
							<input name="businessType" id="businessType" type="text" value="${entity.businessType}" class="form-control">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4 required" >联系电话</label>
						<div class="col-md-8" >
							<input name="phone" id="phone" type="text" value="${entity.phone}" class="form-control">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4" >是否发送成功</label>
						<div class="col-md-8" >
							<input name="status" id="status" type="text" value="${entity.status}" class="form-control">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4" >响应时间</label>
						<div class="col-md-8" >
							<input name="responseDate" id="responseDate" type="text" value="${entity.responseDate}" class="Wdate form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});">
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label class="control-label col-md-2" >发送数据</label>
						<div class="col-md-10" >
							<input id="senddata" name="senddata" type="text" class="form-control" value="${entity.senddata}">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4" >类别</label>
						<div class="col-md-8" >
							<select name="type" id="type" data-enum-group="BooleanType" class="yy-input-enumdata form-control"></select>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-md-1" style="width: 11.11%;">模板内容</label>
						<div class="col-md-11" style="width: 88.89%;">
							<textarea name="templateContent" id="templateContent" class="form-control">${entity.templateContent}</textarea>
						</div>
					</div>
				</div>
			</div>
			</fieldset>
		</form>
		<div class="row">
		    <div class="col-sm-12">
		        <div class="tabs-container">
		            <ul class="nav nav-tabs">
		                <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true"> 第一个选项卡</a>
		                </li>
		                <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">第二个选项卡</a>
		                </li>
		            </ul>
		            <div class="tab-content">
		                <div id="tab-1" class="tab-pane active">
		                    <div class="panel-body">
		                        <strong>HTML5 文档类型</strong>
		                        <p>Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置。</p>
		                    </div>
		                </div>
		                <div id="tab-2" class="tab-pane">
		                    <div class="panel-body">
		                        <strong>移动设备优先</strong>
		                        <p>在 Bootstrap 2 中，我们对框架中的某些关键部分增加了对移动设备友好的样式。而在 Bootstrap 3 中，我们重写了整个框架，使其一开始就是对移动设备友好的。这次不是简单的增加一些可选的针对移动设备的样式，而是直接融合进了框架的内核中。也就是说，Bootstrap 是移动设备优先的。针对移动设备的样式融合进了框架的每个角落，而不是增加一个额外的文件。</p>
		                    </div>
		                </div>
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
			validateForms();
		});
		
		
		//表单校验
		function validateForms(){
			validata = $('#yy-form-edit').validate({
				onsubmit : true,
				rules : {
					'businessType' : {required : true,maxlength : 100},
					'code' : {required : true,maxlength : 100},
					'datecols' : {maxlength : 100},
					'timecols' : {maxlength : 100},
					'isDisplay' : {maxlength : 100},
					'type' : {maxlength : 100},
					'type111' : {maxlength : 100}
				}
			});
		}
    </script>
</body>
</html>
