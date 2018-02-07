<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<c:set var="serviceurl" value="${adminPath}/ver/test1"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>11111</title>
     <meta name="decorator" content="form"/>
</head>
<body class="white-bg">
<div id="yy-page-edit" class="container-fluid page-container page-content">
	
		<!-- <div class="row yy-toolbar">
			<button id="yy-btn-save" class="btn blue btn-sm">
				<i class="fa fa-save"></i> 保存
			</button>
			<button id="yy-btn-cancel" class="btn blue btn-sm">
				<i class="fa fa-rotate-left"></i> 取消
			</button>
		</div> -->
		<div class="row">
			<form id="yy-form-edit" class="form-horizontal yy-form-edit">
				<input name="uuid" id="uuid" type="hidden"/>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4 required" >名称</label>
							<div class="col-md-8" >
								<input name="name" id="name" type="text" value="${entity.name}" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4 required" >编码</label>
							<div class="col-md-8" >
								<input name="code" id="code" type="text" value="${entity.code}" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4" >日期</label>
							<div class="col-md-8" >
								<input name="datecols" id="datecols" type="text" value="${entity.datecols}" class="Wdate form-control" onclick="WdatePicker();">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4" >日期时间</label>
							<div class="col-md-8" >
								<input name="timecols" id="timecols" type="text" value="${entity.timecols}" class="Wdate form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});">
							</div>
						</div>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-md-2" >是否显示</label>
							<div class="col-md-10" >
								<input id="isDisplayUuid" name="isDisplay.uuid" type="hidden" value="${entity.isDisplay}">
								<input id="isDisplayName" name="isDisplayName" type="text" class="form-control" readonly="readonly" value="${entity.isDisplay}">
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
							<label class="control-label col-md-1" style="width: 11.11%;">备注</label>
							<div class="col-md-11" style="width: 88.89%;">
								<textarea name="type111" id="type111" class="form-control">${entity.type111}</textarea>
							</div>
						</div>
					</div>
				</div>

			</form>
		</div>
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
</body>
</html>
