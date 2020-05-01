package com.zd1024.withyou.entity.QsTestBank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_qsdetails")
public class QsDetails implements Serializable {

    @TableId
    //题目序号
    private Integer qsNumber;

    //问题描述
    private String qsContent;

    @TableId
    //所属试题集
    private String qsId;

    private String userId;

    //正向或反向评分
    private Integer scoreRule;

    //选项
    private String qsOption1;

    private String qsOption2;

    //可空
    private String qsOption3;

    //可空
    private String qsOption4;
}
