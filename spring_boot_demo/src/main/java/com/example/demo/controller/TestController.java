package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("-----");
        return "hello aaa";
    }

    @RequestMapping("/user")
    @ResponseBody
    public List<User> userList(){
        System.out.println("user...");
        User u1 = new User();
        u1.setId(1);
        u1.setUsername("zhangsan");
        u1.setPassword("11111");

        User u2 = new User();
        u1.setId(2);
        u1.setUsername("lisi");
        u1.setPassword("22222");

        User u3 = new User();
        u1.setId(3);
        u1.setUsername("wangwu");
        u1.setPassword("33333");

        List<User> al = new ArrayList<User>();
        al.add(u1);
        al.add(u2);
        al.add(u3);
        JSONArray jsonArray = null;
        System.out.println();
        
        String a = "select distinct(yy.dept_sn),zd.name " +
                "from yy_order_detl detl, yy_parts_order yy, zd_unit_code zd " +
                "where yy.yyh_sn = detl.yyh_sn and yy.dept_sn = zd.unit_sn " +
                "and  detl.order_status in ('1','2') and detl.close_flag='0' ";
        return al;
    }
}
