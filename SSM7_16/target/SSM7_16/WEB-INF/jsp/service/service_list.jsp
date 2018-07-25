<!DOCTYPE html >
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
        //显示角色详细信息
        function showDetail(flag, a) {
            var detailDiv = a.parentNode.getElementsByTagName("div")[0];
            if (flag) {
                detailDiv.style.display = "block";
            }
            else
                detailDiv.style.display = "none";
        }
        //删除
        function deleteAccount() {
            var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
            document.getElementById("operate_result_info").style.display = "block";
        }
        //开通或暂停
        function setState(e,f) {
            var r = window.confirm("确定要执行此项操作吗？");
            if (r){
                window.location.href="../service/serviceStatus.do?id="+e+"&status="+f;
            }

//            document.getElementById("operate_result_info").style.display = "block";
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
    <form action="../service/servicePage.do" method="post">
        <!--查询-->
        <div class="search_add">
            <div>OS 账号：<input id="os" type="text" name="os_username" value="${servicePage.os_username}" class="width100 text_search"/></div>
            <div>服务器 IP：<input id="ip" type="text" name="unix_host" value="${servicePage.unix_host}" class="width100 text_search"/></div>
            <div>身份证：<input id="id" type="text" name="idcard_no" value="${servicePage.idcard_no}" class="text_search"/></div>
            <input id="page1" type="hidden" name="currentPage" value=""/>
            <div>状态：
                <select class="select_search" name="status">
                    <option id="op0" value="0">全部</option>
                    <option id="op1" value="1">开通</option>
                    <option id="op2" value="2">暂停</option>
                    <option id="op3" value="3">删除</option>
                </select>
            </div>
            <div><input type="button" value="搜索" class="btn_search" id="AA1" onclick="pageAccount2(1)"/></div>
            <a href="<%=request.getContextPath()%>/service/serviceAddIn.do"><input type="button" value="增加" class="btn_add"/></a>

        </div>
        <!--删除的操作提示-->
        <div id="operate_result_info" class="operate_success">
            <img src="../images/close.png" onclick="this.parentNode.style.display='none';"/>
            删除成功！
        </div>
        <!--数据区域：用表格展示数据-->
        <div id="data">
            <table id="datalist">
                <tr>
                    <th class="width50">业务ID</th>
                    <th class="width70">账务账号ID</th>
                    <th class="width150">身份证</th>
                    <th class="width70">姓名</th>
                    <th>OS 账号</th>
                    <th class="width50">状态</th>
                    <th class="width100">服务器 IP</th>
                    <th class="width100">资费</th>
                    <th class="width200"></th>
                </tr>
                <c:forEach var="serviceBean" items="${servicePage.list}">
                    <tr>
                        <td><a href="#" title="查看明细">${serviceBean.id}</a></td>
                        <td>${serviceBean.account_id}</td>
                        <td>${serviceBean.account.idcard_no}</td>
                        <td>${serviceBean.account.real_name}</td>
                        <td>${serviceBean.os_username}</td>
                        <td>
                            <c:choose>
                                <c:when test="${serviceBean.status=='1'}">
                                    开通
                                </c:when>
                                <c:when test="${serviceBean.status=='2'}">
                                    暂停
                                </c:when>
                                <c:when test="${serviceBean.status=='3'}">
                                    删除
                                </c:when>
                            </c:choose>
                        </td>
                        <td>${serviceBean.unix_host}</td>
                        <td>
                            <a class="summary" onmouseover="showDetail(true,this);"
                               onmouseout="showDetail(false,this);">${serviceBean.cost.name}</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                                    ${serviceBean.cost.descr}
                            </div>
                        </td>
                        <td>
                        <c:choose>
                                <c:when test="${serviceBean.status=='1'}">
                                    <input type="button" value="暂停"  class="btn_pause" onclick="setState(${serviceBean.id},'2');"/>
                                    <input type="button" value="修改" class="btn_modify"
                                           onclick="location.href='service_modi.jsp';"/>
                                    <input type="button" value="删除" class="btn_delete" onclick="setState(${serviceBean.id},'3');"/>
                                </c:when>
                                <c:when test="${serviceBean.status=='2'}">
                                    <input type="button" value="开通"  class="btn_start" onclick="setState(${serviceBean.id},'1');"/>
                                    <input type="button" value="修改" class="btn_modify"
                                           onclick="location.href='account_modi.jsp';"/>
                                    <input type="button" value="删除" class="btn_delete" onclick="setState(${serviceBean.id},'3');"/>
                                </c:when>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p>业务说明：<br/>
                1、创建即开通，记载创建时间；<br/>
                2、暂停后，记载暂停时间；<br/>
                3、重新开通后，删除暂停时间；<br/>
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br/>
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br/>
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
        </div>
        <!--分页-->
        <div id="pages">
            <a href="#" onclick="pageAccount2(1)">首页</a>
            <a href="#" onclick="pageAccount2(${servicePage.currentPage-1})">上一页</a>
            <c:forEach items="${servicePage.stringList}" var="i">
                <c:choose>
                    <c:when test="${servicePage.currentPage==i}">
                        <a href="#" class="current_page" onclick="pageAccount2(${i})">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" onclick="pageAccount2(${i})">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <a href="#" onclick="pageAccount2(${servicePage.currentPage+1})">下一页</a>
            <a href="#" onclick="pageAccount2(${servicePage.allPage})">末页</a>
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
    <p>版权所有(C)云科技有限公司 </p>
</div>
<script type="text/javascript">
    $(function () {
        var status = "${servicePage.status}"
        if (status==0){
            $("#op0").attr("selected","selected")
        }if (status==1){
            $("#op1").attr("selected","selected");
        }if (status==2){
            $("#op2").attr("selected","selected")
        }if (status==3){
            $("#op3").attr("selected","selected")
        }
    })


    function pageAccount2(e){
        $("#page1").attr("value", e);
        $("form").submit();

    }


    function addAccount() {
        $(function () {
            $.ajax({
                type: "get",
                url: "serviceAddIn.do",
//                dataType: "json",
//                contentType: "application/json", //前端向后台发送json数据时要声明
//                data: d,
//                success: function (data) {
//                   var servicePage = eval(data);
//                    show(servicePage);
//                },
                error: function () {
                    alert("失败")
                }
            })
        })
    }

    //修改状态






//
//    function show(servicePage) {
//        $("#os").attr("value",servicePage.os_username);
//        $("#ip").attr("value",servicePage.unix_host);
//        $("#id").attr("value",servicePage.idcard_no);
//        if (0==servicePage.status){
//            $("#op0").attr("selected","selected")
//        }if (1==servicePage.status){
//            $("#op1").attr("selected","selected")
//        }if (2==servicePage.status){
//            $("#op2").attr("selected","selected")
//        }if (3==servicePage.status){
//            $("#op3").attr("selected","selected")
//        }
//
//
//
//    }



</script>
</body>
</html>
