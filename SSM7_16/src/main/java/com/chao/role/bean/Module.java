package com.chao.role.bean;

import java.io.Serializable;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class Module implements Serializable{
    private String module_id,name;

    @Override
    public String toString() {
        return "Module{" +
                "module_id='" + module_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
