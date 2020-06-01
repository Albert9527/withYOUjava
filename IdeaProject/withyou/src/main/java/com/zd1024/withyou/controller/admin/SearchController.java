package com.zd1024.withyou.controller.admin;

import com.zd1024.withyou.dao.UserMapper;
import com.zd1024.withyou.entity.Activity;
import com.zd1024.withyou.entity.QsTestBank.QsAnalysis;
import com.zd1024.withyou.entity.QsTestBank.QsBank;
import com.zd1024.withyou.entity.QsTestBank.QsDetails;
import com.zd1024.withyou.entity.QsTestBank.UserTestRecord;
import com.zd1024.withyou.entity.User;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.ActivityService;
import com.zd1024.withyou.service.QsTestBnakService;
import com.zd1024.withyou.service.RecommendService;
import com.zd1024.withyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private QsTestBnakService qsTestBnakService;

    @Autowired
    private RecommendService recommendService;

    @PostMapping("/search")
    public String searchBaseInfo(Integer current,String ctgy, String keyWord, Model model) {
        switch (ctgy) {
            case "user":
                ObjVo<User> userVo = userService.searchUser(current,5,keyWord);
                model.addAttribute("userVo",userVo);
                return "userinfo::userList";
            case "volunteer":
                ObjVo<User> vulunteerVo = userService.searchvolunteer(current,5,keyWord);
                model.addAttribute("vltuser",vulunteerVo);
                return "volunteer::volunteerList";
            case "activity":
                ObjVo<Activity> activityVo = activityService.searchActivity(current,5,keyWord);
                model.addAttribute("actVo",activityVo);
                return "activityinfo::actList";
            default:
                return "index";
        }

    }

    @PostMapping("/search/qstest")
    public String searchQsInfo(Integer current,String ctgy, String keyWord,String qsId, Model model) {
        switch (ctgy) {
            case "qsalys":
                ObjVo<QsAnalysis> qsalysVo = qsTestBnakService.searchQsAlys(current, 2, keyWord,qsId);
                model.addAttribute("qsalysVo", qsalysVo);
                return "qstestbank/qsalysInfo::qsalysList ";
            case "qsbank":
                ObjVo<QsBank> qsbankVo = qsTestBnakService.searchQsBnak(current, 2, keyWord);
                model.addAttribute("qsBankVo", qsbankVo);
                return "qstestbank/qsbank::qsBankList ";
            case "qsdtinfo":
                ObjVo<QsDetails> qsdtVo = qsTestBnakService.searchQsdtInfo(current, 2, keyWord,qsId);
                model.addAttribute("qsdtVo", qsdtVo);
                return "qstestbank/qsdtInfo::qsdtList ";
            case "utrdinfo":
                ObjVo<UserTestRecord> utrdVo = qsTestBnakService.searchQsUtrd(current, 2, keyWord,qsId);
                model.addAttribute("utrdVo", utrdVo);
                return "qstestbank/utrdInfo::utrdList ";
            default:
                return "index";
        }
    }

    @PostMapping("/search/rcminfo")
    public String searchRcmInfo(Integer current,String ctgy, String keyWord,String qsId, Model model) {
        ObjVo objVo = recommendService.searchrcm(current,8,keyWord,ctgy);

        model.addAttribute(ctgy+"Vo",objVo);
        return "recommend/"+ctgy+"info::"+ctgy+"List";
    }
}
