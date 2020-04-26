package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.ActApply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

public interface ActApplyMapper extends BaseMapper<ActApply> {

    @Select("Select act_start_time " +
            "from t_activity ta " +
            "where ta.act_id in " +
            "(select act_id " +
            "from t_menber m " +
            "where m.user_id = #{userid})")
    List<Date> getMyActivityDate(String userid);

    @Update("Update t_actapply " +
            "set act_audit_state = #{state} " +
            "where act_apply_id = #{actApplyId}")
    int updateApplyState(@Param("actApplyId") String actApplyId,@Param("state") Integer state);
}
