package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.Dynamic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 动态类Mapper映射
 * create by zd
 */
public interface DyMapper extends BaseMapper<Dynamic> {

    @Select("Select * from t_dynamic " +
            "where user_id = #{userid} or user_id in"+
            "( select target_id " +
            "from t_follow " +
            "where user_id = #{userid}) " +
            "order by dy_create_time desc")
    List<Dynamic> selectMyfollowDy(String userid);


}
