package com.chao.admin.service.impl;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import com.chao.admin.dao.AdminMapper;
import com.chao.admin.service.AdminService;
import com.chao.role.bean.Module;
import com.chao.role.bean.Role;
import com.chao.utils.AppMD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Service
public class AdminServiceImpl implements AdminService {
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
        return dealWith(adminPage, beanList);
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

        return dealWith(adminPage, adminBeanList);
    }

    //查询所有role
    @Override
    public List adminAdd() {
        return adminMapper.findAllRole();
    }

    //增加
    @Override
    public String addAdminAndRole(AdminBean adminBean, List<String> roleidList) {

        if (judge(adminBean, roleidList)) {
            return "请将必填项填写完成";
        }
        if (judge1(adminBean)) {
            return "两次密码不一致";
        }
        if (judge2(adminBean)) {
            return "管理员账号已存在";
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
        String enrolldate = dateFormat.format(date);
        adminBean.setEnrolldate(enrolldate);
        String password = AppMD5Util.MD5(adminBean.getPassword()+"MD5");
        adminBean.setPassword(password);
        int num = adminMapper.addAdmin(adminBean);
        adminBean = adminMapper.findAdminByCode(adminBean.getAdmin_code());
        for (String roleid : roleidList) {
            adminMapper.addAdminAndRole(adminBean.getAdmin_id(), roleid);
        }
        String msg = "";
        if (num > 0) {
            msg = "成功!";
        } else {
            msg = "失败!";
        }
        return msg;
    }

    @Override
    public Map adminModi(String adminCode) {
        AdminBean adminBean = adminMapper.findAdminByCode(adminCode);
        List<Role> roleList = adminMapper.findAdminRole(adminBean);
        List<Role> allroleList = adminMapper.findAllRole();
        for (int i = allroleList.size() - 1; i > -1; i--) {
            if (allroleList.size() < 1) break;
            for (Role aRoleList : roleList) {
                if (aRoleList.getName().equals(allroleList.get(i).getName())) {
                    allroleList.remove(i);
                    break;
                }
            }

        }
        adminBean.setRoleList(roleList);
        Map<String, Object> map = new HashMap<>();
        map.put("adminBean", adminBean);
        map.put("allRoleList", allroleList);
        return map;
    }

    @Override
    public String modiAdminAndRole(AdminBean adminBean) {
        adminBean.setRePassword(adminBean.getPassword());
        List<String> roleidList = adminBean.getRoleidList();
        if (judge(adminBean, roleidList)) {
            return "请将必填项填写完成";
        }
        List<Role> roleList = adminMapper.findAdminRole(adminBean);

        for (int i = roleidList.size()-1; i > -1; i--) {
            if (roleidList.size()<1)break;
            for (int e = roleList.size()-1; e >-1 ; e--) {
                if (roleidList.get(i).equals(roleList.get(e).getRole_id())){
                    roleidList.remove(i);
                    roleList.remove(e);
                    break;
                }
            }
        }
        boolean flag = true;
        for (String s : roleidList) {
           int num = adminMapper.addAdminAndRole(adminBean.getAdmin_id(),s);
            if (num<1){
                flag = false;
            }
        }
        for (Role role : roleList) {
            int num = adminMapper.deleteAdminAndRole(adminBean.getAdmin_id(), role.getRole_id());
            if (num<1){
                flag = false;
            }
        }
       String msg ="";
        if (flag){
            msg = "成功!";
        }else {
            msg = "失败!";
        }

        return msg;
    }

//-----------------------------------方法区-----------------------------------------//

    //处理adminPage
    private Map dealWith(AdminPage adminPage, List<AdminBean> beanList) {
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

        //得到adminPage
        adminPage.setList(beanList);
        //得到全部权限
        List<Module> moduleList = adminMapper.findAllModule();
        //设置map
        Map<String, Object> map = new HashMap<>();
        map.put("adminPage", adminPage);
        map.put("moduleList", moduleList);
        return map;
    }

    //判断对象是否含有空值 集合是否不为空
    private boolean judge(AdminBean adminBean, List<String> roleidList) {
        if ("".equals(adminBean.getAdmin_id()) || adminBean.getAdmin_id() == null) adminBean.setAdmin_id("789");
        if ("".equals(adminBean.getEnrolldate()) || adminBean.getEnrolldate() == null) adminBean.setEnrolldate("789");

        List<Role> roleList = adminMapper.findAllRole();
        adminBean.setRoleList(roleList);
        Class clazz = adminBean.getClass();
        boolean flag = false;
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object o = field.get(adminBean);
                if ("".equals(o) || o == null) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (roleidList == null || roleidList.size() < 1) {
            flag = true;
        }
        return flag;
    }

    //判断两次密码
    private boolean judge1(AdminBean adminBean) {
        boolean flag = false;
        if (!adminBean.getPassword().equals(adminBean.getRePassword())) {
            flag = true;
        }
        return flag;
    }

    //判断管理员账号是否存在
    private boolean judge2(AdminBean adminBean) {
        boolean flag = false;
//        List<AdminBean> adminBeanList = adminMapper.findAllAdmin();
//        for (AdminBean bean : adminBeanList) {
//            if (bean.getAdmin_code().equals(adminBean.getAdmin_code())){
//                flag = true;
//                break;
//            }
//        }
        AdminBean bean = adminMapper.findAdminByCode(adminBean.getAdmin_code());
        if (bean != null) flag = true;
        return flag;
    }

    //判断管理员账号是否更改
    private boolean judge3(AdminBean adminBean) {
        boolean flag = true;
        AdminBean bean = adminMapper.findAdminByCode(adminBean.getAdmin_code());
        if (bean ==null || bean.getAdmin_id().equals(adminBean.getAdmin_id())) flag = false;
        return flag;
    }
}
