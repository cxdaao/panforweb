<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>	
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	j = 1;
	$(document).ready(function(){
			
		$("#btn_add2").click(function(){
			document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input class="btn btn-default" name="file_'+j+'" type="file"  /><input class="btn btn-default" type="button" value="删除" onclick="del_2('+j+');"/></div>';
			  j = j + 1;
		});
	});
	
	function del_2(o){
		 document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));
	}
 
</script>

	<style> 
        .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
    </style>
<script>
    function jump(){
        window.location.href="upload.html";
    }
    
    
    function contextMenu(props,events){
   this.props = props;
   this.events = events;
}
 
contextMenu.prototype.buildContextMenu = function(){
	 // 加载右键菜单体
   var menuObj = document.getElementById(this.props.menuID);
   
   // 提取菜单初始化设置的属性及事件
   var targetEle = this.props.targetEle;
   var eventFunc = this.events;
   
   // 初始化菜单项的mouseover 和 mouseout事件
   var _items = menuObj.getElementsByTagName("li");
  	for(var i=0;i<_items.length;i++){
  		if(_items[i].className != "separator"){
  			 _items[i].className = "item";
	  		 _items[i].onmouseover = function(){this.className ="item itemOver";};
	  		 _items[i].onmouseout = function(){this.className = "item";}
  	  }
  }
 
   document.oncontextmenu = function(evt){
   	     var _bodyWidth = null;
		 var _bodyHeight = null;
		 var _mWidth = null;
		 var _mHeight = null;
		 var _px = null;
		 var _py = null;
	 	 try{
				  var cobj = ele(evt);
				  if(cobj.className == targetEle){
				  	// 绑定菜单项点击事件
			  	  for(var j=0;j<_items.length;j++){
			  		  if(_items[j].className != "separator"){
				  		 _items[j].onclick = function(){hide();func(this.id,cobj);};
			  	    }
			  	  }
			  	
			  	// 判断显示位置
			  	_px = parseInt(getX(evt));
			  	_py = parseInt(getY(evt));
			  	_bodyWidth = parseInt(document.body.offsetWidth ||document.body.clientWidth);
			  	_bodyHeight = parseInt(document.body.offsetHeight || document.body.clientHeight);
			  	_mWidth = parseInt(menuObj.style.width);
			  	_mHeight = parseInt(menuObj.offsetHeight);
			  	menuObj.style.left = ((_px + _mWidth) > _bodyWidth?(_px - _mWidth):_px) +"px";
				menuObj.style.top  = ((_py + _mHeight) > _bodyHeight?(_py - _mHeight):_py) +"px";
				menuObj.style.display = "block";
			  }else{
			    hide();	
			  }
		  }catch(e){
		  }finally{
		  	_bodyWidth = null;
			  _bodyHeight = null;
			  _mWidth = null;
			  _mHeight = null;
			  _px = null;
		    _py = null;
		  	clearEventBubble(evt);
		  	return false;	
		  }
	  
 }
 
 document.onclick = function(){hide();}
  
 var func = function(fid,srcEle){
  		eventFunc.bindings[fid](srcEle);
  }
  
 var hide = function(){
   	 try{
  		 if(menuObj && menuObj.style.display != "none"){
  		 		menuObj.style.display = "none";
  		 	}
  	}catch(e){}
  }
 var ele = function(evt){
      evt = evt||window.event;
      var _ele = null;
      try{
      	_ele = (evt.srcElement || evt.target);
      	return _ele;
      }catch(e){
      }finally{
      	_ele = null;
      }
   }
 

 
 
/*==============================================================*/
 	function getX(evt){
 		  var _x = null;
		 	try{
		 			evt = evt || window.event;
		 			_x = (evt.x || evt.clientX || evt.pageX);
		 			return _x;
		 	}catch(e){
		 	}finally{
		 		_x = null;
		 	}
		
	}
	
	function getY(evt){
			var _y = null;
		 	try{
		 			evt = evt || window.event;
		 			_y = (evt.y || evt.clientY || evt.pageY);
		 			return _y;
		 	}catch(e){
		 	}finally{
		 		_y = null;
		 	}
	}
	
	function clearEventBubble(evt){
	   evt = evt || window.event;
	   
	   if(evt.stopPropagation){
	   	 evt.stopPropagation();
	   }else{
	     evt.cancelBubble = true; 
	   }
	   
    if(evt.preventDefault){
    	 evt.preventDefault();
    }else{ 
       evt.returnValue = false;
    }
 
	} 
 
	function reP(){
	document.getElementById('oImg').style.display = "block";
	}
</script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function preview(path){
    var ext=path.split('.')[1];
    if(ext=="PNG"){
        window.open("<%=path%>/file/showImage.do?path="+path);
    }
	}
	
	function fun(){
     var arr = [];
      $("[name='id']:checked").each(function () {
          arr.push($(this).next().html());
      })
      $http({
		method : 'get',
		url : '../memquery?params=' + JSON.stringify(arrays)//使用json传递
    }).success(function(data) {
		alert(JSON.stringify(data));
		alert("成功");
	}).error(function() {
		alert("提交失败");
	});

}
	function share(){
	document.mainArea.action = "normalShare.action";
	document.mainArea.submit();
	}
	
	function batchDownload(){
		document.mainArea.action = "batchDownload.action";
	document.mainArea.submit();
	}
	

	function gcb123456(){
		var checkboxValue = new Array();
		var checkboxText = new Array();
		var checkboxStr = document.getElementById("lalalala");
		for(var i = 0; i<checkboxStr.length;i++){
			if(checkboxStr[i].checked){
				checkboxValue.push(checkboxStr[i].value);
				checkboxText.push(checkboxStr[i].nextSibling.nodeValue);
			}
		}	
		
		checkboxValue=checkboxValue.toString();
		checkboxText=checkboxText.toString();
		window.location="manage.action?checkboxValue="+checkboxValue+"&checkboxText="+checkboxText;
		alert("复制成功");
	}

	
	function rename(){
	var rename = prompt("请输入你想要更改的名称", ""); //将输入的内容赋给变量 name
	var checkboxValue = new Array();
		var checkboxText = new Array();
		var checkboxStr = document.getElementById("lalalala");
		checkboxStr = checkboxStr.toString();
		for(var i = 0; i<checkboxStr.length;i++){
			if(checkboxStr[i].checked){
				alert(checkboxStr[i].value);
				checkboxValue.push(checkboxStr[i].value);
				checkboxText.push(checkboxStr[i].nextSibling.nodeValue);
			}
		}	
		alert("复制成功");
		checkboxValue=checkboxValue.toString();
		checkboxText=checkboxText.toString();
		if(rename){
		alert("更改成功");
			window.location="manage.action?whattodo=rename&changename="+rename+"&checkboxValue="+checkboxValue+"&checkboxText="+checkboxText;
			alert("更改成功");
		}else{
			alert("更改失败");
		}
	}
	</script>

  </head>
  
  <body>
  <div class="gtco-loader"></div>
	
	<div id="page">

	
	<div class="page-inner">
	<nav class="gtco-nav" role="navigation">
	  <div class="gtco-container">
			
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<div id="gtco-logo"><a href="index.jsp">盘 <em>.</em></a></div>
				</div>
				<div class="col-xs-8 text-right menu-1">
					<a href="info.jsp"><img src="${loginUser.picurl}" width="50" height="50"/><font color="#DFDEDE">用户：<label>${loginUser.username}</label></font></a>
	  			</div>
			</div>
			
		</div>
	</nav>
	
		
	<header id="gtco-header" class="gtco-cover" role="banner" style="background-color:grey">
		<div class="overlay1"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
					<div class="row row-mt-15em">
							<!-- <input style="position: absolute; top: 96px; left: 1020px;" type="button" onclick=javascrtpt:jump()>
							<form action="upload.action" method="post" enctype="multipart/form-data" >  
        					<input type="file" name="file" width="120px">  
       							 <input type="submit" value="上传" >  
   									 </form> -->
								<div class="form-wrap">
								<div style="overflow-y:auto;  width:400px; height:150px;" class="tab">

								<form name="userForm2" action="upload2.action" enctype="multipart/form-data" method="post">
 									<div id="newUpload2">
									<input class="btn btn-default" type="file" name="file">
									</div>
									<input class="btn btn-default" type="button" id="btn_add2" value="增加一个新文件" >
									<input class="btn btn-default" type="submit" value="上传" >	
 									</form> 

								</div>
								</div>
							 <b>${shareMsg }</b> 
							<!--  <input style="position: absolute; top: 96px; left: 1020px;" type="button" value="返回登录页面" onclick=javascrtpt:void();>-->
							<div class="form-wrap">
								<div class="tab">
									<ul class="tab-menu1">
										<li class=" gtco-first"><a class="tab-control" href="sortFile.action?sort=all" data-tab="allfile">全部文件</a></li>
										<li class=" gtco-second"><a class="tab-control"  href="sortFile.action?sort=doc" data-tab="doc" style="z-index:99;">文档</a></li>
										<li class=" gtco-second"><a class="tab-control"  href="sortFile.action?sort=media" data-tab="media">媒体</a></li>
										<li class=" gtco-second"><a class="tab-control"  href="sortFile.action?sort=recycle" data-tab="rev">回收站</a></li>
										<li class=" gtco-second"><a class="tab-control"  href="sortFile.action?sort=myShare" data-tab="share">我的分享</a></li>
									</ul>
									
									<div class="tab-content1">
										<div class="tab-pane active" data-content="allfile">
											<form action="batchDownload.action" name="mainArea" method="get">
												<div class="row form-group">
													<div class="col-md-12">
													<a class="btn btn-default" href="manage.action?whattodo=mkdir">新建文件夹</a>
													&nbsp&nbsp
													<a class="btn btn-default" href="javascript:void(0);"  onclick="gcb123456()">复制</a> <!--"manage.action?whattodo=copy"manage.action?whattodo=rename  -->
													&nbsp&nbsp
													<a class="btn btn-default" href="manage.action?whattodo=paste">粘贴</a>
													&nbsp&nbsp
													<a class="btn btn-default" href="manage.action?whattodo=delete">删除</a>
													<br/>
													
													<label style="color:white;">路径：</label>
													<c:forEach var="path" items="${webpathList }" varStatus="status">
													<a href="path.action?wantto=${status.count}">${path}</a>&nbsp\
													</c:forEach>
													<br/>

													<c:forEach var="file" items="${fileList}">

													<nobr><li id="wenjian" class="contextMenu">
													<input type="checkbox" id="lalalala" name="id" value="${file.fileId}"/>	
													&nbsp
													<c:if test="${file.subfix =='folder' }">
											<label><a href="path.action?filename=${file.filename}&nowpath=${file.webpath}${file.filename}/" style="color:white;">${file.filename}</a></label>		
													</c:if>
													<c:if test="${file.subfix !='folder' }">
											<label><a href="download.action?id=${file.fileId}" style="color:white;">${file.filename}</a></label>		
													</c:if>
													&nbsp&nbsp
    												<a class="btn btn-default" href="download.action?id=${file.fileId}" style="color:black;">下载</a>
    												&nbsp&nbsp
    												<a class="btn btn-default" href = "linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" style="color:black;">二维码分享</a> 
    												&nbsp&nbsp
 													<a class="btn btn-default" href="linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" style="color:black;">网页分享</a>
 													&nbsp&nbsp
 													<a class="btn btn-default" href="manage.action?whattodo=delete&value=${file.fileId}" style="color:black;">删除</a>
 													&nbsp&nbsp
 													<label style="color:white;">${file.updatetime}</label>
       										 		<br/>
													</li>
													</nobr>
    												</c:forEach>
														<!-- ...................... -->
														<!--<input type="submit" value="下载"/>-->
														<button class="btn btn-default" onclick="batchDownload()">批量下载</button><br/>
														<label class="btn btn-default" style="color:green;">要分享给的用户是：</label><input type="text" name="username" style="color:black;" value="username"/>
														<button class="btn btn-default" onclick="share()">分享给ta</button>
													</div>
												</div>
											</form>	
										</div>

										<div class="tab-pane " data-content="doc">
											<form action="sortFile.action?sort=doc">
												<c:forEach var="file" items="${fileList}">
													<li id="wenjian" class="contextMenu">
													<input type="checkbox" name="id" value="${file.fileId}"/>	
													<c:if test="${file.subfix =='folder' }">
											<label><a href="pathmanage.action?nowpath=${file.webpath}${file.filename}/" style="color:white;">${file.filename}</a></label>		
													</c:if>
													
													<c:if test="${file.subfix !='folder' }">
											<label><a href="preview.action?fileid=${file.fileId}" style="color:white;">${file.filename}</a></label>		
													</c:if>
														<!-- ..................... -->
    												<a href="download.action?id=${file.fileId}" style="color:white;">下载</a>
    												<a href = "linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" >二维码分享</a> 
 													<a href="linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" style="color:white;">网页分享</a>
 													<label style="color:white;">${file.updatetime}</label>
       										 		<br/>
       										 		</li>
    												</c:forEach>
														<!-- ...................... -->
														<input type="submit" value="下载"/>
											</form>	
										</div>

										<div class="tab-pane " data-content="media">
											<form action="sortFile.action?sort=meida">
												<c:forEach var="file" items="${fileList}">
													<li id="wenjian" class="contextMenu">
													<input type="checkbox" name="id" value="${file.fileId}"/>	
													<c:if test="${file.subfix =='folder' }">
											<label><a href="pathmanage.action?nowpath=${file.webpath}${file.filename}/" style="color:white;">${file.filename}</a></label>		
													</c:if>
													
													<c:if test="${file.subfix !='folder' }">
											<label><a href="preview.action?fileid=${file.fileId}" style="color:white;">${file.filename}</a></label>		
													</c:if>
														<!-- ..................... -->
    												<a href="download.action?id=${file.fileId}" style="color:white;">下载</a>
    												<a href = "linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" >二维码分享</a> 
 													<a href="linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" style="color:white;">网页分享</a>
 													<label style="color:white;">${file.updatetime}</label>
       										 		<br/>
       										 		</li>
    												</c:forEach>
														<!-- ...................... -->
														<input type="submit" value="下载"/>
											</form>	
										</div>
										<div class="tab-pane " data-content="rev">
											<form action="sortFile.action?sort=recycle">
												<c:forEach var="file" items="${fileList}">
													<li id="wenjian" class="contextMenu">
													<input type="checkbox" name="id" value="${file.fileId}"/>	
													<c:if test="${file.subfix =='folder' }">
											<label><a href="pathmanage.action?nowpath=${file.webpath}${file.filename}/" style="color:white;">${file.filename}</a></label>		
													</c:if>
													
													<c:if test="${file.subfix !='folder' }">
											<label><a href="preview.action?fileid=${file.fileId}" style="color:white;">${file.filename}</a></label>		
													</c:if>
														<!-- ..................... -->
    												<a href="download.action?id=${file.fileId}" style="color:white;">下载</a>
    												<a href = "linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" >二维码分享</a> 
 													<a href="linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" style="color:white;">网页分享</a>
 													<label style="color:white;">${file.updatetime}</label>
       										 		<br/>
       										 		</li>
    												</c:forEach>
														<!-- ...................... -->
														<input type="submit" value="下载"/>
											</form>	
										</div>
										<div class="tab-pane " data-content="share">
											<form action="sortFile.action?sort=myShare">
												<c:forEach var="file" items="${fileList}">
													<li id="wenjian" class="contextMenu">
													<input type="checkbox" name="id" value="${file.fileId}"/>	
													<c:if test="${file.subfix =='folder' }">
											<label><a href="pathmanage.action?nowpath=${file.webpath}${file.filename}/" style="color:white;">${file.filename}</a></label>		
													</c:if>
													
													<c:if test="${file.subfix !='folder' }">
											<label><a href="preview.action?fileid=${file.fileId}" style="color:white;">${file.filename}</a></label>		
													</c:if>
														<!-- ..................... -->
    												<a href="download.action?id=${file.fileId}" style="color:white;">下载</a>
    												<a href = "linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" >二维码分享</a> 
 													<a href="linkShare.action?QRlink=http://localhost:8080/span/download.action?id=${file.fileId}" style="color:white;">网页分享</a>
 													<label style="color:white;">${file.updatetime}</label>
       										 		
       										 		</li>
    												</c:forEach>
    												 <c:forEach var="shareTo" items="${shareTo}">
 													<label style="color:white;">${shareTo.username}</label>
 													<br/>
 													
 		       										 </c:forEach>

														<!-- ...................... -->
														<input type="submit" value="下载"/>
											</form>	
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
			</header>
		
		

		
		

	</div>
	</div>
		
		

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script> 
  
  

  

  </body>

</html>
