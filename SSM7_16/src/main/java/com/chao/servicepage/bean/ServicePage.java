package com.chao.servicepage.bean;

import com.chao.abstract_bean.Page;

import java.io.Serializable;
import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public class ServicePage extends Page implements Serializable{
    private  String os_username,unix_host,idcard_no,status;

    @Override
    public String toString() {
        return "ServicePage{" +
                "os_username='" + os_username + '\'' +
                ", unix_host='" + unix_host + '\'' +
                ", idcard_no='" + idcard_no + '\'' +
                ", status='" + status + '\'' +super.toString()+
                '}';
    }

    public ServicePage() {
    }

    public ServicePage(String os_username, String unix_host, String idcard_no, String status) {
        this.os_username = os_username;
        this.unix_host = unix_host;
        this.idcard_no = idcard_no;
        this.status = status;
    }

    public ServicePage(int allCount, int onePageNum, int currentPage, int allPage, int beginNum, List list, List list2, String os_username, String unix_host, String idcard_no, String status) {
        super(allCount, onePageNum, currentPage, allPage, beginNum, list, list2);
        this.os_username = os_username;
        this.unix_host = unix_host;
        this.idcard_no = idcard_no;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOs_username() {
        return os_username;
    }

    public void setOs_username(String os_username) {
        this.os_username = os_username;
    }

    public String getUnix_host() {
        return unix_host;
    }

    public void setUnix_host(String unix_host) {
        this.unix_host = unix_host;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }
}
