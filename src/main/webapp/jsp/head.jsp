<%--
  Created by IntelliJ IDEA.
  User: lb_nst
  Date: 2019/5/21
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        //发送ajax请求所有分类的信息，拼写成导航栏
        $(function () {

            //准备ajax参数
            var url = "${pageContext.request.contextPath}/IndexServlet";
            var param = {
                "method": "getCategory"
            };
            var dataType = "json";
            //发送请求
            $.post(url, param, function (categories) {
//                alert("接收到了ajax请求来的所有分类")
                //将数据拼写成导航栏
                console.log(categories)

                //迭代所有的分类
                $(categories).each(function () {
                    //this代表每一个分类对象
                    <%--<li><a href="#">${thisCategory.cname}</a></li>&ndash;%&gt;--%>
                    var thisLi = "<li><a href='${pageContext.request.contextPath}/ProductServlet?method=findProByCid&pageNumber=1&cid=" + this.cid + "'>" + this.cname + "</a></li>";
                    //找到导航栏的ul，每次循环向其中拼写一个li
                    $("#navigator").append(thisLi)
                })

            }, dataType)
        })
    </script>
</head>
<body>
<!--
描述：菜单栏
-->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <%--判断当前是否为登陆状态,判断依据sessin中是否有登陆用户--%>
            <c:if test="${not empty loginUser}">
                欢迎您,${loginUser.name}
                <c:if test="${loginUser.sex == '男'}">先生</c:if>
                <c:if test="${loginUser.sex == '女'}">女士</c:if>
                <%--我的订单,必须是登陆用户才有--%>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=logout">退出</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
            </c:if>
            <%--登陆与注册必须没有登陆才能看到--%>
            <c:if test="${empty loginUser}">
                <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
            </c:if>
            <%--登陆不登陆都有购物车--%>
            <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
        </ol>
    </div>
</div>

<!--
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="navigator" class="nav navbar-nav">
                    <%--当访问jsp/index.jsp网页时，在request中应该已经存储了所有的分类信息。--%>
                    <%--该网页应该从rquest中读取所有的分类信息，将其拼写到导航栏中--%>
                    <%--<li class="active"><a href="${pageContext.request.contextPath}/jsp/product_list.jsp">手机数码<span class="sr-only">(current)</span></a></li>--%>
                    <%--<li><a href="#">电脑办公</a></li>--%>
                    <%--<c:forEach items="${categories}" var="thisCategory">--%>
                    <%--<li><a href="#">${thisCategory.cname}</a></li>&ndash;%&gt;--%>
                    <%--</c:forEach>--%>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>


</body>
</html>
