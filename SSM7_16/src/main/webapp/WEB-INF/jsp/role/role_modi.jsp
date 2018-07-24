<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
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
                <li><a href="<%=request.getContextPath()%>/role/roleList.do" class="role_on"></a></li>
                <li><a href="<%=request.getContextPath()%>/admin/admin_list.html" class="admin_off"></a></li>
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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" name="roleName" value="${map.get("role").name}" />
                    <input type="hidden" name="role_id" value="${map.get("role").role_id}" />
                    <span class="required">*</span>
                    <div class="validate_msg_medium error_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>

                            <c:forEach var="roleModule" items="${map.get('role').moduleList}">
                                <li><input type="checkbox" name="moduleName" checked />${roleModule.name}</li>
                            </c:forEach>
                            <c:forEach var="module" items="${map.get('allModul')}">
                                <li><input type="checkbox" name="moduleName" />${module.name}</li>
                            </c:forEach>

                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" id="btn" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" />
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
            //将一个表单的数据返回成JSON对象
            $.fn.serializeObject = function() {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function () {
                    if (o[this.name]) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };

            $(function () {
                $("#btn").click(function () {
                    var d = JSON.stringify($("form").serializeObject());
                    console.log(d);
                    $.ajax({
                        type:"post",
                        url:"roleModi.do",
                        dataType:"text",
                        contentType:"application/json",
                        data:d,
                        success:function (data) {
                            $("#save_result_info").html(data);
                            showResult();
                        },
                        error:function () {
                            alert("未知错误,请刷新")
                        }
                    })


                })
            })

        </script>

    </body>
</html>
