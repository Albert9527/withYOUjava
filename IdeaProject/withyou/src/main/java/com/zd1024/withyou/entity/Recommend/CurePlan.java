package com.zd1024.withyou.entity.Recommend;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@TableName("t_rcmcureplan")
public class CurePlan {

    @TableId
    private String rcmcpId;

    private String rcmcpName;

    private String rcmcpIntro;

    private String userId;

    private String categoryTag;

    private Integer praiseCount;

    //发布日期
    private Date createTime;

    private String rcmcpReason;

    //创始人
    private String founder;

}
