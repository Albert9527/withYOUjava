package com.zd1024.withyou.dao.QsTestBank;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd1024.withyou.entity.QsTestBank.QsAnalysis;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QsAnalysisMapper extends BaseMapper<QsAnalysis> {

    @Select("select * " +
            "from t_qsanalysis " +
            "where qs_id = #{qsId} " +
            "and #{score} > section_left " +
            "and #{score} <= section_right")
    QsAnalysis queryByQsIdAndScore(@Param("qsId") String qsId, @Param("score")Integer score);
}
