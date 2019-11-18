package com.sc.act.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.sc.act.api.mapper.auto.ActivityWinnersMapper;
import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;
import com.sc.act.api.service.ActivityWinnersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:活动中奖名服务实现类
 *
 * @className:ActivityWinnersServiceImpl
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@Service
public class ActivityWinnersServiceImpl implements ActivityWinnersService {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinnersServiceImpl.class);

    @Autowired
    private ActivityWinnersMapper activityWinnersMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handlerWinnersInfo(List<ExcelWinnersInfoBmo> list, Integer activityId) {
        LOG.info("进入处理中奖名单服务请求参数list={}", JSON.toJSONString(list));
        //


    }


}
