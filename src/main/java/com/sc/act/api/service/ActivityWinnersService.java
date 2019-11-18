package com.sc.act.api.service;

import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;

import java.util.List;

/**
 * 功能描述:活动中奖名服务类
 *
 * @className:ActivityWinnersService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ActivityWinnersService {

    /**
     * 处理中奖名单
     *
     * @param list
     */
    void handlerWinnersInfo(List<ExcelWinnersInfoBmo> list);


}
