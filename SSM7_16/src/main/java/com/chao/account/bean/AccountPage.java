package com.chao.account.bean;

import com.chao.abstract_bean.Page;

import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class AccountPage extends Page {
    private String idcard_no,login_name,real_name,status;
    @Override
    public String toString() {
        return "AccountPage{" +
                "idcard_no='" + idcard_no + '\'' +
                ", login_name='" + login_name + '\'' +
                ", real_name='" + real_name + '\'' +
                ", status='" + status + '\'' + super.toString()+
                '}';
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
