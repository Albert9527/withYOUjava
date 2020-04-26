package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_menber")
public class Menber {
    //成员表ID
    private String menberId;

    //活动ID
    private String actId;

    //参与者ID
    private String userId;

    //参与者角色
    private Integer menberRole;
}
