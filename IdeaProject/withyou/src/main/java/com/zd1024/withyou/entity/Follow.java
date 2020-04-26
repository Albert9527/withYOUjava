package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_follow")
public class Follow {

    private String followId;

    private String userId;

    private String targetId;

    private Integer istwoway;
}
