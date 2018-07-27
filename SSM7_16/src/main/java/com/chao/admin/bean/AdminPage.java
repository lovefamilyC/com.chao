package com.chao.admin.bean;

import com.chao.abstract_bean.Page;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class AdminPage extends Page {
    private String moduleName, adminName;

    @Override
    public String toString() {
        return "AdminPage{" +
                "moduleName='" + moduleName + '\'' +
                ", adminName='" + adminName + '\'' +super.toString()+
                '}';
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
