package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.MerchantAccountRecord;
import com.sc.act.api.model.auto.MerchantAccountRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantAccountRecordMapper {
    long countByExample(MerchantAccountRecordExample example);

    int deleteByExample(MerchantAccountRecordExample example);

    int deleteByPrimaryKey(Integer merchantAccountRecordId);

    int insert(MerchantAccountRecord record);

    int insertSelective(MerchantAccountRecord record);

    List<MerchantAccountRecord> selectByExample(MerchantAccountRecordExample example);

    MerchantAccountRecord selectByPrimaryKey(Integer merchantAccountRecordId);

    int updateByExampleSelective(@Param("record") MerchantAccountRecord record, @Param("example") MerchantAccountRecordExample example);

    int updateByExample(@Param("record") MerchantAccountRecord record, @Param("example") MerchantAccountRecordExample example);

    int updateByPrimaryKeySelective(MerchantAccountRecord record);

    int updateByPrimaryKey(MerchantAccountRecord record);
}