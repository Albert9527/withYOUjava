package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * 动态类
 * createby：zd
 */

@Data
@TableName("T_Dynamic")
public class Dynamic {

    //id
    private String dyId;

    //内容
    private String dyContent;

    //图片
    private String dyPictrue;

    //发布者
    private String userId;

    //发布时间
    private Date dyCreateTime;

    //分享来源
    private String dyShareId;

    //点赞数
    private Integer dyPraiseCount;

    //分享数
    private Integer dyShareCount;
}
