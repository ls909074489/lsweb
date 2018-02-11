package cn.jeeweb.modules.sms.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.JeewebPropertiesUtil;
import cn.jeeweb.modules.sms.entity.SmsSendLog;
import cn.jeeweb.modules.sms.service.ISmsSendLogService;

/**   
 * @Title: 短信发送日志
 * @Description: 短信发送日志
 * @author jeeweb
 * @date 2017-06-08 12:56:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sms/smssendlog")
@RequiresPathPermission("sms:smssendlog")
public class SmsSendLogController extends BaseCRUDController<SmsSendLog, String> {
	@Autowired
	private ISmsSendLogService smsSendLogService;
	
	
	@ResponseBody
	@RequestMapping(value = "/testcontrol")
	public AjaxJson testcontrol(HttpServletRequest request) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("删除成功");

		List<SmsSendLog> list=smsSendLogService.list();
		for(SmsSendLog log:list){
			log.setTemplateId("7");
			System.out.println(log.getTemplateId()+"===============66");
		}
		smsSendLogService.batchSave(list);
		return ajaxJson;
	}
}
