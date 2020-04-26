package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.Util.PictrueDealUtil;
import com.zd1024.withyou.entity.Dynamic;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.service.DyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/dynamic")
public class DyController {
    private AndroidData<Dynamic> data = null;

    @Autowired
    private DyService dyService;

    @GetMapping("/MyFollowDy")
    public AndroidData<Dynamic> getMyFollowDy(String userid){
        List<Dynamic> dynamics = dyService.getMyFollowDynamic(userid);
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
    public AndroidData addDynamic(@Param("newDynamic") Dynamic dynamic,MultipartFile pic){
        dynamic.setDyPictrue(PictrueDealUtil.savePictrue(pic));
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
