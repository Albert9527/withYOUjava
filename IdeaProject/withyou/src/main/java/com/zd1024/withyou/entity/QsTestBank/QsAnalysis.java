package com.zd1024.withyou.entity.QsTestBank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_qsanalysis")
public class QsAnalysis {

    //评分左区间
    @TableId
    private String sectionLeft;

    //评分右区间
    @TableId
    private String sectionRight;

    @TableId
    private String qsId;

    //测试结果
    private String alysContent;

    //建议
    private String alysProposal;
}
