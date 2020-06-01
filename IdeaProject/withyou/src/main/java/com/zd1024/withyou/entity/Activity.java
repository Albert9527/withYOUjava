package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@TableName("t_activity")
public class Activity {

    @TableId
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

    private Integer act_join_num;

    @TableField(exist = false)
    private MultipartFile pic;
}
