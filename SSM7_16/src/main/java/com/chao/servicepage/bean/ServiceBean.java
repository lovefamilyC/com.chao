package com.chao.servicepage.bean;

/* I AM A PURE SHEEP
 *
 * WHAT YOU WANT TO DO
 *
 * FUCK AS YOU WISH
 */

import com.chao.account.bean.Account;
import com.chao.fee.bean.Cost;

import javax.annotation.Resource;

public class ServiceBean {

    private String id,account_id,unix_host,
            os_username,login_passwd,status,
            create_date,pause_date,close_date,cost_id;

    private Cost cost;
    private Account account;
    private String confirm_passwd,name;


    @Override
    public String toString() {
        return "ServiceBean{" +
                "id='" + id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", unix_host='" + unix_host + '\'' +
                ", os_username='" + os_username + '\'' +
                ", login_passwd='" + login_passwd + '\'' +
                ", status='" + status + '\'' +
                ", create_date='" + create_date + '\'' +
                ", pause_date='" + pause_date + '\'' +
                ", close_date='" + close_date + '\'' +
                ", cost_id='" + cost_id + '\'' +
                ", cost=" + cost +
                ", account=" + account +
                ", confirm_passwd='" + confirm_passwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirm_passwd() {
        return confirm_passwd;
    }

    public void setConfirm_passwd(String confirm_passwd) {
        this.confirm_passwd = confirm_passwd;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getUnix_host() {
        return unix_host;
    }

    public void setUnix_host(String unix_host) {
        this.unix_host = unix_host;
    }

    public String getOs_username() {
        return os_username;
    }

    public void setOs_username(String os_username) {
        this.os_username = os_username;
    }

    public String getLogin_passwd() {
        return login_passwd;
    }

    public void setLogin_passwd(String login_passwd) {
        this.login_passwd = login_passwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPause_date() {
        return pause_date;
    }

    public void setPause_date(String pause_date) {
        this.pause_date = pause_date;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getCost_id() {
        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
    }
}
