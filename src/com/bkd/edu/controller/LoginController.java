package com.bkd.edu.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





import com.bkd.edu.model.File;
import com.bkd.edu.model.Share;
import com.bkd.edu.model.User;
import com.bkd.edu.service.FileService;
import com.bkd.edu.service.ShareService;
import com.bkd.edu.service.UserService;

/**
 * 登陆控制类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */

@Controller
public class LoginController{
	/**
	 * 
	 */
	@Autowired
	private UserService userService;//user服务类
	@Autowired
	private ShareService shareService;//user服务类
	@Autowired
	private FileService fileService;//user服务类
	
	@RequestMapping("/toLogin.action")
	public String login(HttpServletResponse response,HttpServletRequest request,HttpSession session,User user) throws Exception{
		//登陆的逻辑流程
		//String success = "yes";
		String error = "no";
		User luser = userService.findUserByUsernameAndPassword(user);
		if(luser != null && luser.getLevel() == 1){
			System.out.println(luser.getUsername()+"请求登陆;");
			List<Share> shareList = shareService.findShareByRevid(luser.getUserId());
			List<File> sharefileList = new ArrayList<File>();
			
			for(Iterator<Share> iterator = shareList.iterator(); iterator.hasNext();){
				Share share = (Share)iterator.next();
				sharefileList.add(fileService.findFileByFileid(share.getFileId()));
				System.out.println(share.toString());
			}
			File file = new File();
			file.setUserId(luser.getUserId());
			file.setWebpath("root/");
			List<File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("fileList",fileList);
			session.setAttribute("shareFileList", sharefileList);
			session.setAttribute("loginUser", luser);
			List<String> pathList = new ArrayList<String>();
			pathList.add("root");
			session.setAttribute("webpathList", pathList);
			session.setAttribute("nowpath", "root/");
			//request.setAttribute("msg", success);
			return "main";
		}else if(luser != null && luser.getLevel() == 2){
			List<User> normalUserList = userService.findAllUsersExceptAdmin();
			session.setAttribute("normalUserList", normalUserList);
			session.setAttribute("loginUser", luser);
			return "manageUser";
		}else{
			request.setAttribute("msg", error);
			return "login";
		}
	}

}
