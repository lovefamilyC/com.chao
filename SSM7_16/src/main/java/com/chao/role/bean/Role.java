package com.chao.role.bean;

import java.io.Serializable;
import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class Role implements Serializable{
    private String role_id,name;

    private List<Module> moduleList;

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", name='" + name + '\'' +
                ", moduleList=" + moduleList +
                '}';
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
