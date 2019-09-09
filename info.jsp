<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <base href="<%=basePath%>">
    
    <title>login</title>
    	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width">
<meta name="viewport" media="(device-height: 568px)" content="initial-scale=1.0,user-scalable=no,maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="css/gongyong.css">
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>



  <script type="text/javascript">
function email123(){
	var email = document.getElementById("email").value;
	var r = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	boolean flag = r.test(email);
	if(flag){
		$.ajax({
	async:true,
	data:{"email":email},
	type:"POST",
	url:"${ctx}/toChangeInfo.action?change=email",
	success:function(data){
	//todo
	if(data=='0'){
    	  alert("更改未成功！");
      }else{
    	  alert("邮件地址更改成功！")
      }
	}
	
 })
	}else{
	alert("邮件地址输入有误");
	}

}

function password(){
	var nowpassword = document.getElementById("nowpassword").value;
	var originpassword = document.getElementById("originpassword").value;
$.ajax({
	async:true,
	data:{"nowpassword":nowpassword,"originpassword":originpassword},
	type:"POST",
	url:"${ctx}/toChangeInfo.action?change=password",
	success:function(data){
	//todo
	if(data=='0'){
    	  alert("更改未成功！");
      }else{
    	  alert("密码更改成功！")
      }
	}
}
</script>
</head>

<body>

<div class="head">
<span class="head_lf"><a href="" onclick="javascript:history.go(-1)">返回</a></span>
修改个人信息
</div>

<div class="zhuce">
	<form action="toChangeInfo.action?change=picurl" method="post" enctype="multipart/form-data">
    <div class="text">
        <span>头像</span>
        <input type="file" name="pic" >
    </div>
	<div class="btndl"><input type="submit" value="上传头像"/></div>
	</form>
	
	
	<form action="toChangeInfo.action?change=email" method="post" enctype="multipart/form-data">
    <div class="text">
        <span>邮箱</span>
        <input type="text" id="email" name="email" placeholder="请输入邮箱" class="input">
    </div>
	<div class="btndl"><input type="submit" id="btn" value="提交"/></div>
	</form>
	
	
	<form action="toChangeInfo.action?change=password" method="post" enctype="multipart/form-data">
	<div class="text">
        <span>原密码</span>
        <input type="text" id="originpassword" name="originpassword" placeholder="请输入原密码" class="input">
    </div>
	<div class="text">
        <span>新密码</span>
        <input type="text" id="nowpassword" name="nowpassword" placeholder="请输入新密码" class="input">
    </div>
    <div class="btndl"><input type="submit" value="提交"></div>
    </form>
</div>


</body>
</html>
