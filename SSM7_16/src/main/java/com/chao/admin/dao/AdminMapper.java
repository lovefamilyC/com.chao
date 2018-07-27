package com.chao.admin.dao;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Repository
public interface AdminMapper {
    //根据page条件查询admin
    List findAdminByPage(AdminPage adminPage);
    //查询每个admin拥有的role
    List findAdminRole(AdminBean adminBean);
    //查询所有权限
    List findAllModule();
    //得到总数量
    int getAdminCount();
    //条件查询
    List findAdminByRoleAndMoudl(AdminPage adminPage);
    //得到条件数量
    List getCountByCondition(AdminPage adminPage);
}
