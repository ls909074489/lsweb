package cn.jeeweb.modules.caipiao.service;

import java.util.List;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.caipiao.entity.CaiPiaoResultBean;
import cn.jeeweb.modules.caipiao.entity.CaipiaoEntity;
import cn.jeeweb.modules.caipiao.entity.EachartBean;

/**   
 * @Title: 通知公告
 * @Description: 通知公告
 * @author jeeweb
 * @date 2017-06-10 17:15:17
 * @version V1.0   
 *
 */
public interface ICaipiaoService extends ICommonService<CaipiaoEntity> {

	List<List<CaiPiaoResultBean>> getStatis(String day);

	EachartBean numStatis(String sDay, String eDay);

}