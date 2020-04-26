package com.zd1024.withyou.controller.admin;

import com.zd1024.withyou.entityVo.AndroidData;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/recmd")
public class ARecmmendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/")
    public String getAllRcmdByCtgy(Integer current, String ctgy, Model model){
        ObjVo datavo = recommendService.getAllRecommendByCtgy(current,10,ctgy);
        model.addAttribute(ctgy+"Vo",datavo);
        return "recommend/"+ctgy+"info";
    }

    @PostMapping("/add")
    public String addRecommendByCtgy(Object recommend, String ctgy,Model model){
        int rs = recommendService.addNewRecommend(recommend,ctgy);
        if (rs == 1){
            model.addAttribute("result","添加成功");
        }
        else {
            model.addAttribute("result", "添加失败");
        }
        return getAllRcmdByCtgy(1,ctgy,model);
    }
}
