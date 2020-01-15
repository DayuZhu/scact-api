package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.BankInfo;
import com.sc.act.api.model.auto.BankInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankInfoMapper {
    long countByExample(BankInfoExample example);

    int deleteByExample(BankInfoExample example);

    int deleteByPrimaryKey(Integer bankInfoId);

    int insert(BankInfo record);

    int insertSelective(BankInfo record);

    List<BankInfo> selectByExample(BankInfoExample example);

    BankInfo selectByPrimaryKey(Integer bankInfoId);

    int updateByExampleSelective(@Param("record") BankInfo record, @Param("example") BankInfoExample example);

    int updateByExample(@Param("record") BankInfo record, @Param("example") BankInfoExample example);

    int updateByPrimaryKeySelective(BankInfo record);

    int updateByPrimaryKey(BankInfo record);
}