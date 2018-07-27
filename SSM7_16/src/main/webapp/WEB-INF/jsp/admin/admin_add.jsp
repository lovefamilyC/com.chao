<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>云科技</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
    <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
    <script language="javascript" type="text/javascript">
        //保存成功的提示消息
        function showResult() {
            showResultDiv(true);
            window.setTimeout("showResultDiv(false);", 3000);
        }
        function showResultDiv(flag) {
            var divResult = document.getElementById("save_result_info");
            if (flag)
                divResult.style.display = "block";
            else
                divResult.style.display = "none";
        }
    </script>
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
        <li><a href="<%=request.getContextPath()%>/role/roleList.do" class="role_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/admin/adminList.do" class="admin_on"></a></li>
        <li><a href="<%=request.getContextPath()%>/fee/feeList.do" class="fee_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/account/accountList.do" class="account_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/service/serviceList.do" class="service_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/bill/bill_list.html" class="bill_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/report/report.do" class="report_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/user/user_info.html" class="information_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/user/user_modi_pwd.html" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <div id="save_result_info" class="save_success">保存成功！</div>
    <form action="" method="" class="main_form">
        <div class="text_info clearfix"><span>姓名：</span></div>
        <div class="input_info">
            <input type="text" name="name"/>
            <span class="required">*</span>
            <div class="validate_msg_long">20长度以内的汉字、字母、数字的组合</div>
        </div>
        <div class="text_info clearfix"><span>管理员账号：</span></div>
        <div class="input_info">
            <input type="text" name="admin_code"/>
            <span class="required">*</span>
            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>密码：</span></div>
        <div class="input_info">
            <input type="password" name="password"/>
            <span class="required">*</span>
            <div class="validate_msg_long error_msg">30长度以内的字母、数字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>重复密码：</span></div>
        <div class="input_info">
            <input type="password" name="rePassword"/>
            <span class="required">*</span>
            <div class="validate_msg_long error_msg">两次密码必须相同</div>
        </div>
        <div class="text_info clearfix"><span>电话：</span></div>
        <div class="input_info">
            <input type="text" class="width200" name="telephone"/>
            <span class="required">*</span>
            <div class="validate_msg_medium error_msg">正确的电话号码格式：手机或固话</div>
        </div>
        <div class="text_info clearfix"><span>Email：</span></div>
        <div class="input_info">
            <input type="text" class="width200" name="email"/>
            <span class="required">*</span>
            <div class="validate_msg_medium error_msg">50长度以内，正确的 email 格式</div>
        </div>
        <div class="text_info clearfix"><span>角色：</span></div>
        <div class="input_info_high">
            <div class="input_info_scroll">
                <ul>
                    <c:forEach items="${roleList}" var="role">
                        <li><input type="checkbox" name="roleidList" value="${role.role_id}"/>${role.name}</li>
                    </c:forEach>

                </ul>
            </div>
            <span class="required">*</span>
            <div class="validate_msg_tiny error_msg">至少选择一个</div>
        </div>
        <div class="button_info clearfix">
            <input type="button" value="保存" class="btn_save" id="addAmin"/>
            <input type="button" value="取消" class="btn_save"/>
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
    <br/>
    <span>版权所有(C)云科技有限公司 </span>
</div>
<script type="text/javascript">

    $(function () {
        $("#addAmin").click(function () {
            var checkID = [];
            $("input[name='roleidList']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                checkID[i] =$(this).val();
             });
            console.log(checkID);
            var d = $("form").serialize();
            console.log(d);
            $.ajax({
                type: "post",
                url: "adminAddAdmin.do",
//                contentType:"application/json",
                dataType:"text",
                data: d,
                traditional:true,
                success: function (data) {
                    $("#save_result_info").html(data);
                    showResult();
                },
                error: function () {
                    alert("抱歉,网络异常!")
                }
            })
        })
    })


</script>

</body>
</html>
