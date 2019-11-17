package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.ActivityWinsPdt;
import com.sc.act.api.model.auto.ActivityWinsPdtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityWinsPdtMapper {
    long countByExample(ActivityWinsPdtExample example);

    int deleteByExample(ActivityWinsPdtExample example);

    int deleteByPrimaryKey(Integer activityWinsPdtId);

    int insert(ActivityWinsPdt record);

    int insertSelective(ActivityWinsPdt record);

    List<ActivityWinsPdt> selectByExample(ActivityWinsPdtExample example);

    ActivityWinsPdt selectByPrimaryKey(Integer activityWinsPdtId);

    int updateByExampleSelective(@Param("record") ActivityWinsPdt record, @Param("example") ActivityWinsPdtExample example);

    int updateByExample(@Param("record") ActivityWinsPdt record, @Param("example") ActivityWinsPdtExample example);

    int updateByPrimaryKeySelective(ActivityWinsPdt record);

    int updateByPrimaryKey(ActivityWinsPdt record);
}