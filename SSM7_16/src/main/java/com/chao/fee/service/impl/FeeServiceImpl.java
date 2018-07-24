package com.chao.fee.service.impl;

import com.chao.fee.bean.Cost;
import com.chao.fee.dao.FeeMapper;
import com.chao.fee.service.FeeService;
import javafx.scene.input.DataFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
@Service("feeImpl")
public class FeeServiceImpl implements FeeService {
    @Resource
    private FeeMapper mapper;

    @Override
    public List queryAllCost() {
        return mapper.queryAllCost();
    }

    @Override
    public Cost queryById(String cost_id) {
        return mapper.queryById(cost_id);
    }

    @Override
    public Cost queryByName(String name) {
        return mapper.queryByName(name);
    }

    @Override
    public boolean insertCost(Cost cost) {
        //判断字符串是否为空
        if (cost.getName()==""){
            return false;
        }
        //判断是已存在名字
        Cost cost1 = queryByName(cost.getName());
        if (cost1 !=null){
            return false;
        }
        cost = judge(cost);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String creatime = dateFormat.format(date);
        cost.setCreatime(creatime);
        cost.setStatus("暂停");
        int count = mapper.insertCost(cost);
        return count>0;
    }

    @Override
    public boolean updateCostStatus(String status, String cost_id) {
        Cost cost = queryById(cost_id);
        if ("开通".equals(cost.getStatus())){
            return false;
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startime = dateFormat.format(date);
        int count = mapper.updateCostStatus(status,startime,cost_id);
        return count>0;
    }

    //删除
    @Override
    public boolean deleteCost(String cost_id) {
        Cost cost = queryById(cost_id);
        if ("开通".equals(cost.getStatus())){
            return false;
        }
        String status = "删除";
        int count = mapper.deleteCost(cost_id,status);
        return count>0;
    }

    //修改方法
    @Override
    public boolean modiCost(Cost cost) {
        Cost cost1 = mapper.queryById(cost.getCost_id());
        if (!cost1.getName().equals(cost.getName()) ){
            Cost cost2 = queryByName(cost.getName());
            if (cost2 !=null){
                return false;
            }
        }
        cost = judge(cost);
        int count = mapper.modiCost(cost);
        return count>0;
    }




    //判断方法    true继续执行
    public Cost judge(Cost cost){

        //根据类型添加数据
        if ("包月".equals(cost.getCost_type())){
            cost.setBase_duration("0");
            cost.setUnit_cost("0");
        }
        if ("计时".equals(cost.getCost_type())){
            cost.setBase_duration("0");
            cost.setBase_cost("0");
        }
        return cost;
    }

}
