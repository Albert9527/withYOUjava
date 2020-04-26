package com.zd1024.withyou.entity.Recommend;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("t_rcmbook")
public class Book {

    @TableId
    private String rcmbookId;

    //书名
    private String rcmbookName;

    //首图地址
    private String firstPictrue;

    //作者
    private String rcmbookAuthor;

    //出版社
    private String rcmbookPress;

    //简介
    private String rcmbookIntro;

    //推荐理由
    private String rcmbookReason;

    //分类标签
    private String categoryTag;

    //发布者ID
    private String userId;

    //点赞数
    private Integer praiseCount;

    //出版日期
    private Date pubDate;
}
