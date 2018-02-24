package cn.jeeweb.modules.sms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.common.service.impl.RedisUtilsService;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sms.entity.SmsSendLog;
import cn.jeeweb.modules.sms.service.ISmsSendLogService;
import cn.jeeweb.modules.sys.entity.User;

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
	@Autowired
	private RedisUtilsService redisUtilsService;
	
	@ResponseBody
	@RequestMapping(value = "/testcontrol")
	public AjaxJson testcontrol(HttpServletRequest request) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("删除成功");
		
//		RedisTemplate<String, Object> redisTemplate=redisUtilsService.getRedisTemplate();
//		System.out.println(redisTemplate.boundValueOps(key).get(0,-1));
		
		String key="String:billcode:sl_sb:3333-SEQUENCE";
		String val="222222222架构之路之spring+redis的集成123456";
		
		redisUtilsService.putString(key,val);
		System.out.println("=======================================");
		System.out.println("val:"+redisUtilsService.getString(key)+"===111");
		System.out.println("=======================================");
		
		
		String listKey="smssenglog_list";
		List<SmsSendLog> list=smsSendLogService.list();
		redisUtilsService.addVOList(listKey, list);
		List<User> redisList=redisUtilsService.getVOList(listKey, User.class);
		System.out.println(redisList);
		
//		for(SmsSendLog log:list){
//			log.setTemplateId("999");
//			System.out.println(log.getTemplateId()+"===============66");
//			smsSendLogService.update(log);
//		}
//		smsSendLogService.batchSave(list);
		return ajaxJson;
	}
}
