package cn.jeeweb.modules.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.modules.gencode.BeanUtils;
import cn.jeeweb.modules.gencode.entity.ColInfo;
import cn.jeeweb.modules.gencode.entity.EntityInfo;
import cn.jeeweb.modules.sys.service.IUserService;
import net.sf.json.JSONObject;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: FormDemoController.java
 * @package cn.jeeweb.modules.demo.controller
 * @description: 编辑器demo
 * @author: 王存见
 * @date: 2017年5月18日 下午6:17:24
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/demo/form")
public class FormDemoController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 
	 * @title: editor
	 * @description: 编辑器
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editor")
	public String editor() {
		return display("editor");
	}

	/**
	 * 
	 * @title: editor
	 * @description: 文件上传
	 * @return
	 * @return: String
	 */
	@RequestMapping("/upload")
	public String upload() {
		return display("upload");
	}
	
	@RequestMapping("/combox")
	public String combox(HttpServletRequest request) {
		return display("combox");
	}

	@RequestMapping("/ajaxCombox")
	@ResponseBody
	public List<?> ajaxCombox(HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		String userSql = "select * from sys_user WHERE realname like '%" + keyword + "%'";
		return userService.listBySql(userSql);
	}

	@RequestMapping("/bootstrapTable")
	public String bootstrapTable() {
		return display("bootstrap_table");
	}
	
	
	@RequestMapping("/codeList")
	public String codeList() {
		return display("code_list");
	}
	
	@ResponseBody
	@RequestMapping(value = "/genCode")
	public AjaxJson genCode(HttpServletRequest request, HttpServletResponse response, EntityInfo info,
			@RequestParam(value = "subList[]", required = false) String[] subArrs) throws Exception {
		AjaxJson arm=new AjaxJson();
		List<ColInfo> subList = this.convertToColsEntities(subArrs);
		
		BeanUtils.createCode(info, subList);
		
		arm.setRet(0);
		return arm;
	}
	
	private List<ColInfo> convertToColsEntities(String[] paramArr) {
		List<ColInfo> returnList = new ArrayList<>();
		if (paramArr == null || paramArr.length == 0){
		return returnList;
		}else{
		for (String data : paramArr) {
			JSONObject jsonObject = new JSONObject();
			String[] properties = data.split("&amp;");//data.split("&");
			for (String property : properties) {
				String[] nameAndValue = property.split("=");
				if (nameAndValue.length == 2) {
					try {
						nameAndValue[0] = URLDecoder.decode(nameAndValue[0], "UTF-8");
						nameAndValue[1] = URLDecoder.decode(nameAndValue[1], "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					jsonObject.put(nameAndValue[0], nameAndValue[1]);
				}
			}
			ColInfo obj = (ColInfo) JSONObject.toBean(jsonObject,ColInfo.class);
			if(((String) jsonObject.get("isMain")).equals("false")){
				obj.setMain(false);
			}
			if(((String) jsonObject.get("isRequired")).equals("false")){
				obj.setRequired(false);
			}
//			if(((String) jsonObject.get("isRow")).equals("false")){
//				obj.setRow(false);
//			}
			if(((String) jsonObject.get("isSearch")).equals("false")){
				obj.setSearch(false);
			}
			if(((String) jsonObject.get("isListVisiable")).equals("false")){
				obj.setListVisiable(false);
			}
			returnList.add(obj);
		}
		return returnList;
		}
	}
}
