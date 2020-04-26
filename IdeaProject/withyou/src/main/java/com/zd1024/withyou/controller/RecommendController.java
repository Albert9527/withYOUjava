package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/recmd")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/getAllByCtgy")
    public AndroidData getRcmdByCtgy(Integer current,String ctgy){
       ObjVo objVo = recommendService.getAllRecommendByCtgy(current,10,ctgy);

       return DataDealUtil.dealdata(objVo.getDatalist());
    }

}
