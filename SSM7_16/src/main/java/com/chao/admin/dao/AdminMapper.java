package com.chao.admin.dao;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.bean.AdminPage;
import org.apache.ibatis.annotations.Param;
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
    //得到前端条件查询admin
    List findAdminByRoleAndMoudl(AdminPage adminPage);
    //得到前端条件查询admin数量
    List getCountByCondition(AdminPage adminPage);
    //查询所有角色
    List findAllRole();
    //插入admin
    int addAdmin(AdminBean adminBean);
    //插入admin_role
    int addAdminAndRole(@Param("admin_id")String admin_id,@Param("role_id")String role_id);
    //通过admin_code查询admin
    AdminBean findAdminByCode(String code);
    //查询所有admin
    List findAllAdmin();
    //修改admin
    int modiAdmin(AdminBean adminBean);
    //删除admin_role
    int deleteAdminAndRole(@Param("admin_id")String admin_id,@Param("role_id")String role_id);
    //根据admin_code查询admin
    AdminBean findAdminByAdminId(String admin_code);
}
