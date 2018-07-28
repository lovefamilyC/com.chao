package com.chao.login.bean;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class LoginBean {
    private String adminCode,password,verCode;

    @Override
    public String toString() {
        return "LoginBean{" +
                "adminCode='" + adminCode + '\'' +
                ", password='" + password + '\'' +
                ", verCode='" + verCode + '\'' +
                '}';
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }
}
