package com.bkd.edu.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bkd.edu.model.User;
import com.bkd.edu.service.UserService;
/**
 * 用户控制类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;//用户的服务类
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@RequestMapping("/toRegister.action")
	public String register(HttpServletResponse response,HttpServletRequest request,HttpSession session,User user) throws Exception{
		//注册的逻辑流程
		
		List<User> userList = userService.findAllUsers();
		
		for(Iterator<User> iterator = userList.iterator();iterator.hasNext();){
			User checkuser = (User)iterator.next();
			if(user.getUsername().equals(checkuser.getUsername())){
				session.setAttribute("loginmsg", "用户名相同");
				return "login";
			}
		}

		user.setLevel(1);
		//user.setEmail(user.getEmail());
		user.setPicurl("1");
		user.setSign("1");
		user.setStatus(1);
		userService.insertUser(user);
		System.out.println(user.getUserId()); 
		return "login";
		
	}
	@RequestMapping("/toChangeInfo.action")
	public String toChangeInfo(HttpServletResponse response,HttpServletRequest request,HttpSession session,User user, String change, MultipartFile pic,String email,String originpassword,String nowpassword) throws Exception{
		User luser = (User)session.getAttribute("loginUser");
		if("picurl".equals(change)){
			String fileName = pic.getOriginalFilename();
			String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
			if("".equals(fileExtName)){
				request.setAttribute("loginUser", luser);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('上传图片为空');</script>");
				//response.getWriter().write("<script>alert('网页将跳转到test.do'); window.location='test.do' ;window.close();</script>");
				response.getWriter().flush();
				return "main";
			}else{
				String path=request.getSession().getServletContext().getRealPath("/images");
				fileName = luser.getUsername() + "_" + "head" + "." +fileExtName;
				File dir=new File(path,fileName);
				if(!dir.exists()){
					dir.mkdirs();
				}
				pic.transferTo(dir);
				luser.setPicurl("images" + "\\" +fileName);
				userService.updateUserPic(luser);
				System.out.println("ok!");
			}

		}else if("email".equals(change)){
			if(email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
				luser.setEmail(email);
				userService.updateByPrimaryKeySelective(luser);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('邮箱已更改');</script>");
				//response.getWriter().write("<script>alert('网页将跳转到test.do'); window.location='test.do' ;window.close();</script>");
				response.getWriter().flush();
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('不符合格式');</script>");
				request.setAttribute("loginUser", luser);
				response.getWriter().flush();
				return "main";
			}
			
			
			
		}else if("password".equals(change)){
			User tempUser = new User();
			tempUser.setPassword(originpassword);
			tempUser.setUsername(luser.getUsername());
			User isUser = userService.findUserByUsernameAndPassword(tempUser);
			if(isUser == null){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('这tm改不了');</script>");
				response.getWriter().flush();
			}else{
				luser.setPassword(nowpassword);
				userService.updateByPrimaryKeySelective(luser);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('改了');</script>");
				response.getWriter().flush();
			}
		}
		request.setAttribute("loginUser", luser);
		return "main";
	
	}
	@RequestMapping("/findPasswordBack.action")
	public String findPasswordBack(HttpServletResponse response,HttpServletRequest request,HttpSession session,User user) throws Exception{
		//注册的逻辑流程
		User tmpUser = userService.findUserByUsernameAndEmail(user);
		if(tmpUser == null){
			//用户信息有误，无法找回密码
			response.getWriter().write("0");
		}else{
			//查找有此用户，开始找回密码，生成新密码，更新数据库，邮件通知用户
			//1:生成新密码 生成六位随机数
			Random random=new Random();
			String newPassword="";
			for(int i=0;i<6;i++) {
				int temp=random.nextInt(10);
				newPassword+=temp;
			}
			//六位新密码生成了！
			tmpUser.setPassword(newPassword);
			//数据库更新完了!
			userService.updatePasswordByEmail(tmpUser);
			//邮件通知用户
			//1：创建一个邮件
			SimpleMailMessage mail=new SimpleMailMessage();
			//给邮件设置title  body
			mail.setSubject("欢迎使用密码找回功能(span.edu.bkd.com)");
			mail.setText("您的新密码为  "+newPassword+"  ,请妥善保管您的密码！"+ "\r\n请您登陆后立即更改密码！");
			mail.setFrom("cxdaao@sina.com");
			mail.setTo(tmpUser.getEmail());
			mailSender.send(mail);
			//发完之后返回一个标识给ajax回调函数
			response.getWriter().write("1");
		}
		return "login";
	}
}
