<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <meta>
    <title>Title</title>
    <link rel="stylesheet" href="css/demo.css">
	<script>
    function jump(){
        window.location.href="main.html";
    }
	</script>
</head>

<body style="background-image: url(images/74764615_p0.jpg); background-repeat: no-repeat; background-attachment: fixed">
    <!--遮罩-->
    <div class="overlay"></div>
    <!--模态框-->
    <div id="modal" class="dropbox">
        <div class="items-container">
            <div id="close" style="cursor:pointer;float: right;width:20px">
                <span class="css-close"></span>
            </div>
            <div>
                <p class="head"><b><font color="white">拖拽文件至此</font></b></p>
                <div class="content" id="content">
                    <table class="table">
                        <tbody class="tbody"></tbody>
                    </table>
                </div>
                <div class="footer">
                    <button class="btn" onclick="upload()">开始上传</button>
                </div>
				<a href='#' onclick='clearAll()' style='position:absolute;bottom:10px;right:30px;'>清空所有</a>
            </div>
        </div>
    </div>
    <!--页面内容-->
    <div style="margin-top:40vh;text-align: center; background-color:aliceblue; opacity: 0.5; height: 150px; width:500px; line-height: 60px; position:absolute; left: 700px">
		<p><font color="black"><b>点击下方按钮，弹出模态框</b></font></p>
		<button class="btn" onclick="showModal()"><font color="black"><b>点击上传</b></font></button>
		<a href="main.jsp"><button class="btn"><font color="black"><b>返回</b></font></button></a>
    </div>
    
    
    <!--嵌入脚本-->
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
    <script  src="js/demo.js" type="text/javascript"></script>
    <form  action="batchUpload.action"  id="uploadForm" enctype="multipart/form-data">
    <input type="file" name="file" multiple="multiple"/><br/>
    <input type="submit" value="批量上传">
    </form>
    

</body>
</html>
