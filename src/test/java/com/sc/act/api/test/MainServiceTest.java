package com.sc.act.api.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceTest.class);

    @Test
    public void test01() {
        LOG.info("测试 test01");
        System.out.println(Math.random() * 9 + 1);
    }


}
