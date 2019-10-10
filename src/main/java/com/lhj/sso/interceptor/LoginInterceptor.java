package com.lhj.sso.interceptor;

import com.lhj.sso.service.LoginService;
import com.lhj.sso.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginInterceptor implements HandlerInterceptor {
    /**
     *  封装的拦截器
     *  判断用户是否处于登录状态
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        /*
        * 需要再次的匹配用户是否处于登录状态
        * 1、从cookie中获取Redis的key值
        * 有两种情况，第一种情况cookie的值(redis的key)不为null(用户已经登录过了，需要跳转到登录项目去检测)，
        * 第二种情况cookie的值为null，说明用户根本没有登录(需要让用户登录)
        * 之前说明java项目中是否可以出现硬编码(魔法值)？ 不能
        * cookie的key值其实是从properties文件中获取到的
        */
        String cookieValue = CookieUtil.getCookieValue(httpServletRequest, loginService.cookieKey);
        //2、判断是否为空
        if (null == cookieValue || "".equals(cookieValue)){
            //没有登录时跳转到登录项目的登录页面中
            //重定向
            //http://127.0.0.1:8082/turnLoginPage/?currentPage\=当前页面的地址
            httpServletResponse.sendRedirect(loginService.baseUrl + loginService.loginUrl + loginService.loginParam +  httpServletRequest.getRequestURL());
        } else {
            //说明已经登录过了
            //跳转到登录项目中进行检测
            String userString = loginService.checkLogin(cookieValue);
            if (!"".equals(userString) && null !=userString){
                //说明已为登录状态
                return true;
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
