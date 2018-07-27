<!DOCTYPE html>
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
            <form action="<%=request.getContextPath()%>/admin/adminListPage.do" method="post">
                <!--查询-->
                <div class="search_add">
                    <div>
                        模块：
                        <select id="selModules" class="select_search" name="moduleName">
                            <option id="op">全部</option>
                            <c:forEach var="module" items="${map.get('moduleList')}">
                                <c:choose>
                                    <c:when test="${module.name == map.get('adminPage').moduleName}">
                                        <option selected>${module.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${module.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div>角色：<input type="text" name="adminName" value="${map.get('adminPage').adminName}" class="text_search width200" /></div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="searchPage(1)"/></div>
                    <input type="hidden" name="currentPage" value="" id="currentPage"/>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='admin_add.jsp';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>

                        <c:forEach items="${map.get('adminPage').list}" var="admin">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${admin.admin_id}</td>
                            <td>${admin.name}</td>
                            <td>${admin.admin_code}</td>
                            <td>${admin.telephone}</td>
                            <td>${admin.email}</td>
                            <td>${admin.enrolldate}</td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
                                    <c:forEach begin="1" end="${admin.roleList.size()}" var="i">
                                        <c:if test="${i==1}">
                                            ${admin.roleList[0].name}
                                        </c:if>
                                        <c:if test="${i==2}">
                                            ...
                                        </c:if>
                                    </c:forEach>

                                </a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
                                    <c:forEach var="role" items="${admin.roleList}">
                                        ${role.name}&nbsp;
                                    </c:forEach>
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='admin_modi.jsp';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin();" />
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                    <a href="#" onclick="searchPage('${map.get('adminPage').currentPage-1}')">上一页</a>

                    <c:forEach var="i" items="${map.get('adminPage').stringList}">
                        <c:choose>
                            <c:when test="${map.get('adminPage').currentPage==i}">
                                <a href="#" class="current_page" onclick="searchPage(${i})">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#"  onclick="searchPage(${i})">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <a href="#" onclick="searchPage(${map.get('adminPage').currentPage+1})">下一页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <span>版权所有(C)云科技有限公司 </span>
        </div>
    <script type="text/javascript">
        $(function () {

        })


        function searchPage(e) {
            $("#currentPage").val(e);
            var d = $("#currentPage").val();
            console.log(d)
            $("form").submit();
        }

        //显示角色详细信息
        function showDetail(flag, a) {
            var detailDiv = a.parentNode.getElementsByTagName("div")[0];
            if (flag) {
                detailDiv.style.display = "block";
            }
            else
                detailDiv.style.display = "none";
        }
        //重置密码
        function resetPwd() {
            alert("请至少选择一条数据！");
            //document.getElementById("operate_result_info").style.display = "block";
        }
        //删除
        function deleteAdmin() {
            var r = window.confirm("确定要删除此管理员吗？");
            document.getElementById("operate_result_info").style.display = "block";
        }
        //全选
        function selectAdmins(inputObj) {
            var inputArray = document.getElementById("datalist").getElementsByTagName("input");
            for (var i = 1; i < inputArray.length; i++) {
                if (inputArray[i].type == "checkbox") {
                    inputArray[i].checked = inputObj.checked;
                }
            }
        }
    </script>
    </body>
</html>
