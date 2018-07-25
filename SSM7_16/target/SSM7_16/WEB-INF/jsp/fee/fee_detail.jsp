<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />       
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="<%=request.getContextPath()%>/login/login.do" class="index_off"></a></li>
                <li><a href="../role/role_list.jsp" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="<%=request.getContextPath()%>/fee/feeList.do" class="fee_on"></a></li>
                <li><a href="<%=request.getContextPath()%>/account/accountList.do" class="account_off"></a></li>
                <li><a href="../service/service_list.jsp" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.jsp" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${cost.cost_id}" /></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${cost.name}"/></div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                    <select class="readonly" disabled>
                        <option>开通</option>
                        <option>暂停</option>
                        <option>删除</option>
                        <option value="" selected='selected'>${cost.status}</option>
                    </select>                        
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="cost_type" id="monthly" disabled="disabled" class="costType"/>
                    <label for="monthly">包月</label>
                    <input type="radio" name="cost_type" id="package" disabled="disabled" class="costType"/>
                    <label for="package">套餐</label>
                    <input type="radio" name="cost_type"  id="timeBased" disabled="disabled" class="costType"/>
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" class="readonly" readonly value="${cost.base_duration}"  />
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="${cost.base_cost}" />
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="${cost.unit_cost}" />
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="${cost.creatime}" /></div>
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="${cost.startime}" /></div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70 readonly" readonly>${cost.descr}</textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='<%=request.getContextPath()%>/fee/feeList.do'" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)云科技有限公司 </span>
        </div>
    <script type="text/javascript">
        $(function () {
            var costType = "${cost.cost_type}";
            console.log(costType)
            if(costType=='包月'){
                $("#monthly").attr("checked","checked")
            }if(costType=="套餐"){
                $("#package").attr("checked","checked")
            }if(costType=="计时"){
                $("#timeBased").attr("checked","checked")
            }
        })
    </script>
    </body>
</html>
