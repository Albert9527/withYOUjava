package com.zd1024.withyou.controller.admin;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/admin/qstest")
public class AQsTestBankController {

    @Autowired
    private QsTestBnakService qsTestBnakService;

    @GetMapping("/getAllQs")
    public String getAllQsByctgy22(Integer current, Model model) {
        ObjVo<QsBank> qsBankObjVo = qsTestBnakService.getAllQuestion(current, 2);

        model.addAttribute("qsBankVo", qsBankObjVo);
        return "qstestbank/qsbank";
    }

    @PostMapping("/addqsbanke")
    public String addNewQsBank(QsBank qsBank, Model model) {
        int rs = qsTestBnakService.addNewQsBank(qsBank);
        if (rs == 1) {
            model.addAttribute("msg", "题集添加成功");
        } else {
            model.addAttribute("msg", "题集添加失败");
        }
        return getAllQsByctgy22(1, model);
    }

    @GetMapping("/deleteQsBank")
    public String deleteQsBank(Integer current, String qsId, Model model) {
        if (qsTestBnakService.deleteQsBank(qsId) == 1) {
            model.addAttribute("msg", "题集删除成功");
        } else {
            model.addAttribute("msg", "题集删除失败");
        }
        return getAllQsByctgy22(current, model);
    }

    @GetMapping("/getQsDetails")
    private String getallQsDetails(Integer current, String qsId, Model model) {
        ObjVo<QsDetails> qsDetailsObjVo = qsTestBnakService.getQsDetailsByQsId(current, 2, qsId);

        model.addAttribute("qsdtVo", qsDetailsObjVo);
        model.addAttribute("qsId",qsId);
        return "qstestbank/qsdtInfo";
    }

    @PostMapping("/addqsdt")
    public String addQsDt(QsDetails details,Model model){
        Map map = qsTestBnakService.addNewQSdetails(details);
        if ("1".equals(map.get("rs"))) {
            model.addAttribute("msg", "试题添加成功");
        } else {
            model.addAttribute("msg", "试题添加失败"+"  错误："+map.get("msg"));
        }
        return getallQsDetails(1,details.getQsId(),model);
    }

    @GetMapping("/deleteQsDt")
    private String deleteQsDt(Integer current,Integer qsNumber,String qsId,Model model){
        if (qsTestBnakService.deleteQsDt(qsNumber,qsId) == 1) {
            model.addAttribute("msg", "题集删除成功");
        } else {
            model.addAttribute("msg", "题集删除失败");
        }
        return getallQsDetails(current, qsId,model);
    }

    @GetMapping("/getQsAnalysis")
    private String getallQsAlys(Integer current, String qsId, Model model) {
        ObjVo<QsAnalysis> qsAnalysisObjVo = qsTestBnakService.getQsAnalysisByQsId(current, 2, qsId);

        model.addAttribute("qsalysVo", qsAnalysisObjVo);
        model.addAttribute("qsId",qsId);
        return "qstestbank/qsalysInfo";
    }

    @PostMapping("/addAlys")
    public String addQsAnalysis(QsAnalysis analysis, Model model){
        Map map = qsTestBnakService.addNewQsAnalysis(analysis);
        if ("1".equals(map.get("rs"))) {
            model.addAttribute("msg", "分析标准添加成功");
        } else {
            model.addAttribute("msg", "分析标准添加失败"+"  错误："+map.get("msg"));
        }
        return getallQsAlys(1,analysis.getQsId(),model);
    }

    @GetMapping("/deleteAlys")
    public String deleteAnalysis(Integer current,Integer sectionLeft,String qsId,Model model){
        int rs = qsTestBnakService.deleteQsAlys(sectionLeft,qsId);
        if (rs == 1){
            model.addAttribute("msg","分析标准删除成功");
        }else {
            model.addAttribute("msg","分析标准删除失败");
        }
       return getallQsAlys(current,qsId,model);
    }

    @GetMapping("/getAllUtrd")
    public String getAllUTRD(Integer current,Model model){
        ObjVo<UserTestRecord> allUtrd = qsTestBnakService.getAllUtrd(current,2);
        model.addAttribute("utrdVo",allUtrd);
        return "qstestbank/utrdInfo";
    }
}
