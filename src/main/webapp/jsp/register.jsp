<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员注册</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<style>
		  body{
		   margin-top:20px;
		   margin:0 auto;
		 }
		 .carousel-inner .item img{
			 width:100%;
			 height:300px;
		 }
		 .container .row div{ 
			 /* position:relative;
			 float:left; */
		 }
		 
		font {
		    color: #3164af;
		    font-size: 18px;
		    font-weight: normal;
		    padding: 0 10px;
		}
		 </style>
		 
		 <script type="text/javascript">
		 	
		 	//页面加载事件
		 	$(function() {
		 		checkUserName();
		 	});
		 	
		 	//封装验证用户名方法
		 	function checkUserName() {
		 		//alert(1)
		 		//找到用户名文本框
		 		$("#username").blur(function() {
		 			//获取username用户填写的值
		 			var username = $(this).val();
		 			//滤空判断
		 			if(username == "") {
		 				//如果为空，则提交按钮不可点击
		 				$("#submitBtn").prop("disabled","disabled");
		 				return;
		 			}
		 			
		 			//将用户填写的内容发送给xxservlet的xxx方法
		 			var url = "${pageContext.request.contextPath}/UserServlet";
		 			var sendData = {"method":"checkUsername","username":username};
		 			//处理服务器返回的数据
		 			var callBack = function(data) {
		 				//data属性：isExists是否存在该用户名       message：验证信息
		 				//<span id="checkSuccess" class="label label-success" style="display:none" ></span> 
						//<span id="checkError" class="label label-danger"  style="display:none" ></span>
		 				if(data.isExists) {
		 					//存在用户名，不能注册
		 					//提示错误信息
		 					$("#checkError").text(data.message).show();
		 					//隐藏正确信息
		 					$("#checkSuccess").hide();
		 					//按钮无法提交
			 				$("#submitBtn").prop("disabled","disabled");
		 				}else {
		 					//不存在用户名，可以注册
		 					//提示正确信息
		 					$("#checkSuccess").text(data.message).show();
		 					//隐藏错误信息
		 					$("#checkError").hide();
		 					//按钮可以提交
			 				$("#submitBtn").removeProp("disabled");
		 				}
		 			};
		 			var dataType = "json";
		 			
		 			//发送ajax
		 			$.post(url,sendData,callBack,dataType);
		 		});
		 	}
		 </script>
</head>
<body>
			<!-- 引入网页头 -->
			<%@ include file="/jsp/head.jsp" %>

<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font>会员注册</font>USER REGISTER
		
		<!-- 提交表单 -->
		<form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet" method="post" style="margin-top:5px;">
			<input type="hidden" name="method" value="regist">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名">
			    </div>						
			    <div class="col-sm-4">
					<span id="checkSuccess" class="label label-success" style="display:none" ></span> 
					<span id="checkError" class="label label-danger"  style="display:none" ></span>
				</div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3"  class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email">
			    </div>
			  </div>
			 <div class="form-group">
			    <label for="usercaption"  class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" name="name" class="form-control" id="usercaption" placeholder="请输入姓名">
			    </div>
			  </div>
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio1" value="男"> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio2" value="女"> 女
			</label>
			</div>
			  </div>		
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">出生日期</label>
			    <div class="col-sm-6">
			      <input type="date" name="birthday" class="form-control"  >		      
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-3">
			      <input type="text" class="form-control"  >
			      
			    </div>
			    <div class="col-sm-2">
			    <img src="${pageContext.request.contextPath}/img/captcha.jhtml"/>
			    </div>
			    
			  </div>
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input id="submitBtn" type="submit" disabled="disabled" width="100" value="注册" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;" class="form-control">
			    </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
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

</body></html>
