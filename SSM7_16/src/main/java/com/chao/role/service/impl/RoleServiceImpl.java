package com.chao.role.service.impl;

import com.chao.role.bean.Module;
import com.chao.role.bean.Role;
import com.chao.role.bean.RolePage;
import com.chao.role.dao.RoleMapper;
import com.chao.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Service("roleImpl")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public RolePage getRole(String currentPage) {
        int currentPage1 = Integer.parseInt(currentPage);
        RolePage rolePage = new RolePage();
        //判断页数
        if (currentPage1 < 1) {
            currentPage1 = 1;
        }
        //总数量
        int count = roleMapper.getAllCount();
        //设置相关属性
        rolePage.setAllCount(count);
        if (currentPage1 > rolePage.getAllPage()) {
            currentPage1 = rolePage.getAllPage();
        }
        rolePage.setCurrentPage(currentPage1);
        //设置页数
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < rolePage.getAllPage(); i++) {
            stringList.add(String.valueOf(i + 1));
        }
        rolePage.setStringList(stringList);
        //查询role
        List<Role> roleList = roleMapper.queryRole(rolePage);
        //查询权限
        for (Role role : roleList) {
            List<Module> moduleList = roleMapper.queryModule(role);
            role.setModuleList(moduleList);
        }
        //设置集合
        rolePage.setList(roleList);

        return rolePage;
    }

    @Override
    public List findAllModule() {
        return roleMapper.findAllModule();
    }

    @Override
    public String roleAdd(Map map) {
        String msg = "";
        String rolrName = (String) map.get("role_name");
        if (map.get("module_id") instanceof String) {
            String id = (String) map.get("module_id");
            if (map.size() < 2 || rolrName.isEmpty() || id == null) {
                msg = "请按要求写!";
            } else {
                Role role1 = roleMapper.queryRoleId(rolrName);
                if (role1 != null) {
                    msg = "用户名已存在!";
                    System.out.println("b" + msg);
                } else {
                    int num1 = roleMapper.insertRole(rolrName);
                    if (num1 > 0) {
                        msg = "添加成功!";
                        Role role = roleMapper.queryRoleId(rolrName);
                        int num2 = roleMapper.insertRoleAndModule(role.getRole_id(), id);
                        if (num2 < 1) {
                            msg = "添加失败!";
                        }

                    }
                }
            }

        } else {
            List<String> moduleId = (List<String>) map.get("module_id");
            if (map.size() < 2 || rolrName.isEmpty() || moduleId.size() > 0) {
                msg = "请按要求写!";
            } else {
                Role role1 = roleMapper.queryRoleId(rolrName);
                if (role1 != null) {
                    msg = "用户名已存在!";
                    System.out.println("b" + msg);
                } else {
                    int num1 = roleMapper.insertRole(rolrName);
                    if (num1 > 0) {
                        msg = "添加成功!";
                        Role role = roleMapper.queryRoleId(rolrName);
                        for (String s : moduleId) {
                            int num2 = roleMapper.insertRoleAndModule(role.getRole_id(), s);
                            if (num2 < 1) {
                                msg = "添加失败!";
                            }
                        }
                    }
                }
            }
        }

        return msg;
    }

    @Override
    public Map roleModi(String role_id) {
        Map<String, Object> map = new HashMap<>();
        Role role = roleMapper.queryRoleName(role_id);
        List<Module> moduleList = roleMapper.queryModule(role);
        List<Module> allModul = roleMapper.findAllModule();
        for (int i = 0; i < allModul.size(); i++) {
            for (Module module : moduleList) {
                if (module.getName().equals(allModul.get(i).getName())) {
                    allModul.remove(i);
                }
            }
        }
        role.setModuleList(moduleList);
        map.put("role", role);
        map.put("allModul", allModul);
        return map;
    }

    //修改
    @Override
    public String hardRoleModi(Map map) {
        String roleName = (String) map.get("roleName");
        String role_id = (String) map.get("role_id");
        if ("".equals(roleName)||roleName==null){
            return "名称不能为空";
        }
        if (map.get("moduleName")instanceof String){
            String moduleName = (String) map.get("moduleName");
            if (moduleName ==null){
                return "必须勾选一个权限";
            }
            Role role = roleMapper.queryRoleId(roleName);
            if (role ==null || role_id.equals(role.getRole_id())){
                role.setRole_id(role_id);
                role.setName(roleName);
                int num = roleMapper.updateRoleName(role);
                List<Module> moduleList = roleMapper.queryModule(role);
                for (int i = 0; i < moduleList.size(); i++) {
                    if (moduleName.equals(moduleList.get(i).getName())){
                        continue;
                    }
                        int num2 = roleMapper.deleteRoleAndModule(role_id,moduleList.get(i).getModule_id());
                }
                //没写完!!!!!!

            }else {
                return "名称已存在";
            }


        }

        return null;
    }
}
