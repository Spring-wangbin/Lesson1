package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * “@Controller”表示 IndexController 的实例是一个控制器
 *
 * @Controller 相当于@Controller(@Controller) 或@Controller(value="@Controller")
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("login")
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response){

        session.setAttribute("aaa","sessionaaa");
        request.setAttribute("bbb","requestbbb");
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        /*在视图中可以使用EL表达式${success}取出model中的值*/
        model.addAttribute("success", "注册成功");
        return "register";
    }
}
