package com.chao.login.service.impl;

import com.chao.admin.bean.AdminBean;
import com.chao.admin.dao.AdminMapper;
import com.chao.login.bean.LoginBean;
import com.chao.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Resource
    private AdminMapper adminMapper;

    @Override
    public boolean login(LoginBean loginBean) {
        AdminBean adminBean = adminMapper.findAdminByCode(loginBean.getAdminCode());
        if (adminBean ==null|| !adminBean.getPassword().equals(loginBean.getPassword())) return false;
        return true;
    }
}
