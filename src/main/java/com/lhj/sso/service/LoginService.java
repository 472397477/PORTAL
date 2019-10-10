package com.lhj.sso.service;

import com.lhj.sso.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    //获取登录项目的地址
    @Value("${sso_base_url}")
    public String baseUrl;

    //登录项目跳转到登录页面
    @Value("${sso_access_login_url}")
    public String loginUrl;

    //检测用户是否处于登录状态（在登录项目中有一个@RequestMapping("/token")）
    @Value("${sso_access_url}")
    public String tokenUrl;

    //传递当前页面的地址（当用户登录成功之后将跳转到用户访问的那个页面）
    @Value("${sso_login_url_param}")
    public String loginParam;

    //获取cookie的key值
    @Value("${cookie_key}")
    public String cookieKey;

    //跳转到登录项目中的检测页面
    public String checkLogin(String cookieValue){
                              // http://127.0.0.1:8082/token/redis的key(用户)
        return HttpClientUtil.doGet(baseUrl + tokenUrl + cookieValue);
    }


}
