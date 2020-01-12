package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.Merchant;
import com.sc.act.api.model.auto.MerchantExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantMapper {
    long countByExample(MerchantExample example);

    int deleteByExample(MerchantExample example);

    int deleteByPrimaryKey(Integer merchantId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    List<Merchant> selectByExample(MerchantExample example);

    Merchant selectByPrimaryKey(Integer merchantId);

    int updateByExampleSelective(@Param("record") Merchant record, @Param("example") MerchantExample example);

    int updateByExample(@Param("record") Merchant record, @Param("example") MerchantExample example);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);
}