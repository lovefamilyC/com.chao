package com.chao.fee.service;

import com.chao.fee.bean.Cost;

import java.util.List;

/* I AM A PURE SHEEP
 *                   
 * WHAT YOU WANT TO DO                 
 *                 
 * FUCK AS YOU WISH                                           
 */
public interface FeeService {
    List queryAllCost();
    Cost queryById(String cost_id);
    Cost queryByName(String name);
    boolean insertCost(Cost cost);

    boolean updateCostStatus(String status ,String cost_id);
    boolean deleteCost(String cost_id);
    boolean modiCost(Cost cost);

}
