package com.chao.filter;

import com.chao.admin.bean.AdminBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class AllFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String loginAdminCode = (String) session.getAttribute("loginAdminCode");
        if (loginAdminCode!=null){
            chain.doFilter(request, response);
        }else {
            response.sendRedirect(request.getContextPath()+"/login.html");

        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
