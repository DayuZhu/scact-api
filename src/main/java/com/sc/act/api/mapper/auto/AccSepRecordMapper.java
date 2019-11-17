package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.AccSepRecord;
import com.sc.act.api.model.auto.AccSepRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccSepRecordMapper {
    long countByExample(AccSepRecordExample example);

    int deleteByExample(AccSepRecordExample example);

    int deleteByPrimaryKey(Integer accSepRecordId);

    int insert(AccSepRecord record);

    int insertSelective(AccSepRecord record);

    List<AccSepRecord> selectByExample(AccSepRecordExample example);

    AccSepRecord selectByPrimaryKey(Integer accSepRecordId);

    int updateByExampleSelective(@Param("record") AccSepRecord record, @Param("example") AccSepRecordExample example);

    int updateByExample(@Param("record") AccSepRecord record, @Param("example") AccSepRecordExample example);

    int updateByPrimaryKeySelective(AccSepRecord record);

    int updateByPrimaryKey(AccSepRecord record);
}