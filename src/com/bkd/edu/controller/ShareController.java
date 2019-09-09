package com.bkd.edu.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkd.edu.model.Share;
import com.bkd.edu.model.User;
import com.bkd.edu.service.ShareService;
import com.bkd.edu.service.UserService;
import com.bkd.edu.util.DateTransform;
import com.bkd.edu.util.MakeQRCode;
/**
 * 分享的控制类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
@Controller
public class ShareController {
@Autowired
private ShareService shareService;//文件分享的服务类

@Autowired
private UserService userService;//文件分享的服务类

@RequestMapping("/normalShare.action")
public String nshare(HttpServletResponse response,HttpServletRequest request,HttpSession session, User user, int[] id) throws Exception{
	//普通分享的的流程
	Share share = new Share();
	String successmsg = "share success!";
	String errormsg = "no";
	User tempUser = (User)session.getAttribute("loginUser");
	User RecUser = userService.findUserByUsername(user);
	if( RecUser != null){
		for(int fileid : id){
			share.setUserId(tempUser.getUserId());
			share.setFileId(fileid);
			share.setRecuserId(RecUser.getUserId());
			share.setSharetime(DateTransform.transDate(new Date()));
			int info = shareService.insertShare(share);
			System.out.println(tempUser.getUsername()+ ".shareProcess:" + info);
		}
		request.setAttribute("shareMsg", successmsg);
	}else{
		request.setAttribute("shareMsg",errormsg);
	}
	return "main";
	
}

@RequestMapping("/linkShare.action")
public String lshare(HttpServletResponse response,HttpServletRequest request,HttpSession session, String QRlink) throws Exception{
	//二维码分享和网页分享的流程
	//二维码生成
	//生成路径为工程下的temp文件夹
	//生成图片为QRcode.png
	//每个普通用户都有一张二维码图片，每次新生成刷新图片

	//文件路径
	//暂时
	User luser = (User) session.getAttribute("loginUser");
	@SuppressWarnings("deprecation")
	String rootPath = request.getRealPath("");
	String path = rootPath + "/images/"+luser.getUsername()+"_QRcode";
	try{
		//工具类生成二维码
		//Qlink为网页传输过来的网址
		//TODO：path固定为每个用户的temp文件夹
		MakeQRCode.getCode(QRlink, path);
	}catch(Exception e){
		//System.out.println(QRlink);
		e.printStackTrace();
	}
	//二维码生成结束
	return "share";
	
}

}
