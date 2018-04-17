package cn.jeeweb.modules.gencode.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.gencode.entity.ColInfo;
import cn.jeeweb.modules.gencode.service.IColInfoService;

@Transactional
@Service("colInfoService")
public class ColInfoServiceImpl extends CommonServiceImpl<ColInfo> implements IColInfoService {

}
