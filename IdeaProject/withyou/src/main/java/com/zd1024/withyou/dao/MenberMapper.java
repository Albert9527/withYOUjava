package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.Menber;
import org.apache.ibatis.annotations.Select;

public interface MenberMapper extends BaseMapper<Menber> {

    @Select("Select act_id " +
            "from t_menber " +
            "where user_id = #{userid} and menber_role = 1")
    String selectActId(String userid);
}
