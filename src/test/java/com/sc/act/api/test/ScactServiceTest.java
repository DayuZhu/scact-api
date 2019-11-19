package com.sc.act.api.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.util.SnowflakeUitl;
import com.sc.act.api.mapper.auto.TicketMapper;
import com.sc.act.api.mapper.ext.TicketExtMapper;
import com.sc.act.api.model.auto.Ticket;
import com.sc.act.api.model.auto.TicketExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScactServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(ScactServiceTest.class);

    private static final List<Integer> LIST_TICKET_PRICE = Lists.newArrayList(100, 500, 1000, 10000, 50000, 100000);

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketExtMapper ticketExtMapper;

    @Value("${shopxo.url}")
    private String b2cUrl;

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

    @Test
    public void test02() {
        LOG.info("业务 测试 ");
        List<Integer> list = ticketExtMapper.selectDistinctNominalValue();
        System.out.println(JSON.toJSONString(list));

    }

    @Test
    public void test03() {
        LOG.info("业务 测试 ");
        TicketExample ticketExample = new TicketExample();
        ticketExample.setOrderByClause(" ticket_id desc");
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andStateEqualTo(CommonConstant.PRODUCT_TICKET_0);
        criteria.andNominalValueEqualTo(100000);
        PageHelper.startPage(0, 2, false);
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        System.out.println(JSON.toJSONString(tickets));

    }

    @Test
    public void test04() {
        LOG.info("业务 测试 ");
        List<Integer> list = ticketExtMapper.selectDistinctNominalValue();

        List<Ticket> resultTicket = new ArrayList<>();

        //如果金额等于0 continue;

        int yu = 692300;
        int wPrice = 0;
        for (Integer price : list) {
            int sum = resultTicket.stream().mapToInt(Ticket::getNominalValue).sum();
            if (sum == 692300) {
                break;
            }
            if (yu < price) {
                continue;
            }

            int count = queryTicketCount(price);
            if (count == 0) {
                continue;
            }

            int i = yu % price;
            if (i == 0) {
                int pageSize = yu / price;
                if (pageSize > count) {
                    List<Ticket> tickets = queryTicketInfo(price, count);
                    //绑定券
                    resultTicket.addAll(tickets);
                    yu = yu - price * count;
                    continue;
                }

                List<Ticket> tickets = queryTicketInfo(price, pageSize);
                //绑定券
                resultTicket.addAll(tickets);

            } else {
                int pageSize = yu / price;
                if (pageSize > count) {
                    List<Ticket> tickets = queryTicketInfo(price, count);
                    //绑定券
                    resultTicket.addAll(tickets);
                    yu = yu - price * count;
                    continue;
                }

                List<Ticket> tickets = queryTicketInfo(price, pageSize);
                //绑定券
                resultTicket.addAll(tickets);
                yu = yu % price;
            }
            wPrice = price;
        }
        System.out.println(wPrice);
        System.out.println(JSON.toJSONString(resultTicket));

    }


    private int queryTicketCount(Integer price) {

        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andStateEqualTo(CommonConstant.PRODUCT_TICKET_0);
        criteria.andNominalValueEqualTo(price);
        long l = ticketMapper.countByExample(ticketExample);
        return (int) l;
    }

    private List<Ticket> queryTicketInfo(Integer price, int pageSize) {

        TicketExample ticketExample = new TicketExample();
        ticketExample.setOrderByClause(" ticket_id desc");
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andStateEqualTo(CommonConstant.PRODUCT_TICKET_0);
        criteria.andNominalValueEqualTo(price);
        PageHelper.startPage(1, pageSize, false);
        return ticketMapper.selectByExample(ticketExample);
    }

    @Test
    public void test05() {
        System.out.println(b2cUrl);
    }


}
