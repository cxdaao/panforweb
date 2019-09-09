<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width">
<meta name="viewport" media="(device-height: 568px)" content="initial-scale=1.0,user-scalable=no,maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="css/gongyong.css">
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"> 
function getPwd(){
var index = parent.layer.getFrameIndex(window.name);
var email=$("#email").val();
var username=$("#username").val();
if(email!=""&&username!=""){
$.ajax({
    url:"/findPasswordBack.action",    //请求的url地址
    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
    data:{"email":email,"username":username,},    //参数值
    type:"post",   //请求方式
    success:function(data){
      if(data=='0'){
    	  alert("用户信息有误，请仔细核对!");
      }else{
    	  alert("邮件已发送成功，请注意接收!")
      }
      
    },
});
}else{

alert("用户名和邮箱不能为空");
}
}
</script>
</head>
<body>
<div class="zhuce">
	<form action="findPasswordBack.action" >
    <div class="text">
        <span>邮箱</span>
        <input type="text" id="email" name="email" placeholder="请输入邮箱" class="input">
    </div>

	
    <div class="text">
        <span>用户名</span>
        <input type="text" id="username" name="username" placeholder="请输入用户名" class="input">
    </div>
	<div class="btndl"><input type="submit" id="btn" value="找回密码"/></div>
	</form>
    <!--  
    <div class="password_d">
            <div class="dang">当前位置：找回密码</div>
            <div class="password_f"> 
            <form action="findPasswordBack.action">
            <dl><dt><span style="color:red">*</span>用户名：</dt><dd><input type="text" name="username" id="username" class="in"/></dd><dd>输入您的用户名</dd></dl>
                <dl><dt><span style="color:red">*</span>邮箱：</dt><dd><input type="text" name="email" id="email" class="in"/></dd><dd>输入您注册信息中的email地址</dd></dl>
                <dl style="border:0;"><dt>&nbsp;</dt><dd><input type="submit" value="找回密码"  /></dd></dl>
                </form>
           </div>
        </div>
    </div>
    -->
</body>
</html>
