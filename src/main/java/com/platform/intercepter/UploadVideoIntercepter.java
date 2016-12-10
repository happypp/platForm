package com.platform.intercepter;

import com.platform.entities.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/12/7.
 */
public class UploadVideoIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            if (!"教师".equals(user.getType())) {
//                modelAndView.addObject("message", "您没有访问的权利~！");
//                modelAndView.setViewName("message");
                modelAndView.setViewName("redirect:/index");
            }
        }else {
//            modelAndView.addObject("message", "您没有访问的权利~！");
//            modelAndView.setViewName("message");
            modelAndView.setViewName("redirect:/index");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
