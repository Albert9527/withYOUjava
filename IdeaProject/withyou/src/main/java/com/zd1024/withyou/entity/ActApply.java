package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_actapply")
public class ActApply {
    //申请表ID
    private String actApplyId;

    //活动id
    private String actId;

    //申请者ID
    private String userId;

    //申请时间
    private Date actApplyTime;

    //申请理由
    private String actApplyReason;

    //审核状态
    private Integer actAuditState;
}

