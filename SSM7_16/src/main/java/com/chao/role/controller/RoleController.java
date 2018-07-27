package com.chao.role.controller;

import com.chao.role.bean.Module;
import com.chao.role.bean.Role;
import com.chao.role.bean.RolePage;
import com.chao.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource(name = "roleImpl")
    private RoleService roleServiceImpl;

    //展示列表
    @RequestMapping("/roleList.do")
    public String role1(Model model) {
        String currentPage = "1";
        RolePage rolePage = roleServiceImpl.getRole(currentPage);
        model.addAttribute("rolePage", rolePage);
        return "role/role_list";
    }

    //点击页数
    @RequestMapping("/rolePage.do")
    public String role2(@RequestParam String currentPage, Model model) {
        RolePage rolePage = roleServiceImpl.getRole(currentPage);
        model.addAttribute("rolePage", rolePage);
        return "role/role_list";
    }

    //添加
    @RequestMapping("/roleAddIn.do")
    public String role3(Model model) {
        List<Module> moduleList = roleServiceImpl.findAllModule();
        model.addAttribute("moduleList", moduleList);
        return "role/role_add";
    }

    @RequestMapping(value = "/roleAdd.do", method = RequestMethod.POST)
    @ResponseBody
    public String role4(@RequestBody Map<String, Object> map) {
        String msg = roleServiceImpl.roleAdd(map);
        return msg;
    }

    //修改
    @RequestMapping("/roleModiIn.do")
    public String role5(@RequestParam("role_id") String role_id, Model model) {
        Map map = roleServiceImpl.roleModi(role_id);
        model.addAttribute("map", map);
        return "role/role_modi";
    }

    @RequestMapping("/roleModi.do")
    @ResponseBody
    public String role5(@RequestBody Map<String, Object> map) {
        return roleServiceImpl.hardRoleModi(map);
    }

}
