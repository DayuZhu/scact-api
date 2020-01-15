package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.MerchantAccInfo;
import com.sc.act.api.model.auto.MerchantAccInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantAccInfoMapper {
    long countByExample(MerchantAccInfoExample example);

    int deleteByExample(MerchantAccInfoExample example);

    int deleteByPrimaryKey(Integer merchantAccInfoId);

    int insert(MerchantAccInfo record);

    int insertSelective(MerchantAccInfo record);

    List<MerchantAccInfo> selectByExample(MerchantAccInfoExample example);

    MerchantAccInfo selectByPrimaryKey(Integer merchantAccInfoId);

    int updateByExampleSelective(@Param("record") MerchantAccInfo record, @Param("example") MerchantAccInfoExample example);

    int updateByExample(@Param("record") MerchantAccInfo record, @Param("example") MerchantAccInfoExample example);

    int updateByPrimaryKeySelective(MerchantAccInfo record);

    int updateByPrimaryKey(MerchantAccInfo record);
}