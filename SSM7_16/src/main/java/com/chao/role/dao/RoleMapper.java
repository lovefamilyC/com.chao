package com.chao.role.dao;

import com.chao.role.bean.Module;
import com.chao.role.bean.Role;
import com.chao.role.bean.RolePage;
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
public interface RoleMapper {
    //查询所有role
    List queryRole(RolePage rolePage);
    //查询role数量
    int getAllCount();
    //查询role所含权限
    List queryModule(Role role);
    //查询所有module
    List findAllModule();
    //插入role
    int insertRole(String name);
    //插入RoleAndModule
    int insertRoleAndModule(@Param("role_id")String role_id,@Param("module_id")String module_id);
    //通过名字查role
    Role queryRoleId(String name);
    //通过名字查ModuleId
    Module queryModuleId(String name);
    //通过id查role
    Role queryRoleName(String role_id);
    //修改rolen的name
    int updateRoleName(Role role);
    //通过role_id和module_id删除RoleAndModule
    int deleteRoleAndModule(@Param("role_id")String role_id,@Param("module_id")String module_id);
}
