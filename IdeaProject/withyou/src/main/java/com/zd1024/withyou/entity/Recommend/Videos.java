package com.zd1024.withyou.entity.Recommend;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@TableName("t_rcmvideos")
public class Videos {

    @TableId
    private String rcmvdId;

    //名称
    private String rcmvdTitle;

    //首图
    private String firstPictrue;

    //简介
    private String rcmvdIntro;

    //点映地址
    private String watchAddress;

    //发布者
    private String userId;

    //上映时间
    private String releaseTime;

    //导演
    private String director;

    //主演
    private String mainPerforme;

    //分类标签
    private String categoryTag;

    //推荐理由
    private String rcmReason;

    //点赞数
    private Integer praiseCount;

    @TableField(exist = false)
    private MultipartFile upPic;

}
