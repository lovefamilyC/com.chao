package com.chao.login.controller;

import com.chao.login.bean.LoginBean;
import com.chao.login.service.LoginService;
import com.chao.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginServiceImpl;

    @RequestMapping(value = "/verifyCode.do",method = RequestMethod.GET)
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        request.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片
        int w = 65, h = 33;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public String login(LoginBean loginBean,HttpServletRequest request){
        System.out.println(loginBean);

        String verCode = (String) request.getSession().getAttribute("verCode");
        if (!verCode.equalsIgnoreCase(loginBean.getVerCode())) return "verCode";
        boolean flag = loginServiceImpl.login(loginBean);
        if (flag){
            HttpSession session = request.getSession();
            session.setAttribute("admin",loginBean.getAdminCode());
            return "ture";
        }
        return "admin";
    }


}
