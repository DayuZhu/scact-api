package com.sc.act.api.test;

import com.google.common.collect.Lists;
import com.sc.act.api.commons.web.util.SnowflakeUitl;
import com.sc.act.api.mapper.auto.TicketMapper;
import com.sc.act.api.model.auto.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScactServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(ScactServiceTest.class);

    private static final List<Integer> LIST_TICKET_PRICE = Lists.newArrayList(100, 500, 1000, 10000, 50000, 100000);

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void test01() {
        LOG.info("业务 测试 ");
        Date currentTime = new Date();


        for (Integer value : LIST_TICKET_PRICE) {
            for (int i = 0; i < 50; i++) {
                Ticket ticket = new Ticket();
                ticket.setTicketCode(SnowflakeUitl.getSnowflakeId());
                ticket.setTicketPwd((int) ((Math.random() * 9 + 1) * 100000) + "");
                ticket.setNominalValue(value);
                ticket.setCreateTime(currentTime);
                ticket.setUpdateTime(currentTime);
                ticketMapper.insertSelective(ticket);
            }
        }
    }


}
