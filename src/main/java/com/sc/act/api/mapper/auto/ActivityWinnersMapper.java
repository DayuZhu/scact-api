package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.ActivityWinners;
import com.sc.act.api.model.auto.ActivityWinnersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityWinnersMapper {
    long countByExample(ActivityWinnersExample example);

    int deleteByExample(ActivityWinnersExample example);

    int deleteByPrimaryKey(Integer activityWinnersId);

    int insert(ActivityWinners record);

    int insertSelective(ActivityWinners record);

    List<ActivityWinners> selectByExample(ActivityWinnersExample example);

    ActivityWinners selectByPrimaryKey(Integer activityWinnersId);

    int updateByExampleSelective(@Param("record") ActivityWinners record, @Param("example") ActivityWinnersExample example);

    int updateByExample(@Param("record") ActivityWinners record, @Param("example") ActivityWinnersExample example);

    int updateByPrimaryKeySelective(ActivityWinners record);

    int updateByPrimaryKey(ActivityWinners record);
}