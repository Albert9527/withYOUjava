package com.zd1024.withyou.service;

import com.zd1024.withyou.entity.QsTestBank.QsAnalysis;
import com.zd1024.withyou.entity.QsTestBank.QsBank;
import com.zd1024.withyou.entity.QsTestBank.QsDetails;
import com.zd1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zd1024.withyou.entityVo.ObjVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface QsTestBnakService {
    ObjVo getAllQuestion(Integer current, Integer size);

    ObjVo getQsDetailsByQsId(Integer curren,Integer size,String qsId);

    QsAnalysis getQsAnalysisByQsId(String qsId,Integer score);

    ObjVo getQsAnalysisByQsId(Integer current,Integer size,String qsId);

    int addNewRecord(UserTestRecord utrd);

    int addNewQsBank(QsBank qsBank);

    int deleteQsBank(String qsId);

    Map addNewQSdetails(QsDetails details);

    Map addNewQsAnalysis(QsAnalysis analysis);

    int deleteQsDt(Integer qsNumber, String qsId);

    int deleteQsAlys(Integer sectionLeft, String qsId);

    ObjVo<UserTestRecord> getAllUtrd(Integer current, Integer size);

    ObjVo<QsAnalysis> searchQsAlys(Integer current, Integer size, String keyWord, String qsId);

    ObjVo<QsBank> searchQsBnak(Integer current, Integer size, String keyWord);

    ObjVo<QsDetails> searchQsdtInfo(Integer current, Integer size, String keyWord, String qsId);

    ObjVo<UserTestRecord> searchQsUtrd(Integer current, Integer size, String keyWord, String qsId);
}
