package com.sc.act.api.service;

import com.sc.act.api.model.auto.Activity;
import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;
import com.sc.act.api.model.bo.ProductPriceInfoBmo;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:活动中奖名服务类
 *
 * @className:ActivityWinnersService
 * @projectName:
 * @author:generater-code
 * @date: 2019-11-17 18:34:48
 */
public interface ActivityWinnersProductService {

    /**
     * 处理中奖产品名单
     *
     * @param list
     */
    void createWinnersProductInfo(List<ExcelWinnersInfoBmo> list,
                                  Activity activity,
                                  Date currentTime,
                                  List<ProductPriceInfoBmo> productPriceInfoList);

}
