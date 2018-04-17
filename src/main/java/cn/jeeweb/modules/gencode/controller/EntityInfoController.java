package cn.jeeweb.modules.gencode.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.utils.Json;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.gencode.entity.ColInfo;
import cn.jeeweb.modules.gencode.entity.EntityInfo;
import cn.jeeweb.modules.gencode.service.IColInfoService;
import cn.jeeweb.modules.gencode.service.IEntityInfoService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("${admin.url.prefix}/codegen/entityinfo")
public class EntityInfoController extends BaseCRUDController<EntityInfo, String> {
	
	@Autowired
	private IEntityInfoService entityInfoService;
	@Autowired
	private IColInfoService colInfoService;
	

	@RequestMapping("/codeList")
	public String codeList() {
		return display("code_list");
	}
	
	@ResponseBody
	@RequestMapping(value = "/genCode")
	public AjaxJson genCode(HttpServletRequest request, HttpServletResponse response, EntityInfo info,
			@RequestParam(value = "subList[]", required = false) String[] subArrs) throws Exception {
		List<ColInfo> subList = this.convertToColsEntities(subArrs);
		return entityInfoService.createCode(info, subList);
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

	@Override
	public String onEdit(@RequestParam(value = "id", required = true) String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(OPENSTATE, OPENSTATE_EDIT);
		EntityInfo entity = get(id);
		preEdit(entity, model, request, response);
		model.addAttribute("entity", entity);
		List<ColInfo> colList=colInfoService.list("entityinfo.id", entity.getId());
		model.addAttribute("colList", colList);
		model.addAttribute("colListJson", Json.toJson(colList));
		String updateView = showUpdate(newModel(), model, request, response);
		if (!StringUtils.isEmpty(updateView)) {
			return updateView;
		}
		return display("edit");
	}

	@Override
	public String onDetail(@RequestParam(value = "id", required = true) String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute(OPENSTATE, OPENSTATE_DETAIL);
		return super.onDetail(id, model, request, response);
	}
	
	
}
