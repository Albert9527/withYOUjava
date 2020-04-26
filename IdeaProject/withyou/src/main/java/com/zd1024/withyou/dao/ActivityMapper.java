package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

public interface ActivityMapper extends BaseMapper<Activity> {

    @Update("Update t_activity " +
            "set act_audit_state = #{state}" +
            " where act_id = #{actId}")
    int UpdateActivityState(@Param("actId") String actId,@Param("state")int state);

    @Select("Select user_id " +
            "from t_activity " +
            "where act_id = #{actId}")
    String getuUserIdByActId(String actId);
}
