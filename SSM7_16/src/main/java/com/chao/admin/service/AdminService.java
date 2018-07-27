package com.chao.admin.service;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import com.chao.role.bean.Role;

import java.util.List;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public interface AdminService {
    Map adminList();
    Map adminCondition(AdminPage adminPage);
    List adminAdd();
    String addAdminAndRole(AdminBean adminBean, List<String> role_idList);
    Map adminModi(String adminCode);
    String modiAdminAndRole(AdminBean adminBean);
}
