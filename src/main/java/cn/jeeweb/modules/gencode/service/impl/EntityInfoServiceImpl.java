package cn.jeeweb.modules.gencode.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.modules.gencode.BeanUtils;
import cn.jeeweb.modules.gencode.entity.ColInfo;
import cn.jeeweb.modules.gencode.entity.EntityInfo;
import cn.jeeweb.modules.gencode.service.IColInfoService;
import cn.jeeweb.modules.gencode.service.IEntityInfoService;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;

@Transactional
@Service("entityInfoService")
public class EntityInfoServiceImpl extends CommonServiceImpl<EntityInfo> implements IEntityInfoService {
	@Autowired
	private IColInfoService colInfoService;

	@Override
	public AjaxJson createCode(EntityInfo info, List<ColInfo> subList) {
		AjaxJson arm=new AjaxJson();
		try {
			Principal principal=(Principal) SecurityUtils.getSubject().getPrincipal();
			User user = new User();
			user.setId(principal.getId());
//			info.setCreateBy(user);
			save(info);
			for(ColInfo col:subList){
//				col.setCreateBy(user);
				col.setEntityinfo(info);
			}
			colInfoService.batchSave(subList);
			BeanUtils.createCode(info, subList);
			arm.setRet(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arm;
	}
}
