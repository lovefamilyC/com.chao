package com.chao.admin.service;

import com.chao.admin.bean.AdminPage;

import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public interface AdminService {
    Map adminList();
    Map adminCondition(AdminPage adminPage);
}
