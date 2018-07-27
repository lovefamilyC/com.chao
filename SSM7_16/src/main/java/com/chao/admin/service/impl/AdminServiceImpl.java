package com.chao.admin.service.impl;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import com.chao.admin.dao.AdminMapper;
import com.chao.admin.service.AdminService;
import com.chao.role.bean.Module;
import com.chao.role.bean.Role;
import org.springframework.stereotype.Service;

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
@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Map adminList() {
        AdminPage adminPage = new AdminPage();
        int count = adminMapper.getAdminCount();
        adminPage.setAllCount(count);
        adminPage.setCurrentPage(1);
        //查询admin
        List<AdminBean> beanList = adminMapper.findAdminByPage(adminPage);
        return dealWith(adminPage,beanList);
    }

    @Override
    public Map adminCondition(AdminPage adminPage) {
        //判断页数
        if (adminPage.getCurrentPage() < 1) {
            adminPage.setCurrentPage(1);
        }
        List<AdminBean> beanList = adminMapper.getCountByCondition(adminPage);
        adminPage.setAllCount(beanList.size());
        if (adminPage.getCurrentPage() > adminPage.getAllPage()) {
            adminPage.setCurrentPage(adminPage.getAllPage());
        }
        List<AdminBean> adminBeanList = adminMapper.findAdminByRoleAndMoudl(adminPage);

        return dealWith(adminPage,adminBeanList);
    }




    //处理adminPage
    public Map dealWith(AdminPage adminPage,List<AdminBean> beanList){
        //设置页数
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < adminPage.getAllPage(); i++) {
            stringList.add(String.valueOf(i + 1));
        }
        adminPage.setStringList(stringList);
        //查询拥有的角色
        for (AdminBean adminBean : beanList) {
            List<Role> roleList = adminMapper.findAdminRole(adminBean);
            adminBean.setRoleList(roleList);
        }
        for (AdminBean adminBean : beanList) {
            System.out.println(adminBean);
        }

        //得到adminPage
        adminPage.setList(beanList);
        //得到全部权限
        List<Module> moduleList = adminMapper.findAllModule();
        //设置map
        Map<String,Object> map = new HashMap<>();
        map.put("adminPage",adminPage);
        map.put("moduleList",moduleList);
        return map;
    }
}
