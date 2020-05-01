package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.Acount;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AcountMapper extends BaseMapper<Acount> {

    @Select("select user_id " +
            "from t_acount " +
            "where role = 1")
    List getAdminId();

    @Select("select user_id " +
            "from t_acount " +
            "where role = 0")
    List getVolunteerId();

    @Select("select user_id " +
            "from t_acount " +
            "where role = -1")
    List getUserId();
}
