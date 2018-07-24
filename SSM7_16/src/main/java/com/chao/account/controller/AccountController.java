package com.chao.account.controller;

import com.chao.account.bean.Account;
import com.chao.account.bean.AccountPage;
import com.chao.account.service.impl.AccountServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource(name = "accountImpl")
    private AccountServiceImpl accountService;


    //查询列表
    @RequestMapping("/accountList.do")
    public String account1(Model model) {
        AccountPage accountPage = new AccountPage();
        AccountPage accountPage1 = accountService.queryAllAccount(accountPage);
        model.addAttribute("accountPage", accountPage1);
        return "account/account_list";
    }

    //列表分类查询 条件查询
    @RequestMapping("/accountListOne.do")
    public String account2(AccountPage account, Model model) {
        AccountPage accountPage = accountService.queryAllAccount(account);
        model.addAttribute("accountPage", accountPage);
        return "account/account_list";
    }

    //列表点击页数查询
    @RequestMapping("/accountPage.do")
    public String account22(@RequestParam("currentPage")int currentPage ,AccountPage account, Model model) {
        account.setCurrentPage(currentPage);
        AccountPage accountPage = accountService.queryPageAccount(account);
        model.addAttribute("accountPage", accountPage);
        return "account/account_list";
    }

    //账户详情
    @RequestMapping("/accountDetail.do")
    public String account3(@Param("account_id") String account_id, Model model) {
        Account account = accountService.queryById(account_id);
        Account reAccount = accountService.queryById(account.getRecommender_id());
        model.addAttribute("account", account);
        model.addAttribute("reAccount", reAccount);
        return "account/account_detail";
    }


    //账户状态修改
    @RequestMapping("/accountSetStatus.do")
    public String account4(@Param("account_id") String account_id, Model model) {
        boolean flag = accountService.setStatusAccount(account_id);
        if (flag) {
            return "redirect:accountList.do";
        } else {
            String msg = "操作失败";
            model.addAttribute("msg", msg);
            account1(model);
            return "account/account_list";
        }
    }

    //账户删除
    @RequestMapping("/accountDelete.do")
    public String account5(@Param("account_id") String account_id, Model model) {
        boolean flag = accountService.deleteAccount(account_id);
        if (flag) {
            return "redirect:accountList.do";
        } else {
            String msg = "操作失败";
            model.addAttribute("msg", msg);
            account1(model);
            return null;

        }
    }
}
