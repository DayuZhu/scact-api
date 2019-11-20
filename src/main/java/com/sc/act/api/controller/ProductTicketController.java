package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.model.auto.Ticket;
import com.sc.act.api.service.ProductTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 功能描述: 产品与券码关系控制类
 *
 * @className:ProductTicketController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/product/ticket")
@Api(value = "产品与券码关系控制类", tags = "产品与券码关系控制类")
public class ProductTicketController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductTicketController.class);

    @Autowired
    private ProductTicketService productTicketService;


    @ApiOperation("查询券明细信息")
    @GetMapping("/info")
    public Result<List<Ticket>> queryInfo(@NotNull @RequestParam(name = "outProductId") Integer outProductId) {
        LOG.info("查询券明细信息请求参数outProductId={}", outProductId);
        Result<List<Ticket>> result = new Result<>();
        List<Ticket> tickets = productTicketService.selectProductTicketContent(outProductId);
        result.setRetMsg("查询成功");
        result.setData(tickets);
        return result;
    }


}
