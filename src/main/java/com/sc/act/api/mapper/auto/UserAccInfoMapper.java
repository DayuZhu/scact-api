package com.sc.act.api.mapper.auto;

import com.sc.act.api.model.auto.UserAccInfo;
import com.sc.act.api.model.auto.UserAccInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccInfoMapper {
    long countByExample(UserAccInfoExample example);

    int deleteByExample(UserAccInfoExample example);

    int deleteByPrimaryKey(Integer userAccInfoId);

    int insert(UserAccInfo record);

    int insertSelective(UserAccInfo record);

    List<UserAccInfo> selectByExample(UserAccInfoExample example);

    UserAccInfo selectByPrimaryKey(Integer userAccInfoId);

    int updateByExampleSelective(@Param("record") UserAccInfo record, @Param("example") UserAccInfoExample example);

    int updateByExample(@Param("record") UserAccInfo record, @Param("example") UserAccInfoExample example);

    int updateByPrimaryKeySelective(UserAccInfo record);

    int updateByPrimaryKey(UserAccInfo record);
}