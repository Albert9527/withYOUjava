package com.zd1024.withyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.MySHA512;
import com.zd1024.withyou.Util.PictrueDealUtil;
import com.zd1024.withyou.dao.QsTestBank.QsAnalysisMapper;
import com.zd1024.withyou.dao.QsTestBank.QsBankMapper;
import com.zd1024.withyou.dao.QsTestBank.QsDetailsMapper;
import com.zd1024.withyou.dao.QsTestBank.UTRecordMapper;
import com.zd1024.withyou.entity.QsTestBank.QsAnalysis;
import com.zd1024.withyou.entity.QsTestBank.QsBank;
import com.zd1024.withyou.entity.QsTestBank.QsDetails;
import com.zd1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.QsTestBnakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class QsTestBankServiceImpl implements QsTestBnakService {

    private MySHA512 mySHA512 = new MySHA512();

    @Autowired
    private QsBankMapper qsBankMapper;

    @Autowired
    private QsDetailsMapper qsDetailsMapper;

    @Autowired
    private QsAnalysisMapper qsAnalysisMapper;

    @Autowired
    private UTRecordMapper utRecordMapper;


    @Override
    public ObjVo getAllQuestion(Integer current, Integer size) {
        ObjVo<QsBank> objVo = new ObjVo<>();
        IPage<QsBank> page = new Page<>(current, size);

        qsBankMapper.selectPage(page, null);

        objVo.setCurrent(current);
        objVo.setSize(size);
        objVo.setTotal(page.getTotal());
        objVo.setPages(page.getPages());
        objVo.setDatalist(page.getRecords());

        return objVo;
    }

    @Override
    public int addNewQsBank(QsBank qsBank) {
        if (qsBank.getUpPic() != null) {
            qsBank.setQsPictrue(PictrueDealUtil.savePictrue(qsBank.getUpPic()));
        }
        qsBank.setQsId(mySHA512.getId("qsbank"));
        qsBank.setQsCreateTime(new Date(System.currentTimeMillis()));
        return qsBankMapper.insert(qsBank);
    }

    @Override
    public int deleteQsBank(String qsId) {
        return qsBankMapper.deleteById(qsId);
    }

    @Override
    public ObjVo getQsDetailsByQsId(Integer current, Integer size, String qsId) {
        QueryWrapper<QsDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qs_id", qsId)
                .orderByAsc("qs_number");

        IPage<QsDetails> page = new Page<>(current, size);
        qsDetailsMapper.selectPage(page, queryWrapper);

        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public Map addNewQSdetails(QsDetails details) {
        Map map = new HashMap();
        //System.out.println(details.toString());
        try {
            int rs = qsDetailsMapper.insert(details);
            map.put("rs", rs);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                map.put("msg", "数据重复！");
                map.put("rs", -1);
            }
        }
        return map;
    }

    @Override
    public int deleteQsDt(Integer qsNumber, String qsId) {
        QueryWrapper<QsDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qs_number", qsNumber)
                .eq("qs_id", qsId);
        return qsDetailsMapper.delete(queryWrapper);
    }

    @Override
    public QsAnalysis getQsAnalysisByQsId(String qsId, Integer score) {
        return qsAnalysisMapper.queryByQsIdAndScore(qsId, score);
    }

    @Override
    public ObjVo getQsAnalysisByQsId(Integer current, Integer size, String qsId) {
        QueryWrapper<QsAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qs_id", qsId);

        IPage<QsAnalysis> page = new Page<>(current, size);

        qsAnalysisMapper.selectPage(page, queryWrapper);
        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public Map addNewQsAnalysis(QsAnalysis analysis) {
        Map map = new HashMap();
        //System.out.println(analysis.toString());
        try {
            int rs = qsAnalysisMapper.insert(analysis);
            map.put("rs", rs);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                map.put("msg", "数据重复！");
                map.put("rs", -1);
            }
        }
        return map;
    }

    @Override
    public int deleteQsAlys(Integer sectionLeft, String qsId) {
        QueryWrapper<QsAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("section_left", sectionLeft)
                .eq("qs_id", qsId);
        return qsAnalysisMapper.delete(queryWrapper);
    }

    @Override
    public ObjVo<UserTestRecord> getAllUtrd(Integer current, Integer size) {
        IPage<UserTestRecord> page = new Page<>(current, size);

        utRecordMapper.selectPage(page, null);

        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public int addNewRecord(UserTestRecord utrd) {
        if (utrd != null) {
            utrd.setTrId(mySHA512.getId("utrd"));
            return utRecordMapper.insert(utrd);
        } else
            return -1;
    }

    @Override
    public ObjVo<QsAnalysis> searchQsAlys(Integer current, Integer size, String keyWord, String qsId) {
        IPage<QsAnalysis> page = new Page<>(current, size);
        QueryWrapper<QsAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("alys_content", keyWord)
                .or()
                .like("alys_proposal", keyWord);
        qsAnalysisMapper.selectPage(page, queryWrapper);
        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public ObjVo<QsBank> searchQsBnak(Integer current, Integer size, String keyWord) {
        IPage<QsBank> page = new Page<>(current, size);
        QueryWrapper<QsBank> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("qs_name", keyWord)
                .or()
                .like("qs_intro", keyWord);
        qsBankMapper.selectPage(page, queryWrapper);
        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public ObjVo<QsDetails> searchQsdtInfo(Integer current, Integer size, String keyWord, String qsId) {
        IPage<QsDetails> page = new Page<>(current, size);
        QueryWrapper<QsDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("qs_content", keyWord)
                .or()
                .like("qs_option1", keyWord)
                .or()
                .like("qs_option2", keyWord)
                .or()
                .like("qs_option3", keyWord)
                .or()
                .like("qs_option4", keyWord);
        qsDetailsMapper.selectPage(page, queryWrapper);
        return DataDealUtil.PageDataDeal(page);
    }

    @Override
    public ObjVo<UserTestRecord> searchQsUtrd(Integer current, Integer size, String keyWord, String qsId) {
        IPage<UserTestRecord> page = new Page<>(current, size);
        QueryWrapper<UserTestRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("tr_score", keyWord)
                .or()
                .like("user_id", keyWord);
        utRecordMapper.selectPage(page, queryWrapper);
        return DataDealUtil.PageDataDeal(page);
    }
}
