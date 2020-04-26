package com.zd1024.withyou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.Follow;
import com.zd1024.withyou.entity.Friend;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FollowMapper extends BaseMapper<Follow>{

    @Select("Select u.nickname,u.avatar,u.intro,f.istwoway " +
            "from t_User u,t_Follow f " +
            "where u.user_id in"+
            "( select f.target_id " +
            "from t_Follow " +
            "where f.user_id = #{userid})")
    List<Friend> getMyFollow(String userid);

    @Select("Select u.nickname,u.avatar,u.intro,f.istwoway " +
            "from t_User u,t_Follow f " +
            "where u.user_id in"+
            "( select f.user_id " +
            "from t_Follow " +
            "where f.target_id = #{userid})")
    List<Friend> getFollowMe(String userid);

    @Update("update t_follow " +
            "set istwoway = #{istwoway} " +
            "where target_id = #{userid}")
    int updatetwoway(@Param("userid") String userid, @Param("istwoway") Integer istwoway);

    @Delete("Delete from t_follow " +
            "where user_id = #{userId} " +
            "and " +
            "target_id = #{targetId}")
    int cancelFollow(@Param("userId") String userId, @Param("targetId") String targetId);
}
