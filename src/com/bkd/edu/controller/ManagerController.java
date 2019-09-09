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

import com.bkd.edu.model.User;
import com.bkd.edu.service.FileService;
import com.bkd.edu.service.UserService;
/**
 * 管理者的控制类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
@Controller
public class ManagerController {

	@Autowired
	private UserService userService;//用户服务类

	@Autowired
	private FileService fileService;//文件服务类
	
	@RequestMapping("/manageUser.action")
	public String manageUser(HttpServletResponse response,HttpServletRequest request,HttpSession session, String act, String[] username) throws Exception{
		System.out.println(act);
		List<User> userList = new ArrayList<User>();
		User tempuser = new User();
		//userList.add(userService.findUserByUsername(name));
		for(String name : username){
			tempuser.setUsername(name);
			userList.add(userService.findUserByUsername(tempuser));
			//System.out.println(name);
		}
		if(act.equals("freezeUser")){
			for(Iterator<User> iterator = userList.iterator();iterator.hasNext();){
				tempuser = iterator.next();
				userService.freezeUserByUserid(tempuser.getUserId());
			}
			List<User> normalUserList = userService.findAllUsersExceptAdmin();
			session.setAttribute("normalUserList", normalUserList);
		}else if(act.equals("deleteUser")){
			for(Iterator<User> iterator = userList.iterator();iterator.hasNext();){
				tempuser = iterator.next();
				userService.deleteByPrimaryKey(tempuser.getUserId());
			}
			List<User> normalUserList = userService.findAllUsersExceptAdmin();
			session.setAttribute("normalUserList", normalUserList);
		}else{
			for(Iterator<User> iterator = userList.iterator();iterator.hasNext();){
				tempuser = iterator.next();
				userService.unfreezeUserByUserid(tempuser.getUserId());
			}
			List<User> normalUserList = userService.findAllUsersExceptAdmin();
			session.setAttribute("normalUserList", normalUserList);
		}
		return "manageUser";
	}
	
	public void manageResource(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception{
		//文件管理的流程
	}
}
