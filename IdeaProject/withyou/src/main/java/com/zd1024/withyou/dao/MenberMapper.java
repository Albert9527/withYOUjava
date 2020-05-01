package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.Menber;
import com.zd1024.withyou.entityVo.MenberVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenberMapper extends BaseMapper<Menber> {

    @Select("Select act_id " +
            "from t_menber " +
            "where user_id = #{userid} and menber_role = 1")
    String selectActId(String userid);

    @Select("select u.user_id,u.nickname,u.intro,u.avatar,m.menber_role as role " +
            "from t_user u,t_menber m " +
            "where m.act_id = #{actId} and " +
            "u.user_id in " +
            "(select m.user_id " +
            "from t_menber " +
            "where  m.act_id = #{actId})")
    List<MenberVo> queryMenberInfoByActId(String actId);
}
