package com.zd1024.withyou.controller.admin;

import com.zd1024.withyou.entity.Acount;
import com.zd1024.withyou.entity.User;
import com.zd1024.withyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ALoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

       // System.out.println("》》》》》》》》》"+username+password);
        Acount acount = userService.checkAcount(username,password);
        if (acount!=null&&acount.getRole()==1){
            User user = userService.getUserinfo(acount.getUserId());
            if (user!=null)
            session.setAttribute("user",user);
            return "index";
        }else if (acount!=null&&acount.getRole()!=1){
            attributes.addFlashAttribute("message","抱歉，您不具有该系统的使用权限！");
            return "redirect:/admin";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
