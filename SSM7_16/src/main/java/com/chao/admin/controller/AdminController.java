package com.chao.admin.controller;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import com.chao.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminServiceImpl;

    @RequestMapping("/adminList.do")
    public String admin1(Model model){
        Map map = adminServiceImpl.adminList();
        model.addAttribute("map",map);
        return "/admin/admin_list";
    }

    @RequestMapping(value = "/adminListPage.do",method = RequestMethod.POST)
    public String admin2(AdminPage adminPage,Model model){
        Map map = adminServiceImpl.adminCondition(adminPage);
        model.addAttribute("map",map);
        return "/admin/admin_list";
    }


}
