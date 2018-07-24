package com.chao.servicepage.dao;

import com.chao.account.bean.Account;
import com.chao.fee.bean.Cost;
import com.chao.servicepage.bean.ServiceBean;
import com.chao.servicepage.bean.ServicePage;
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
public interface ServiceMapper {

    List queryService(ServicePage servicePage);
    int getAllCount(ServicePage servicePage);
    List getCost();
    List getHost();
    Account getAccount(String idcard_no);
    int insertService(ServiceBean serviceBean);
    Cost getByName(String name);
    ServiceBean getByOS_username(String os_username);
    int updateService(@Param("id")String id,@Param("status")String status);

}
