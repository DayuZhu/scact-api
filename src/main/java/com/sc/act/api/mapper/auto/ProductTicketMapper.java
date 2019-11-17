package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.ProductTicket;
import com.sc.act.api.model.auto.ProductTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTicketMapper {
    long countByExample(ProductTicketExample example);

    int deleteByExample(ProductTicketExample example);

    int deleteByPrimaryKey(Integer productTicketId);

    int insert(ProductTicket record);

    int insertSelective(ProductTicket record);

    List<ProductTicket> selectByExample(ProductTicketExample example);

    ProductTicket selectByPrimaryKey(Integer productTicketId);

    int updateByExampleSelective(@Param("record") ProductTicket record, @Param("example") ProductTicketExample example);

    int updateByExample(@Param("record") ProductTicket record, @Param("example") ProductTicketExample example);

    int updateByPrimaryKeySelective(ProductTicket record);

    int updateByPrimaryKey(ProductTicket record);
}