package com.zd1024.withyou.controller;

import com.zd1024.withyou.Util.DataDealUtil;
import com.zd1024.withyou.entity.Recommend.Book;
import com.zd1024.withyou.entity.Recommend.CurePlan;
import com.zd1024.withyou.entity.Recommend.Music;
import com.zd1024.withyou.entity.Recommend.Videos;
import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.RecommendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/recmd")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/getAllByCtgy")
    public AndroidData getRcmdByCtgy(Integer current, @Param("ctgy") String ctgy){
       ObjVo objVo = recommendService.getAllRecommendByCtgy(current,10,ctgy);

       return DataDealUtil.dealdata(objVo.getDatalist());
    }

    @PostMapping("/addBook")
    public AndroidData addRecommendByCtgy(Book book){
        int rs = recommendService.addNewRecommend(book,"book");
          return DataDealUtil.dealdata(rs,"推荐添加");
    }

    @PostMapping("/addMusic")
    public AndroidData addRecommendByCtgy(Music music){
        int rs = recommendService.addNewRecommend(music,"music");
        return DataDealUtil.dealdata(rs,"推荐添加");
    }

    @PostMapping("/addVideos")
    public AndroidData addRecommendByCtgy(Videos videos){
        int rs = recommendService.addNewRecommend(videos,"videos");
        return DataDealUtil.dealdata(rs,"推荐添加");
    }

    @PostMapping("/addCureplan")
    public AndroidData addRecommendByCtgy(CurePlan curePlan){
        int rs = recommendService.addNewRecommend(curePlan,"cureplan");
        return DataDealUtil.dealdata(rs,"推荐添加");
    }

}
