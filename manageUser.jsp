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
	<link rel="stylesheet" href="css/animate.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">
		<script src="js/modernizr-2.6.2.min.js"></script>	
    <base href="<%=basePath%>">
    
    <title>My JSP 'manageUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div class="gtco-loader"></div>
	
	<div id="page">

	
	<div class="page-inner">
	<nav class="gtco-nav" role="navigation">
	  <div class="gtco-container">
			
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<div id="gtco-logo"><a href="index.html">骚猪盘 <em>.</em></a></div>
				</div>
				<div class="col-xs-8 text-right menu-1">
					<label><font color="#F5F5F5">欢迎你${LoginUser.username}  </font></label>
				</div>
			</div>
			
		</div>
	</nav>
	
		
	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image: url(images/2.png)">
		<div class="overlay1"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
					<div class="row row-mt-15em">
							<div class="form-wrap">
								<div class="tab">
									<form action="manageUser.action">
									<input type="submit" name="act" value="freezeUser"/>
									<input style="position:absolute; top: 40px; left: 0; " type="submit" name="act" value="deleteUser"/>
									<input style="position:absolute; top: 80px; left: 0; " type="submit" name="act" value="unfreezeUser"/>
									<div class="tab-content1">
										<div class="tab-pane active" data-content="allfile">
											
												<div class="row form-group">
												<table  width="400" >
													<thead>
													
													<tr>    
														<th style="color:white;">用户名    </th>
														<th style="color:white;">用户id</th>
														<th style="color:white;">用户状态</th>
													</tr>
													
													</thead>
													<c:forEach var="nuser" items="${normalUserList}">
													<tbody>
													<tr>
													<td style="color:white;"><input type="checkbox" name="username" value="${nuser.username }">${nuser.username }</td>
	
													<td style="color:white;">${nuser.userId }</td>
													<c:if test="${nuser.status ==0}"><td style="color:red;">冻结</td></c:if>
													<c:if test="${nuser.status ==1}"><td style="color:white;">正常</td></c:if>
													</tr>
													</tbody>
													
													</c:forEach>
													</table>
												</div>
												
										</div>

									</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
			</header>
		
		

		
		
<footer id="gtco-footer" role="contentinfo">
		<div class="gtco-container">
			<div class="row row-p	b-md">

				<div class="col-md-4">
					<div class="gtco-widget">
						<h3>About <span class="footer-logo">Splash<span>.<span></span></h3>
						<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempore eos molestias quod sint ipsum possimus temporibus officia iste perspiciatis consectetur in fugiat repudiandae cum. Totam cupiditate nostrum ut neque ab?</p>
					</div>
				</div>

				<div class="col-md-4 col-md-push-1">
					<div class="gtco-widget">
						<h3>Links</h3>
						<ul class="gtco-footer-links">
							<li><a href="#">Knowledge Base</a></li>
							<li><a href="#">Career</a></li>
							<li><a href="#">Press</a></li>
							<li><a href="#">Terms of services</a></li>
							<li><a href="#">Privacy Policy</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-4">
					<div class="gtco-widget">
						<h3>Get In Touch</h3>
						<ul class="gtco-quick-contact">
							<li><a href="#"><i class="icon-phone"></i> 4008-823-823</a></li>
							<li><a href="#"><i class="icon-mail2"></i> info@</a></li>
							<li><a href="#"><i class="icon-chat"></i> Live Chat</a></li>
						</ul>
					</div>
				</div>

			</div>
</div>
	</footer>
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
