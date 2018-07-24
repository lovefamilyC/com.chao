package com.chao.fee.controller;

import com.chao.fee.bean.Cost;
import com.chao.fee.service.FeeService;
import com.chao.fee.service.impl.FeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Controller
@RequestMapping("/fee")
public class FeeController {
    @Resource(name = "feeImpl")
    private FeeService feeService;

    @RequestMapping("/feeList.do")
    public String fee1(Model model) {
        List<Cost> costList = feeService.queryAllCost();
        model.addAttribute("costList", costList);
        return "fee/fee_list";
    }

    @RequestMapping("/feeDetail.do")
    public String fee2(@RequestParam("cost_id") String cost_id, Model model) {
        Cost cost = feeService.queryById(cost_id);
        model.addAttribute("cost", cost);
        return "fee/fee_detail";
    }

    //添加操作
    @RequestMapping("/feeAdd33.do")
    public String fee33() {
        return "fee/fee_add";
    }

    @RequestMapping(value = "/feeAdd.do", method = RequestMethod.POST)
    public String fee3(Cost cost, Model model) {
        boolean flag = feeService.insertCost(cost);
        if (flag) {
            List<Cost> costList = feeService.queryAllCost();
            model.addAttribute("costList", costList);
            return "fee/fee_list";
        } else {
            String msg = "添加失败,资源名重复或为空";
            model.addAttribute("msg", msg);
            model.addAttribute("show", "show");
            return "fee/fee_add";
        }

    }


    //修改数据
    @RequestMapping(value = "/feeUpdate.do")
    public String fee4
    (@RequestParam("cost_id") String cost_id, Model model) {
        String status = "开通";
        boolean flag = feeService.updateCostStatus(status, cost_id);
        if (flag) {
            model.addAttribute("msg", "成功");
        } else {
            model.addAttribute("msg", "失败");
        }
        List<Cost> costList = feeService.queryAllCost();
        model.addAttribute("show", "show");
        model.addAttribute("costList", costList);
        return "fee/fee_list";
    }

    //删除
    @RequestMapping(value = "/feeDelete.do")
    public String fee5
    (@RequestParam("cost_id") String cost_id, Model model) {
        boolean flag = feeService.deleteCost(cost_id);
        if (flag) {
            model.addAttribute("msg", "删除成功");
        } else {
            model.addAttribute("msg", "开通状态无法操作");
        }
        List<Cost> costList = feeService.queryAllCost();
        model.addAttribute("costList", costList);
        model.addAttribute("show", "show");
        return "fee/fee_list";
    }

    //修改
    @RequestMapping("/feeModi66.do")
    public String fee66(@RequestParam("cost_id") String cost_id, Model model) {
        Cost cost = feeService.queryById(cost_id);
        if ("开通".equals(cost.getStatus())) {
            model.addAttribute("msg", "开通状态无法操作");
            List<Cost> costList = feeService.queryAllCost();
            model.addAttribute("costList", costList);
            model.addAttribute("show", "show");
            return "fee/fee_list";
        } else {
            model.addAttribute("cost", cost);
            return "fee/fee_modi";
        }
    }

    @RequestMapping(value = "/feeModi.do", method = RequestMethod.POST)
    public String fee6(Cost cost, Model model) {
        boolean flag = feeService.modiCost(cost);
        if (flag) {
            List<Cost> costList = feeService.queryAllCost();
            model.addAttribute("costList", costList);
            return "fee/fee_list";
        } else {
            model.addAttribute("msg", "修改未成功,资源名重复");
            model.addAttribute("show", "show");
            return "fee/fee_modi";
        }
    }


}
