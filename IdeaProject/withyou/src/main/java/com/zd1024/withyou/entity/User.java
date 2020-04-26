package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "t_User")
public class User {
    private String id;
    private String nickname;
    private String userId;
    private String intro;
    private String avatar;
    private Date createTime;
    private String location;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userId='" + userId + '\'' +
                ", intro='" + intro + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", location='" + location + '\'' +
                '}';
    }
}
