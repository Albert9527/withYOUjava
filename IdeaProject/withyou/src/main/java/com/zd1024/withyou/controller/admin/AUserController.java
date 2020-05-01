package com.zd1024.withyou.controller.admin;

import com.zd1024.withyou.entity.User;
import com.zd1024.withyou.entityVo.ObjVo;
import com.zd1024.withyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(Integer current, Model model){
        ObjVo<User> userVo = userService.getUser(current,3);
        model.addAttribute("userVo",userVo);
        System.out.println(">>>>>>>"+userVo.getPages());
        return "userinfo";
    }

    @PostMapping("/pageChange")
    public String search(Integer current, Model model){
        ObjVo<User> userVo = userService.getUser(current,2);
        model.addAttribute("userVo",userVo);
        return "userinfo::userList";
    }

    @GetMapping("/volunteer")
    public String getVolunteer(Integer current, Model model){
        ObjVo<User> userVo = userService.getUser(current,10);
        model.addAttribute("vltuser",userVo);
        return "volunteer";
    }

}
