<!DOCTYPE html>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
    <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
    <script language="javascript" type="text/javascript">
        //保存成功的提示信息
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

        //自动查询账务账号
        function searchAccounts(txtObj) {
            //document.getElementById("a1").innerHTML = txtObj.value;
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
        <li><a href="<%=request.getContextPath()%>/role/roleList.do" class="fee_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/admin/admin_list.html" class="admin_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/fee/feeList.do" class="fee_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/account/accountList.do" class="account_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/service/serviceList.do" class="service_on"></a></li>
        <li><a href="<%=request.getContextPath()%>/bill/bill_list.html" class="bill_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/report/report.do" class="report_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/user/user_info.html" class="information_off"></a></li>
        <li><a href="<%=request.getContextPath()%>/user/user_modi_pwd.html" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <!--保存操作的提示信息-->
    <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
    <form action="" method="" class="main_form">
        <!--内容项-->
        <div class="text_info clearfix"><span>身份证：</span></div>
        <div class="input_info">
            <input type="text" name="idcard_no" value="" placeholder="查询出的结果写入账务账号" class="width180" id="idcard_no"/>
            <input type="button" value="查询账务账号" class="btn_search_large" id="btn1"/>
            <span class="required">*</span>
            <div class="validate_msg_short">没有此身份证号，请重新录入。</div>
        </div>
        <div class="text_info clearfix"><span>账务账号：</span></div>
        <div class="input_info">
            <input type="text" name="account_id" value="" id="account_id"/>
            <span class="required">*</span>
            <div class="validate_msg_long">没有此账务账号，请重新录入。</div>
        </div>
        <div class="text_info clearfix"><span>资费类型：</span></div>
        <div class="input_info">
            <select id="sel1" name="name">
                <%--<option>包 20 小时</option>--%>
                <%--<option>包 40 小时</option>--%>
                <%--<option>包 60 小时</option>--%>
                <%--<option>包月</option>--%>
            </select>
        </div>
        <div class="text_info clearfix"><span>服务器 IP：</span></div>
        <div class="input_info">
            <select id="sel2" name="unix_host">
                <%--<option>192.168.0.23</option>--%>
                <%--<option>192.168.0.23</option>--%>
                <%--<option>192.168.0.23</option>--%>
                <%--<option>192.168.0.23</option>--%>
            </select>
            <%--<input type="text" value="192.168.0.23"  />--%>
            <span class="required">*</span>
            <div class="validate_msg_long">15 长度，符合IP的地址规范</div>
        </div>
        <div class="text_info clearfix"><span>添加 OS 账号：</span></div>
        <div class="input_info">
            <input type="text" name="os_username" value=""/>
            <span class="required">*</span>
            <div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>密码：</span></div>
        <div class="input_info">
            <input type="password" name="login_passwd"/>
            <span class="required">*</span>
            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>重复密码：</span></div>
        <div class="input_info">
            <input type="password" name="confirm_passwd"/>
            <span class="required">*</span>
            <div class="validate_msg_long">两次密码必须相同</div>
        </div>
        <!--操作按钮-->
        <div class="button_info clearfix">
            <input type="button" value="保存" class="btn_save" id="btn2"/>
            <input type="button" value="取消" class="btn_save" onclick="window.history.go(-1)"/>
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

    //刚进入时查询
    $(function () {
        $.ajax({
            type: "post",
            url: "serviceAddOne.do",
            dataType: "json",
            success: function (data) {
                var e = eval(data);
                if (e==null){
                    alert("未知错误,请刷新")
                }else {show1(e);}
            },
            error: function () {
                alert("未知错误,请刷新")
            }

        })
    })


    function show1(e) {
        $.each(e.costs, function (i, item) {
            var html = "<option>" + item.name + "</option>"
            $("#sel1").append(html)
        })

        $.each(e.hosts, function (i, item) {
            var html = "<option>" + item.id + "</option>"
            $("#sel2").append(html)
        })
    }

    //根据身份证查询
    $(function () {
        $("#btn1").click(function () {
            var idcard_no = $("#idcard_no").val();
            $.ajax({
                type: "get",
                url: "serviceAddQuery.do",
                data: {"idcard_no": idcard_no},
                success: function (data) {
                    var e = eval(data);
                    if (e==null){
//                        document.getElementById("save_result_info").innerHTML="没有此身份证账号";
                        $("#save_result_info").html("没有此身份证账号")
                        showResult();
                    }else { show2(e);}

                },
                error: function () {
                    alert("未知错误,请刷新")
                }

            })


        })
    })

    function show2(e) {
        $("#account_id").val(e.account_id)
    }


    //将一个表单的数据返回成JSON对象
    $.fn.serializeObject = function () {
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
    //添加跳转
    $(function () {
        $("#btn2").click(function () {
            $.ajax({
                type:"post",
                url:"serviceAdd.do",
                data:$("form").serialize(),
                success:function (data) {
                    $("#save_result_info").html(data)
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
