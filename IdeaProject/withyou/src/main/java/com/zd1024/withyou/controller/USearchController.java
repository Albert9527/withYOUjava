package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.*;
import com.zd1024.withyou.service.impl.DyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/search")
public class USearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private QsTestBnakService qsTestBnakService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private DyService dyService;

    @Autowired
    private FriendService friendService;

    @GetMapping("/rcm")
    public AndroidData searchrcm(Integer current, String keyWord, String ctgy) {

        ObjVo objVo = recommendService.searchrcm(current, 10, keyWord, ctgy);

        return DataDealUtil.dealdata(objVo.getDatalist());
    }

    @GetMapping("/act")
    public AndroidData searchActivity(Integer current, String keyWord, String ctgy, String userid) {
        ObjVo objVo = activityService.searchActive(current, 10, keyWord, ctgy, userid);

        return DataDealUtil.dealdata(objVo.getDatalist());
    }

    @GetMapping("/dynamic")
    public AndroidData searchDynamic(Integer current, String keyWord, String userid) {
        ObjVo objVo = dyService.serach(current, 10, keyWord, userid);
        List<Dynamic> dynamics = objVo.getDatalist();
        for (int i = 0; i < dynamics.size(); i++) {
            dynamics.get(i).setNickname(
                    userService.getnickname(dynamics.get(i).getUserId()));
            dynamics.get(i).setUseravatar(
                    userService.getUserAvatar(dynamics.get(i).getUserId()));
        }
        return DataDealUtil.dealdata(dynamics);
    }

    @GetMapping("/friend")
    public AndroidData searchFriend(Integer current, String keyWord, String userid, String ctgy) {
        ObjVo objVo = friendService.serachFriend(current, 10, userid, keyWord, ctgy);

        return DataDealUtil.dealdata(objVo.getDatalist());
    }

    @GetMapping("/qsbank")
    public AndroidData searchQsbank(Integer current, String keyWord) {
        ObjVo objVo = qsTestBnakService.searchQsBnak(current, 10, keyWord);

        return DataDealUtil.dealdata(objVo.getDatalist());
    }

}
