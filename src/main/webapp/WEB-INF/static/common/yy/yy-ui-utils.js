var YYUI= {};
var pageLayout;

YYUI.setUIDefault = function() {
	YYUI.setEditor();
	YYUI.setStyle();
	YYUI.setUIAction("yy-table-list");
	YYUI.setUIAction("yy-table-sublist");
}

YYUI.setEditor = function() {
	YYUI.setEnumField();
	//YYUI.setEnumTextField();
}

YYUI.setUIAction = function(tableID) {
	var table = jQuery('#'+tableID);
	table.find('.group-checkable').change(function () {
	    var set = jQuery(this).attr("data-set");
	    var checked = jQuery(this).is(":checked");
	    jQuery(set).each(function () {
	        if (checked) {
	            $(this).prop("checked", true);
	            $(this).parents('tr').addClass("active");
	        } else {
	            $(this).prop("checked", false);
	            $(this).parents('tr').removeClass("active");
	        }
	    });
	    //jQuery.uniform.update(set);
	});

	table.on('change', 'tbody tr .checkboxes', function () {
	    $(this).parents('tr').toggleClass("active");
	});
}

/**
 * 设置枚举数据列表 applyObj适用范围，不设表示整个页面
 */
YYUI.setEnumField = function(applyObj) {
	var map = YYDataUtils.getEnumMap();
	var selectArray = new Array();
	if(applyObj==null){
		selectArray = $("select.yy-input-enumdata");
	}else{
		selectArray = $("select.yy-input-enumdata",applyObj);
	}
	$(selectArray).each(function() {
		var grpcode = $(this).attr("data-enum-group");
		var grpvalue = $(this).attr("data-enum-value");
		if($(this).find('option').length == 0){
			var select = "<option value=''>&nbsp;</option>";
			
			var enumdatas = map[grpcode];
			if(enumdatas){
				if(grpvalue!=null && grpvalue!=''){
					for (i = 0; i < enumdatas.length; i++) {
						if(grpvalue.indexOf(enumdatas[i].enumdatakey) > -1 ){
							select = select + "<option value='" + enumdatas[i].enumdatakey + "'>" + enumdatas[i].enumdataname + "</option>";
						}
					}
				}else{
					for (i = 0; i < enumdatas.length; i++) {
						select = select + "<option value='" + enumdatas[i].enumdatakey + "'>" + enumdatas[i].enumdataname + "</option>";
					}
				}
				
			}
			$(this).append(select);
		}
	});
	
	//键值一样 start========edit by liusheng=========================
	var selectArray2 = new Array();
	if(applyObj==null){
		selectArray2 = $("select.yy-input-enumdata-kv");
	}else{
		selectArray2 = $("select.yy-input-enumdata-kv",applyObj);
	}
	$(selectArray2).each(function() {
		var grpcode = $(this).attr("data-enum-group");
		if($(this).find('option').length == 0){
			var select = "<option value=''>&nbsp;</option>";
			var enumdatas = map[grpcode];
			if(enumdatas){
				for (i = 0; i < enumdatas.length; i++) {
					select = select + "<option value='" + enumdatas[i].enumdataname + "'>" + enumdatas[i].enumdataname + "</option>";
				}
			}
			$(this).append(select);
		}
	});
	//键值一样 end=============edit by liusheng====================
}

YYUI.setStyle = function() {
	$(".yy-toolbar button").addClass("btn btn-sm btn-info");
	$(".yy-searchbar button").addClass("btn btn-sm btn-info");
	$(".yy-table").addClass("table table-striped table-bordered table-hover");
	$(".yy-table-x").addClass("table-striped table-bordered table-hover");
}

/**
 * 切换到编辑视图
 */
YYUI.setEditMode= function() {
	if(!$('#yy-page-list').hasClass("hide")){
		$('#yy-page-list').addClass("hide");
	}
	if(!$('#yy-page-detail').hasClass("hide")){
		$('#yy-page-detail').addClass("hide");
	}
	if($('#yy-page-edit').hasClass("hide")){
		$('#yy-page-edit').removeClass("hide");
	}
	if($('#yy-page-sublist').hasClass("hide")){
		$('#yy-page-sublist').removeClass("hide");
	}
	if(pageLayout){
		pageLayout.hide("west");
	}
	
	YYFormUtils.unlockForm();
}

/**
 * 为视图设置布局
 * 
 * @returns
 */
this.setBodyLayout = function() {
	pageLayout = $("body").layout({
		applyDefaultStyles : true,
		west : {
			size : 250
		}
	});
	return pageLayout;
}

this.getPageLayout = function(){
	return pageLayout;
}
/**
 * 切换到列表视图
 */
YYUI.setListMode= function() {
	if($('#yy-page-list').hasClass("hide")){
		$('#yy-page-list').removeClass("hide");
	}
	if(!$('#yy-page-edit').hasClass("hide")){
		$('#yy-page-edit').addClass("hide");
	}
	if(!$('#yy-page-detail').hasClass("hide")){
		$('#yy-page-detail').addClass("hide");
	}
	if(!$('#yy-page-sublist').hasClass("hide")){
		$('#yy-page-sublist').addClass("hide");
	}
	
	if(pageLayout){
		pageLayout.show("west");
	}
	
	YYFormUtils.lockForm();
	YYFormUtils.lockForm('yy-form-detail');
}

/**
 *  切换到查看视图
 */
YYUI.setDetailMode = function() {
	if(!$('#yy-page-list').hasClass("hide")){
		$('#yy-page-list').addClass("hide");
	}
	if(!$('#yy-page-edit').hasClass("hide")){
		$('#yy-page-edit').addClass("hide");
	}
	if($('#yy-page-detail').hasClass("hide")){
		$('#yy-page-detail').removeClass("hide");
	}
	if($('#yy-page-sublist').hasClass("hide")){
		$('#yy-page-sublist').removeClass("hide");
	}
	if(pageLayout){
		pageLayout.hide("west");
	}
	
	YYFormUtils.lockForm();
	YYFormUtils.lockForm('yy-form-detail');
}


/*
 * 成功，正确的提示信息
 * content 提示内容
 * time 提示时间长度（毫秒） 时间可以不传(默认3000毫秒)
 */
YYUI.succMsg=function(content,time){
	top.layer.msg(content, {icon: 1,time:time});
}

/*
 * 失败，错误的提示信息
 * content 提示内容
 * time 提示时间长度（毫秒） 时间可以不传(默认3000毫秒)
 */
YYUI.failMsg=function(content,time){
	top.layer.msg(content, {icon: 2,time:time});
}

/*
 * 提示框，并设置弹出时间
 * time 提示时间长度（毫秒） 时间可以不传(默认3000毫秒)
 */
YYUI.promMsg=function(content,time){
	top.layer.msg(content, {icon: 0,time: time});
}

/*
 * 提示框 ,alert提示框
 * time 提示时间长度（毫秒） 时间可以不传(默认3000毫秒)
 */
YYUI.promAlert=function(content,time){
	top.layer.alert(content, {icon: 0,time: time});
}
/*
 * 错误提示框 
 * content 提示内容
 * time 提示时间长度（毫秒） 时间可以不传(默认3000毫秒)
 */
YYUI.failAlert=function(content,time){
	top.layer.alert(content, {icon: 2,time: time });
}

/*
 * 成功提示框 
 * content 提示内容
 * time 提示时间长度（毫秒） 时间可以不传(默认3000毫秒)
 */
YYUI.succAlert=function(content,time){
	top.layer.alert(content, {icon: 1,time: time });
}



//==================yy-msg start=======================================================================================
YYMsg = {};

var YY_ALERTMSG_MAP;
YYMsg.alertMsg = function(key, msgArr) {
	if (!YY_ALERTMSG_MAP) {
		//var alertmsgstr = localStorage.getItem("yy-alertmsg-map");
		//YY_ALERTMSG_MAP = JSON.parse(alertmsgstr);

		YY_ALERTMSG_MAP={
				"sys-submit-http":{uuid:"b8eecb01-3ead-4890-8b13-82a69a6cd10f",alertmsg:"提交失败，HTTP错误。"},
				"sys-success-todo":{uuid:"3c45004d-a242-424c-b926-c7a3d4ce0171",alertmsg:"{0}成功！"},
				"sys-fail-todo":{uuid:"c582c9e0-a4fe-4b1e-b81b-7c250a477080",alertmsg:"{0}失败，原因："},
				"sys-success-tosave":{uuid:"f1580bc9-2fbb-4534-be3a-d6488b8bb356",alertmsg:"保存成功！"},
				"sys-fail-tosave":{uuid:"0f257677-6946-4383-a45d-699737e5d398",alertmsg:"保存成功！"},
				"sys-delete-success":{uuid:"5e1b5917-d2ab-464e-a4ff-8abbcbcbfe35",alertmsg:"删除成功。"},
				"sys-delete-fail":{uuid:"97f49c35-fc1f-4fd0-9da7-c4294669c06c",alertmsg:"删除失败，原因："},
				"sys-delete-http":{uuid:"97f49c35-fc1f-4fd0-9da7-c4294669c06c",alertmsg:"删除失败，HTTP错误。"},
				"sys-to-select":{uuid:"7b8a7822-f564-440f-a3d5-72c94da95f28",alertmsg:"请选择记录"},
				"sys-confirm-delete":{uuid:"e4d65d58-1797-462d-a97c-44afc7aa658e",alertmsg:"确定要删除吗？"},
				"sys-select-only":{uuid:"7c866f7e-28a9-4a5f-bdf2-cafb8fb91be7",alertmsg:"只能选择一行记录"}
		};
	}
	var msgobject = YY_ALERTMSG_MAP[key];
	if (msgobject) {
		return formatMsg(msgobject.alertmsg, msgArr);
	} else {
		YYUI.promMsg("获取不到提示信息。");
	}
}

// 格式化消息
function formatMsg(msgstr, msgArr) {
	if (msgArr == null || msgArr.length == 0) {
		return msgstr;
	} else {
		for (var i = 0; i < msgArr.length; i++) {
			msgstr = msgstr.replace(new RegExp("\\{" + i + "\\}", "g"), msgArr[i]);
		}
		return msgstr;
	}

}

//==================layer dialog start=======================================================================================


//打开对话框(添加修改)
function openlsDialog(title,url,gridId,width,height){
	width = width?width:'800px';
	height = height?height:'500px';
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}

	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         //文档地址http://www.layui.com/doc/modules/layer.html#use
	         iframeWin.contentWindow.doLsSubmit(function(){
	        	 //判断逻辑并关闭
       	         setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
	        	 //刷新表单
       	         onRefreshTable(gridId);//refreshTable(gridId);
	         });
		  },
		 cancel: function(index){ 
		 }
	}); 	
}	

//打开对话框(查看)
function openlsDialogView(title,url,gridId,width,height){
	width = width?width:'800px';
	height = height?height:'500px';
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}

	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['关闭'],
		cancel: function(index){ 
		}
	}); 	
}	
//==================layer dialog end=======================================================================================

