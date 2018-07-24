package com.chao.account.dao;

import com.chao.account.bean.Account;
import com.chao.account.bean.AccountPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Repository
public interface AccountMapper {
    List queryAllAccount();
    List queryOneAccount(AccountPage account);
    int queryAllCount(AccountPage accountPage);

    Account queryById(String account_id);
    int setStatusAccount(Account account);

}
