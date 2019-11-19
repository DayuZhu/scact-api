package com.sc.act.api.mapper.ext;

import java.util.List;

public interface TicketExtMapper {


    List<Integer> selectDistinctNominalValue();

    Integer selectNominalValueTotal();

}