package com.sc.act.api.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.commons.web.util.SnowflakeUitl;
import com.sc.act.api.mapper.auto.MerchantAccountMapper;
import com.sc.act.api.mapper.auto.TicketMapper;
import com.sc.act.api.mapper.ext.MerchantAccountExtMapper;
import com.sc.act.api.mapper.ext.TicketExtMapper;
import com.sc.act.api.model.auto.MerchantAccount;
import com.sc.act.api.model.auto.MerchantAccountExample;
import com.sc.act.api.model.auto.Ticket;
import com.sc.act.api.model.auto.TicketExample;
import com.sc.act.api.model.bo.ProductShopXoBmo;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MerchantAccountExtMapper merchantAccountExtMapper;

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;


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

    @Test
    public void test06() {
        List<Integer> productIdList = new ArrayList<>();
        productIdList.add(1);
        productIdList.add(2);
        productIdList.add(3);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("productIdList", JSON.toJSONString(productIdList));

        HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(productIdList), headers);
        ResponseEntity<Result> response = restTemplate.postForEntity(b2cUrl, request, Result.class);
        Result body = response.getBody();


        //Result<List<ProductShopXoBmo>>

        System.out.println(response.getBody());

        ResponseEntity<Result<List<ProductShopXoBmo>>> responseEntity = this.restTemplate
                .exchange(
                        b2cUrl,
                        HttpMethod.POST,
                        new HttpEntity<>(JSON.toJSONString(productIdList), headers),
                        new ParameterizedTypeReference<Result<List<ProductShopXoBmo>>>() {
                        });

        Result<List<ProductShopXoBmo>> body1 = responseEntity.getBody();
        System.out.println(body1);

    }

    @Test
    public void test07() {

        Integer merchantId = 123;
        MerchantAccountExample merchantAccountExample = new MerchantAccountExample();
        merchantAccountExample.createCriteria().andMerchantIdEqualTo(merchantId);
        List<MerchantAccount> merchantAccounts = merchantAccountMapper.selectByExample(merchantAccountExample);
        if (CollectionUtils.isEmpty(merchantAccounts)) {
            LOG.error("处理中奖名单券商户账户不存在");
            throw new BaseRuntimeException(ResultEnum.MERCHANT_ACCOUNT_INFO_ERROR);
        }

        MerchantAccount merchantAccount = merchantAccounts.get(0);

        Integer balance = merchantAccount.getBalance();
        int i = merchantAccountExtMapper.updateByBalanceAndMerchantIdSelective(new Date(), 500, balance, merchantId);

        System.out.println(i);
    }


}
