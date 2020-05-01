package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Update("Update t_user " +
            "set nickname = #{nickname} " +
            "where user_id = #{userId}")
    int updateNickname(@Param("userId") String userId, @Param("nickname") String nickname);

    @Update("Update t_user " +
            "set intro = #{intro} " +
            "where user_id = #{userId}")
    int updateIntro(@Param("userId") String userId, @Param("intro") String intro);

    @Update("Update t_user " +
            "set location = #{location} " +
            "where user_id = #{userId}")
    int updateLocation(@Param("userId") String userId, @Param("location") String location);

    @Update("Update t_user " +
            "set avatar = #{picname} " +
            "where user_id = #{userId}")
    int updateAvatar(@Param("userId") String userId,@Param("picname") String picname);

    @Select("select * " +
            "from t_user " +
            "where nickname like #{keyWord} " +
            "or intro like #{keyWord}")
    List<User> queryByKeyWord(String keyWord);

}
