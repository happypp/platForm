package com.platform.intercepter;

import com.platform.entities.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2016/1/3.
 */
public class AdminstratorIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user  = (User) request.getSession().getAttribute("admin");
        if(user != null){
            if (!"管理员".equals(user.getType())) {
//                modelAndView.addObject("message", "您没有访问的权利~！");
//                modelAndView.setViewName("message");
                modelAndView.setViewName("redirect:/index");
            }
        }else{
//            modelAndView.addObject("message", "您没有访问的权利~！");
//            modelAndView.setViewName("message");
            modelAndView.setViewName("redirect:/index");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
