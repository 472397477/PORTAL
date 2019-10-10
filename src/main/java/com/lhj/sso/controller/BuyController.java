package com.lhj.sso.controller;

import com.lhj.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuyController {
    //只是页面的跳转

    @RequestMapping("/")
    public String turnIndexPage(){
        return "index";
    }

    @RequestMapping("/buyIphone")
    public String BuyIphonePage(){
       return "buy_iphone";
    }
}
