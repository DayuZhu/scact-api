package com.sc.act.api.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScactServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(ScactServiceTest.class);

    @Test
    public void test01() {
        LOG.info("业务 测试 ");

    }


}
