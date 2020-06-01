package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.PictrueDealUtil;
import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.service.DyService;
import com.zd1024.withyou.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/dynamic")
public class DyController {
    private AndroidData<Dynamic> data = null;

    @Autowired
    private DyService dyService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public AndroidData<Dynamic> getMyFollowDy(String userid){
        List<Dynamic> dynamics = dyService.getMyFollowDynamic(userid);
        for (int i = 0;i<dynamics.size();i++){
            dynamics.get(i).setNickname(
                    userService.getnickname(dynamics.get(i).getUserId()));
            dynamics.get(i).setUseravatar(
                    userService.getUserAvatar(dynamics.get(i).getUserId()));
        }
        data = DataDealUtil.dealdata(dynamics);
        return data;
    }

    @GetMapping("/MyDynamic")
    public AndroidData<Dynamic> getMyDynamic(String userid){
        List<Dynamic> dynamics = dyService.getMyDynamic(userid);
        data = DataDealUtil.dealdata(dynamics);
        return data;
    }

    @PostMapping("/Add")
    public AndroidData addDynamic(String userid,String content, MultipartFile pic){
        Dynamic dynamic = new Dynamic();
        dynamic.setUserId(userid);
        dynamic.setDyContent(content);
        dynamic.setDyPictrue(PictrueDealUtil.savePictrue(pic));
        int rs = dyService.addDynamic(dynamic);
        data =  DataDealUtil.dealdata(rs,"动态发布");
        return data;
    }

    @PostMapping("/AddNoPic")
    public AndroidData addDynamicNoPic(String userid,String content){
        Dynamic dynamic = new Dynamic();
        dynamic.setUserId(userid);
        dynamic.setDyContent(content);
        int rs = dyService.addDynamic(dynamic);
        data =  DataDealUtil.dealdata(rs,"动态发布");
        return data;
    }

    @GetMapping("/Delete")
    public AndroidData deleteDynamic(String dynamicId){
        int rs = dyService.deleteDyByID(dynamicId);
        data = DataDealUtil.dealdata(rs,"动态删除");
        return data;
    }

}
