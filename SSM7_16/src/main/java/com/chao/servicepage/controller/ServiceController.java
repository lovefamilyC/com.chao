package com.chao.servicepage.controller;

import com.chao.account.bean.Account;
import com.chao.servicepage.bean.ServiceBean;
import com.chao.servicepage.bean.ServicePage;
import com.chao.servicepage.service.impl.MyServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    @Resource(name = "myServiceImpl")
    private MyServiceImpl myService;


    @RequestMapping("serviceList.do")
    public String service1(Model model) {
        ServicePage servicePage1 = new ServicePage();
        //设置当前页数
        servicePage1.setCurrentPage(1);
        ServicePage servicePage = myService.queryService(servicePage1);
        model.addAttribute("servicePage", servicePage);
        return "service/service_list";
    }

    @RequestMapping(value = "servicePage.do", method = RequestMethod.POST)
    public String service2(ServicePage servicePage, Model model) {
        ServicePage servicePage1 = myService.queryService(servicePage);
        model.addAttribute("servicePage", servicePage1);
        return "service/service_list";
    }

    //添加数据
    @RequestMapping("serviceAddIn.do")
    public String service3(Model model) {
        return "service/service_add";
    }

    @RequestMapping(value = "serviceAddOne.do", method = RequestMethod.POST)
    @ResponseBody
    public Map service33() {

        Map<String, List> map = myService.getMap();
        return map;
    }


    @RequestMapping(value = "serviceAddQuery.do", method = RequestMethod.GET)
    @ResponseBody
    public Account service4(@RequestParam String idcard_no) {
        Account account = myService.getAccount(idcard_no);
        return account;
    }

    @RequestMapping(value = "serviceAdd.do", method = RequestMethod.POST)
    @ResponseBody
    public String service5(ServiceBean serviceBean) {
        String msg = myService.insertService(serviceBean);
        return msg;
    }

    //修改状态
    @RequestMapping("serviceStatus.do")
    public String service6(@RequestParam String id, @RequestParam String status) {
        System.out.println(id+"===="+status);
        boolean flag = myService.updateService(id, status);

        return "redirect:serviceList.do";
    }

}
