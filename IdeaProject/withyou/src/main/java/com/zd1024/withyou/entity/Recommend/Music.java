package com.zd1024.withyou.entity.Recommend;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@TableName("t_rcmmusic")
public class Music {

    @TableId
    private String rcmMusicId;

    private String rcmMusicName;

    private String firstPictrue;

    private String playAddress;

    private String userId;

    private String singer;

    private Date createTime;

    private String rcmMusicIntro;

    private String categoryTag;

    private String rcmReason;

    private Integer praiseCount;

    @TableField(exist = false)
    private MultipartFile upPic;
}
