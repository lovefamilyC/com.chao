package com.chao.servicepage.service.impl;

import com.chao.account.bean.Account;
import com.chao.fee.bean.Cost;
import com.chao.servicepage.bean.HostBean;
import com.chao.servicepage.bean.ServiceBean;
import com.chao.servicepage.bean.ServicePage;
import com.chao.servicepage.dao.ServiceMapper;
import com.chao.servicepage.service.MyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Service("myServiceImpl")
public class MyServiceImpl implements MyService {
    @Resource
    private ServiceMapper serviceMapper;

    @Override
    public ServicePage queryService(ServicePage servicePage) {

        //判断时候为全部查询 0全部 1开通 2暂停 3删除
        if ("0".equals(servicePage.getStatus())) {
            servicePage.setStatus("");
        }
        //判断页数
        if (servicePage.getCurrentPage() < 1) {
            servicePage.setCurrentPage(1);
        }
        //得到全部条数
        int allCount = serviceMapper.getAllCount(servicePage);
        servicePage.setAllCount(allCount);
        //设置展示条数
        servicePage.setOnePageNum(5);

        if (servicePage.getCurrentPage() > servicePage.getAllPage()) {
            servicePage.setCurrentPage(servicePage.getAllPage());
        }

        //查询servcieBean
        List<ServiceBean> beanList = serviceMapper.queryService(servicePage);
        servicePage.setList(beanList);

        //设置页数
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < servicePage.getAllPage(); i++) {
            stringList.add(String.valueOf(i + 1));
        }
        servicePage.setStringList(stringList);
        return servicePage;
    }

    @Override
    public Map getMap() {
        List<HostBean> hosts = serviceMapper.getHost();
        List<Cost> costs = serviceMapper.getCost();
        Map<String, List> map = new HashMap();
        map.put("hosts", hosts);
        map.put("costs", costs);
        return map;
    }

    @Override
    public Account getAccount(String idcard_no) {
        return serviceMapper.getAccount(idcard_no);
    }


    @Override
    public String insertService(ServiceBean serviceBean) {
        String msg = "";
        if (serviceBean.getLogin_passwd() == null || serviceBean.getOs_username() == null) {
            msg = "用户名或密码为空";
        } else {
            if (!serviceBean.getLogin_passwd().equals(serviceBean.getConfirm_passwd())) {
                msg = "两次密码不一样";
            } else {
                ServiceBean serviceBean1 = serviceMapper.getByOS_username(serviceBean.getOs_username());
                if (serviceBean1 != null) {
                    msg = "保存失败！192.168.0.23服务器上已经开通过 OS 账号" + serviceBean.getOs_username();
                } else {
                    Cost cost = serviceMapper.getByName(serviceBean.getName());
                    Date date = new Date();
                    SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm");
                    String data = sf.format(date);
                    serviceBean.setCreate_date(data);
                    serviceBean.setCost_id(cost.getCost_id());
                    serviceBean.setStatus("1");
                    int count = serviceMapper.insertService(serviceBean);
                    if (count > 0) {
                        msg = "添加成功";
                    } else {
                        msg = "添加失败";
                    }
                }
            }
        }
        return msg;
    }

    @Override
    public boolean updateService(String id, String status) {

        return serviceMapper.updateService(id,status)>0;
    }

}
