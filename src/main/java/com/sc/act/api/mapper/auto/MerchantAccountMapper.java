package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.MerchantAccount;
import com.sc.act.api.model.auto.MerchantAccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantAccountMapper {
    long countByExample(MerchantAccountExample example);

    int deleteByExample(MerchantAccountExample example);

    int deleteByPrimaryKey(Integer merchantAccountId);

    int insert(MerchantAccount record);

    int insertSelective(MerchantAccount record);

    List<MerchantAccount> selectByExample(MerchantAccountExample example);

    MerchantAccount selectByPrimaryKey(Integer merchantAccountId);

    int updateByExampleSelective(@Param("record") MerchantAccount record, @Param("example") MerchantAccountExample example);

    int updateByExample(@Param("record") MerchantAccount record, @Param("example") MerchantAccountExample example);

    int updateByPrimaryKeySelective(MerchantAccount record);

    int updateByPrimaryKey(MerchantAccount record);
}