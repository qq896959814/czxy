<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="css/themes/icon.css"/>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				$("#paginationId").pagination({
					total:'${pageInfo.total}',		//总记录数
					pageSize:'${pageInfo.pageSize}',		//每页显示个数
					pageNumber:'${pageInfo.pageNum}',		//当前页
					layout:['first','prev','sep','links',
						'sep','next','last','sep','manual'],//布局
					beforePageText:'当前第',					//显示跳转文本框，enter键触发
					afterPageText:'页，共${pageInfo.pages}页',
					displayMsg:'当前显示${pageInfo.startRow}到${pageInfo.endRow}条,共${pageInfo.size}条记录', //显示记录信息
					onSelectPage:function(pageNumber, pageSize){      //页面改变是触发
						location.href = "${pageContext.request.contextPath}/ProductServlet?method=findProByCid&cid=${param.cid}&pageNumber=" + pageNumber;
					}
				});
			});

		</script>
	</head>

	<body>
		
						<!-- 引入网页头 -->
			<%@ include file="/jsp/head.jsp" %>


		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
				</ol>
			</div>
			<c:forEach items="${pageInfo.list}" var="thisPro">
				<div class="col-md-2">
					<!—以下的a标签路径与index.jsp中热门商品的a标签路径是一样的，均是访问商品详细信息-->
					<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductById&pid=${thisPro.pid}">
						<img src="${pageContext.request.contextPath}/${thisPro.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p><a href="${pageContext.request.contextPath}/ProductServlet?method=findProductById&pid=${thisPro.pid}" style='color:green'>${thisPro.pname}</a></p>
					<p><font color="#FF0000">商城价：&yen;${thisPro.shop_price}</font></p>
				</div>
			</c:forEach>

			<%--<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>冬瓜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>

			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10002.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>圆白菜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>

			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10003.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>甜玉米</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>

			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10004.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>胡萝卜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10005.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>芹菜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>

			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10006.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>韭菜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>

			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10007.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>香菜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10008.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>土豆</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10007.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>香菜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10008.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>土豆</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10007.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>香菜</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/jsp/product_info.jsp">
					<img src="${pageContext.request.contextPath}/products/1/cs10008.jpg" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/jsp/product_info.jsp" style='color:green'>土豆</a></p>
				<p><font color="#FF0000">商城价：&yen;299.00</font></p>
			</div>--%>

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<%--<ul class="pagination" style="text-align:center; margin-top:10px;">
				<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#">7</a></li>
				<li><a href="#">8</a></li>
				<li><a href="#">9</a></li>
				<li>
					<a href="#" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>--%>
			<div class="easyui-panel">
				<div id="paginationId" style="font-size: 14px;margin: 0;display: block"></div>
			</div>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;"><img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="130px" height="130px" /></li>
				</ul>

			</div>
		</div>
		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="${pageContext.request.contextPath}/jsp/info.jsp">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>

	</body>

</html>