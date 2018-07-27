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
        if (map.size() < 2 || rolrName.isEmpty()) {
            return "请按要求写!";
        }
        boolean flag = false;
        if (map.get("module_id") instanceof String) {
            flag = true;
        }
        Role role1 = roleMapper.queryRoleId(rolrName);
        if (role1 != null) {
            msg = "用户名已存在!";
        } else {
            int num1 = roleMapper.insertRole(rolrName);
            if (num1 > 0) {
                Role role = roleMapper.queryRoleId(rolrName);
                int num2 = 0;
                if (flag) {
                    String id = (String) map.get("module_id");
                    num2 = roleMapper.insertRoleAndModule(role.getRole_id(), id);
                } else {
                    List<String> moduleId = (List<String>) map.get("module_id");
                    for (String s : moduleId) {
                        num2 = roleMapper.insertRoleAndModule(role.getRole_id(), s);
                    }
                }
                if (num2 > 0) {
                    msg = "添加成功!";
                } else {
                    msg = "添加失败!";
                }
            }
        }
        return msg;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map roleModi(String role_id) {
        Map<String, Object> map = new HashMap<>();
        Role role = roleMapper.queryRoleName(role_id);
        List<Module> moduleList = roleMapper.queryModule(role);
        List<Module> allModul = roleMapper.findAllModule();
        for (int i = allModul.size() - 1; i > -1; i--) {
            if (allModul.size()<1)continue;
            boolean flag = false;
            for (int e = moduleList.size() - 1; e > -1; e--) {
                if (allModul.get(i).getName().equals(moduleList.get(e).getName())){
                    flag = true;
                }
            }
            if (flag) allModul.remove(i);
        }
        role.setModuleList(moduleList);
        map.put("role", role);
        map.put("allModul", allModul);
        return map;
    }

    //修改
    @Override
    @SuppressWarnings("unchecked")
    public String hardRoleModi(Map map) {
        String roleName = (String) map.get("roleName");
        String role_id = (String) map.get("role_id");
        if (map.size() < 3 || "".equals(roleName) || roleName == null) {
            return "名称不能为空且必须勾选一个权限";
        }
        boolean flag = false;
        if (map.get("moduleName") instanceof String) {
            flag = true;
        }
        Role role1 = roleMapper.queryRoleId(roleName);
        if (role1 == null || role_id.equals(role1.getRole_id())) {
            Role role = new Role();
            role.setRole_id(role_id);
            role.setName(roleName);
            int num = roleMapper.updateRoleName(role);
            List<Module> moduleList = roleMapper.queryModule(role);
            if (flag) {
                String moduleName = (String) map.get("moduleName");
                //把重复分剔除
                for (int i = 0; i < moduleList.size(); i++) {
                    if (moduleName != null && moduleName.equals(moduleList.get(i).getName())) {
                        moduleList.remove(moduleList.get(i));
                        moduleName = null;
                    }
                }
                //剩余删除
                if (moduleList.size() > 0) {
                    for (Module module : moduleList) {
                        int num2 = roleMapper.deleteRoleAndModule(role_id, module.getModule_id());
                    }
                }
                //剩余的添加
                if (moduleName != null) {
                    Module module = roleMapper.queryModuleId(moduleName);
                    roleMapper.insertRoleAndModule(role_id, module.getModule_id());
                }
            } else {
                List<String> moduleNames = (List<String>) map.get("moduleName");
                //把重复分剔除
                for (int i = moduleList.size() - 1; i > -1; i--) {
                    if (moduleList.size()<1)continue;
                    boolean flag1 = false;
                    for (int e = moduleNames.size() - 1; e > -1; e--) {
                        if (moduleNames.get(e).equals(moduleList.get(i).getName())) {
                            flag1 = true;
                            moduleNames.remove(e);
                        }
                    }
                    if (flag1)moduleList.remove(i);
                }
                //剩余删除
                if (moduleList.size() > 0) {
                    for (Module module : moduleList) {
                        int num2 = roleMapper.deleteRoleAndModule(role_id, module.getModule_id());
                    }
                }
                //剩余的添加
                if (moduleNames.size() > 0) {
                    for (String moduleName : moduleNames) {
                        Module module = roleMapper.queryModuleId(moduleName);
                        roleMapper.insertRoleAndModule(role_id, module.getModule_id());
                    }
                }
                return "成功!";
            }

        } else {
            return "名称已存在";
        }

        return null;
    }

}

