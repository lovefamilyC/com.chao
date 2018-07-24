package com.chao.fee.dao;

import com.chao.fee.bean.Cost;
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
public interface FeeMapper {
    List queryAllCost();
    Cost queryById(String cost_id);
    Cost queryByName(String name);
    int insertCost(Cost cost);
    int updateCostStatus(@Param("status") String status ,@Param("time")String time ,@Param("cost_id") String cost_id);
    int deleteCost(@Param("cost_id") String cost_id,@Param("status") String status);
    int modiCost(Cost cost);
}
