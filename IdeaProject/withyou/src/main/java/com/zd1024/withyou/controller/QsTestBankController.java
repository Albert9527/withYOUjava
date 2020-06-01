package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.entity.QsTestBank.QsAnalysis;
import com.zd1024.withyou.entity.QsTestBank.QsBank;
import com.zd1024.withyou.entity.QsTestBank.QsDetails;
import com.zd1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.QsTestBnakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/qsBank")
@ResponseBody
public class QsTestBankController {

    @Autowired
    private QsTestBnakService qsTestBnakService;

    @GetMapping("/getAllQs")
    public AndroidData getAllQsByctgy22(Integer current) {
        ObjVo objVo = qsTestBnakService.getAllQuestion(current, 10);

        return DataDealUtil.dealdata(objVo.getDatalist());
    }

    @GetMapping("/getQsDetails")
    public AndroidData getQsDetailsbyQsID(String qsId) {
        List<QsDetails> qsDetails = qsTestBnakService.getQsDetailsByQsId(qsId);
        return DataDealUtil.dealdata(qsDetails);
    }

    @PostMapping("/DoAnalysis")
    public AndroidData getQsAnalysis(@RequestBody UserTestRecord utrd) {
        Date date = new Date(System.currentTimeMillis());
        utrd.setTestTime(date);
        int rs = qsTestBnakService.addNewRecord(utrd);
        if (rs == 1) {
            QsAnalysis qsAnalysis = qsTestBnakService.getQsAnalysisByQsId(utrd.getQsId(), utrd.getTrScore());
            return DataDealUtil.dealbeanData(qsAnalysis, "测试结果分析");
        }else
            return DataDealUtil.dealbeanData(null,"数据提交");
    }
}
