package com.chao.account.service.impl;

import com.chao.account.bean.Account;
import com.chao.account.bean.AccountPage;
import com.chao.account.dao.AccountMapper;
import com.chao.account.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Service("accountImpl")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper mapper;

    //带条件查询
    @Override
    public AccountPage queryAllAccount(AccountPage accountPage) {
        if ("0".equals(accountPage.getStatus())){
            accountPage.setStatus("");
        }
        //查询所有数量
        int allCount = mapper.queryAllCount(accountPage);
        //设置总数量
        accountPage.setAllCount(allCount);
        //设置一页的数量
        accountPage.setOnePageNum(5);
        //设置当前页
        accountPage.setCurrentPage(1);
        //查询并设置满足条件的account
        List<Account> accountList = mapper.queryOneAccount(accountPage);
        accountPage.setList(accountList);
        //设置页数
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < accountPage.getAllPage(); i++) {
            stringList.add(String.valueOf(1+i));
        }
        accountPage.setStringList(stringList);
        return accountPage;
    }

    //点击页数时使用
    @Override
    public AccountPage queryPageAccount(AccountPage accountPage) {
        if ("0".equals(accountPage.getStatus())){
            accountPage.setStatus("");
        }
        if (accountPage.getCurrentPage()<1){
            accountPage.setCurrentPage(1);
        }
        //查询所有数量
        int allCount = mapper.queryAllCount(accountPage);
        //设置说要数量
        accountPage.setAllCount(allCount);
        //设置一页的数量
        accountPage.setOnePageNum(5);
        if (accountPage.getCurrentPage()>accountPage.getAllPage()){
            accountPage.setCurrentPage(accountPage.getAllPage());
        }
        //查询并设置满足条件的account
        List<Account> accountList = mapper.queryOneAccount(accountPage);
        accountPage.setList(accountList);
        //设置页数
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < accountPage.getAllPage(); i++) {
            stringList.add(String.valueOf(1+i));
        }
        accountPage.setStringList(stringList);
        return accountPage;

    }

    @Override
    public Account queryById(String account_id) {
        return mapper.queryById(account_id);
    }

    @Override
    public boolean setStatusAccount(String account_id) {
        Account account = mapper.queryById(account_id);
        Account accountParam = new Account();
        if ("3".equals(account.getStatus())){
            return false;
        }
        if ("2".equals(account.getStatus())){
            accountParam.setStatus("1");

        }
        if ("1".equals(account.getStatus())){
            accountParam.setStatus("2");
            Date date = new Date();
            SimpleDateFormat deformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String pauseTime = deformat.format(date);
            accountParam.setPause_date(pauseTime);
        }
        accountParam.setAccount_id(account_id);
        int count = mapper.setStatusAccount(accountParam);
        return count>0;
    }

    @Override
    public boolean deleteAccount(String account_id) {
        Account accountParam = new Account();
        Date date = new Date();
        SimpleDateFormat deformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String closeTime = deformat.format(date);
        accountParam.setClose_date(closeTime);
        accountParam.setStatus("3");
        accountParam.setAccount_id(account_id);
        int count = mapper.setStatusAccount(accountParam);
        return count>0;
    }
}
