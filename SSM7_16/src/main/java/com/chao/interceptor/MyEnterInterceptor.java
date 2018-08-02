package com.chao.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class MyEnterInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String loginAdminCode = (String) session.getAttribute("loginAdminCode");
        if (loginAdminCode != null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/login.html");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
