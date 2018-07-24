package com.chao.account.service;

import com.chao.account.bean.Account;
import com.chao.account.bean.AccountPage;

import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public interface AccountService {
    AccountPage queryAllAccount(AccountPage accountPage);
    AccountPage queryPageAccount(AccountPage account);
    Account queryById(String account_id);
    boolean setStatusAccount(String account_id);
    boolean deleteAccount(String account_id);
}
