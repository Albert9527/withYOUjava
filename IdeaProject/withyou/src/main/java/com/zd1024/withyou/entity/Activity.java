package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("t_Activity")
public class Activity {

    private String actId;

    private String actTitle;

    private String userId;

    private String actAddress;

    private Date actStartTime;

    private Integer actDuration;

    private String actIntro;

    private Integer actScale;

    private String actTag;

    private Integer actAuditState;

    private String actPic;
}
