package com.sc.act.api.test;

import com.alibaba.fastjson.JSON;
import com.sc.act.api.model.bo.ProductPriceInfoBmo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MainServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceTest.class);

    @Test
    public void test01() {
        LOG.info("测试 test01");
        System.out.println(Math.random() * 9 + 1);
        List<ProductPriceInfoBmo> list = new ArrayList<>();
        ProductPriceInfoBmo productPriceInfoBmo = new ProductPriceInfoBmo();
        productPriceInfoBmo.setProductId(32);
        productPriceInfoBmo.setPrice(100000);
        list.add(productPriceInfoBmo);
        System.out.println(JSON.toJSONString(list));
    }


}
