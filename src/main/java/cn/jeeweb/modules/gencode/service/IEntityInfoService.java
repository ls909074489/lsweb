package cn.jeeweb.modules.gencode.service;

import java.util.List;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.modules.gencode.entity.ColInfo;
import cn.jeeweb.modules.gencode.entity.EntityInfo;

public interface IEntityInfoService extends ICommonService<EntityInfo> {

	public AjaxJson createCode(EntityInfo info, List<ColInfo> subList);

}
