package com.zd1024.withyou.controller.admin;

import com.zd1024.withyou.entity.Activity;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/activity")
public class AActivityController {

    private ObjVo<Activity> actVo;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/")
    private String getActivity(Integer current, Model model, int state) {
        actVo = new ObjVo<>();
        actVo = activityService.getActivityByState(current, 10, state);
        model.addAttribute("actVo", actVo);
        return "activityinfo";
        /*  return actVo;*/
    }

    @GetMapping("/pageChange")
    private String getPageActivity(Integer current, Model model, int state) {
        actVo = activityService.getActivityByState(current, 10, state);
        model.addAttribute("actVo", actVo);
        return "activityinfo::actList";
    }

    @GetMapping("/examine")
    public String examineActivity(int current, String actId, int state, Model model) {
        int rs = activityService.examineActivity(actId, state);
        actVo = activityService.getActivityByState(current, 10, 0);
        if (rs == 1) {
            model.addAttribute("msg", "审核成功");
            model.addAttribute("actVo", actVo);
        } else {
            model.addAttribute("msg", "审核失败");
            model.addAttribute("actVo", actVo);
        }

        return "activityinfo";
    }


}
