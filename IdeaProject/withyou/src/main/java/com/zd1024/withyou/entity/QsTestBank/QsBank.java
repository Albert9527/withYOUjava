package com.zd1024.withyou.entity.QsTestBank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@TableName("t_qsbank")
public class QsBank {

    @TableId
    private String qsId;

    private String qsName;

    private String qsIntro;

    private String qsPictrue;

    private Date qsCreateTime;

    private Integer qsTotal;

    private Integer qsOptionScore;

    @TableField(exist = false)
    private MultipartFile upPic;
}
