package com.zd1024.withyou.entity.QsTestBank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("t_usertestrecord")
public class UserTestRecord {

    @TableId
    private String trId;

    private String userId;

    private Integer trScore;

    private String trAnswer;

    private String qsId;

    private Date testTime;
}
