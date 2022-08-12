package com.atguigu.springbootwebsimulateing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {

    //登录
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,String> map,
                        HttpSession session){
        if ("admin".equals(username) && "123456".equals(password)){
            //第一次：直接用thymeleaf解析，可以得到渲染之后dashboard页面，但是面临一个问题，每次刷新要重新发送请求。
            session.setAttribute("username", username);
            //改进的方式就是用重定向:让浏览器实际的到达了url地址。（这里是转发和重定向的区别）
            return "dashboard";
        }else{
            map.put("msg", "账号或密码有误!");
            return "index";

        }

    }

}
