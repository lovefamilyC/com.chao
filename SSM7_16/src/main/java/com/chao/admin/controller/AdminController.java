package com.chao.admin.controller;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import com.chao.admin.service.AdminService;
import com.chao.role.bean.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("adminMain.do")
    public String mian(){
        return "main";
    }

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


    @RequestMapping("/adminAdd.do")
    public String admin3(Model model){
        List<Role> roleList = adminServiceImpl.adminAdd();
        model.addAttribute("roleList",roleList);
        return "/admin/admin_add";
    }

    @RequestMapping(value = "/adminAddAdmin.do",method = RequestMethod.POST)
    @ResponseBody
    public String admin4(AdminBean adminBean){
        List<String> roleidList = adminBean.getRoleidList();
        String msg = adminServiceImpl.addAdminAndRole(adminBean,roleidList);
        return msg;
    }

    @RequestMapping(value = "/adminModi.do")
    public String admin5(String adminCode,Model model){
        Map map = adminServiceImpl.adminModi(adminCode);
        model.addAttribute("map",map);
        return "/admin/admin_modi";
    }

    @RequestMapping(value = "/adminModiOne.do",method = RequestMethod.POST)
    @ResponseBody
    public String admin6(AdminBean adminBean){
        String msg = adminServiceImpl.modiAdminAndRole(adminBean);
        return msg;
    }
}
