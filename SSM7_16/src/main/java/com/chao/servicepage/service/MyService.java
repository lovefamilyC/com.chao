package com.chao.servicepage.service;

import com.chao.account.bean.Account;
import com.chao.servicepage.bean.ServiceBean;
import com.chao.servicepage.bean.ServicePage;

import java.util.List;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public interface MyService {
    ServicePage queryService(ServicePage servicePage);

    Map getMap();
    Account getAccount(String idcard_no);
    String insertService(ServiceBean serviceBean);
    boolean updateService(String id,String status);
}
