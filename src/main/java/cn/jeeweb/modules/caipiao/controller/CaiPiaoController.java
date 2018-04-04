package cn.jeeweb.modules.caipiao.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.modules.caipiao.DataDownUtil;
import cn.jeeweb.modules.caipiao.entity.CaiPiaoResultBean;
import cn.jeeweb.modules.caipiao.entity.CaipiaoBean;
import cn.jeeweb.modules.caipiao.entity.CaipiaoEntity;
import cn.jeeweb.modules.caipiao.entity.EachartBean;
import cn.jeeweb.modules.caipiao.service.ICaipiaoService;

/**
 * 
 * @author liusheng
 *
 */
@Controller
@RequestMapping(value = "/caipiao")
public class CaiPiaoController extends BaseCRUDController<CaipiaoEntity, String>{
	@Autowired
	private ICaipiaoService caiPiaoService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String view(Model model) {
		model.addAttribute("resultList", caiPiaoService.getStatis("20180224"));
		return "modules/demo/caipiao_list";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/genCaiPiao")
	public String genCaiPiao(HttpServletRequest request){
		Long t1=System.currentTimeMillis();
		for(int i=1;i<30;i++){
			List<CaipiaoBean> list=DataDownUtil.getCaiPiaoList(i, 1500);
			List<CaipiaoEntity> dest=new ArrayList<CaipiaoEntity>();
			try {
				for(CaipiaoBean bean:list){
					CaipiaoEntity entity=new CaipiaoEntity();
					org.apache.commons.beanutils.BeanUtils.copyProperties(entity, bean);
					String haoma=entity.getHaoMa();
					String []arr=haoma.split(",");
					entity.setWan(Integer.parseInt(arr[0]));
					entity.setWanScope(changeScore(Integer.parseInt(arr[0])));
					
					entity.setQian(Integer.parseInt(arr[1]));
					entity.setQianScope(changeScore(Integer.parseInt(arr[1])));
					
					entity.setBai(Integer.parseInt(arr[2]));
					entity.setBaiScope(changeScore(Integer.parseInt(arr[2])));
					
					entity.setShi(Integer.parseInt(arr[3]));
					entity.setShiScope(changeScore(Integer.parseInt(arr[3])));
					
					entity.setGe(Integer.parseInt(arr[4]));
					entity.setGeScope(changeScore(Integer.parseInt(arr[4])));
					
					dest.add(entity);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			caiPiaoService.batchSave(dest);
			System.out.println(i+"====="+(System.currentTimeMillis()-t1));
		}
		
//		List<CaipiaoEntity> dest=(List<CaipiaoEntity>) caiPiaoService.findAll(new Sort(Sort.Direction.ASC,"qiHao"));
//		List<CaipiaoEntity> result=new ArrayList<CaipiaoEntity> ();
//		for(CaipiaoEntity entity:dest){
//			System.out.println("list.add(new CaipiaoEntity(\""+entity.getQiHao()+"\", "+entity.getEndTime()+"l, "+entity.getOpenTime()+"l, \""+entity.getHaoMa()+"\"));");
//		}
		
		return "suc";
	}
	
	
	public Integer changeScore(Integer num){
		if(num>=5){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	public List<List<CaiPiaoResultBean>> getData(ServletRequest request, Model model) {
		return caiPiaoService.getStatis(request.getParameter("day"));
	}
	
	@ResponseBody
	@RequestMapping(value = "/numStatis")
	public EachartBean numStatis(ServletRequest request, Model model) {
//		return caiPiaoService.numStatis(request.getParameter("sDay"),request.getParameter("eDay"));
		return caiPiaoService.numStatis("2018-02-24", "2018-03-23");
	}
}
