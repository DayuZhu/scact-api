package com.sc.act.api.mapper.ext;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MerchantAccountExtMapper {

    int updateByBalanceAndMerchantIdSelective(@Param("updateTime") Date updateTime,
                                              @Param("sum") Integer sum,
                                              @Param("balance") Integer balance,
                                              @Param("merchantId") Integer merchantId);
}
