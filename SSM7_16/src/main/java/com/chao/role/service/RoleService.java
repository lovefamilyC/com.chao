package com.chao.role.service;

import com.chao.role.bean.Role;
import com.chao.role.bean.RolePage;

import java.util.List;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public interface RoleService {
    RolePage getRole(String currentPage);
    List findAllModule();
    String roleAdd(Map map);
    Map roleModi(String role_id);
    String hardRoleModi(Map map);
}
