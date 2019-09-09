package com.bkd.edu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;




import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bkd.edu.model.Share;
import com.bkd.edu.model.User;
import com.bkd.edu.service.FileService;
import com.bkd.edu.service.ShareService;
import com.bkd.edu.service.UserService;
import com.bkd.edu.util.DateTransform;
import com.bkd.edu.util.FileUpload;

/**
 * 文件控制类
 * @author CXDAAO
 * span
 * @vision 0.1
 */
@Controller
public class FileController {
	@Autowired
	//文件服务类
	private FileService fileService;
	
	@Autowired
	//分享服务类
	private ShareService shareService;
	
	@Autowired
	//用户服务类
	private UserService userService;
	
	@RequestMapping(value="upload.action",method=RequestMethod.POST)
	@ResponseBody
	public void upload(MultipartFile file,HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException{
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		String fileName=file.getOriginalFilename();
		String originName = fileName;
		fileName = FileUpload.makeFileName(fileName);
		fileName = "_" + fileName;
		File dir=new File(path,fileName);
		if(!dir.exists()){
			dir.mkdirs();
		}
		User luser = (User)session.getAttribute("loginUser");
		//MultipartFile自带的解析方法
		file.transferTo(dir);
		
		com.bkd.edu.model.File ifile = new com.bkd.edu.model.File();
		ifile.setFilename(originName);
		String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
		ifile.setSubfix(fileExtName);
		ifile.setRecycle(0);
		ifile.setUserId(luser.getUserId());
		ifile.setUpdatetime(DateTransform.transDate(new Date()));
		ifile.setRealpath(request.getSession().getServletContext().getRealPath("\\WEB-INF\\upload")+"\\"+fileName);
		String nowpath = (String)session.getAttribute("nowpath");
		ifile.setWebpath(nowpath);
		switch(fileExtName){
			case"avi":
			case"mov":
			case"mp4":
				ifile.setIcon(1);
				break;
			case"mp3":
			case"wav":
				ifile.setIcon(2);
				break;
			case"jpg":
			case"png":
			case"bmp":
			case"jpeg":
				ifile.setIcon(3);
				break;
			case"doc":
			case"pdf":
			case"txt":
			case"docx":
				ifile.setIcon(4);
				break;
			default:
				ifile.setIcon(0);
				break;
		}
		fileService.insertFile(ifile);
		//return "redirect:/main.jsp";
		List<com.bkd.edu.model.File> fileList = fileService.findFileByUid(luser.getUserId());
		session.setAttribute("fileList",fileList);
		
		response.sendRedirect("main.jsp?nowpath=/");
		}
	
	@RequestMapping(value = "multifileUpload",method =RequestMethod.POST)
	@ResponseBody
	public void batchupload(List<MultipartFile> files,HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException{
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		User luser = (User)session.getAttribute("loginUser");
		
		//MultipartFile自带的解析方法
		for(Iterator<MultipartFile> iterator =  files.iterator();iterator.hasNext();){
			MultipartFile f = (MultipartFile)iterator.next();
			String fileName=f.getOriginalFilename();
			String originName = fileName;
			fileName = FileUpload.makeFileName(fileName);
			fileName = "_" + fileName;
			File dir=new File(path,fileName);
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			f.transferTo(dir);
			
			com.bkd.edu.model.File ifile = new com.bkd.edu.model.File();
			ifile.setFilename(originName);
			String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
			ifile.setSubfix(fileExtName);
			ifile.setRecycle(0);
			ifile.setUserId(luser.getUserId());
			ifile.setUpdatetime(DateTransform.transDate(new Date()));
			ifile.setRealpath(request.getSession().getServletContext().getRealPath("\\WEB-INF\\upload")+"\\"+fileName);
			ifile.setWebpath("/");
			switch(fileExtName){
				case"avi":
				case"mov":
				case"mp4":
					ifile.setIcon(1);
					break;
				case"mp3":
				case"wav":
					ifile.setIcon(2);
					break;
				case"jpg":
				case"png":
				case"bmp":
				case"jpeg":
					ifile.setIcon(3);
					break;
				case"doc":
				case"pdf":
				case"txt":
				case"docx":
					ifile.setIcon(4);
					break;
				default:
					ifile.setIcon(0);
					break;
			}
			fileService.insertFile(ifile);
			//return "redirect:/main.jsp";
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUid(luser.getUserId());
			session.setAttribute("fileList",fileList);
		}
		
		response.sendRedirect("main.jsp?nowpath=/");
		}
	
	@RequestMapping(value = "upload2.action")
	public void upload2(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException {
		//创建一个通用的多部分解析器
		User luser = (User)session.getAttribute("loginUser");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					//取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if(myFileName.trim() !=""){
						System.out.println(myFileName);
						//重命名上传后的文件名
						String fileName = file.getOriginalFilename();
						//定义上传路径
						String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");

						String originName = fileName;
						fileName = FileUpload.makeFileName(fileName);
						fileName = "_" + fileName;
						
						File localFile = new File(path,fileName);
						file.transferTo(localFile);
						com.bkd.edu.model.File ifile = new com.bkd.edu.model.File();
						ifile.setFilename(originName);
						String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
						ifile.setSubfix(fileExtName);
						ifile.setRecycle(0);
						ifile.setUserId(luser.getUserId());
						ifile.setUpdatetime(DateTransform.transDate(new Date()));
						ifile.setRealpath(request.getSession().getServletContext().getRealPath("\\WEB-INF\\upload")+"\\"+fileName);
						ifile.setWebpath("/");
						switch(fileExtName){
							case"avi":
							case"mov":
							case"mp4":
								ifile.setIcon(1);
								break;
							case"mp3":
							case"wav":
								ifile.setIcon(2);
								break;
							case"jpg":
							case"png":
							case"bmp":
							case"jpeg":
								ifile.setIcon(3);
								break;
							case"doc":
							case"pdf":
							case"txt":
							case"docx":
								ifile.setIcon(4);
								break;
							default:
								ifile.setIcon(0);
								break;
						}
						fileService.insertFile(ifile);
						//return "redirect:/main.jsp";
						List<com.bkd.edu.model.File> fileList = fileService.findFileByUid(luser.getUserId());
						session.setAttribute("fileList",fileList);
					}
				}
			}
			
		}
		response.sendRedirect("main.jsp?nowpath=/");
		//return "/success";
	}
	
	@RequestMapping(value = "sortFile.action")
	public String sortFile(HttpServletRequest request, HttpSession session, HttpServletResponse response, String sort) throws IOException{
		User luser = (User)session.getAttribute("loginUser");
		com.bkd.edu.model.File file = new com.bkd.edu.model.File();
		file.setUserId(luser.getUserId());
		file.setWebpath("root/");
		if("all".equals(sort)){
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("fileList", fileList);
			session.setAttribute("nowpath", "root/");
		}else if("doc".equals(sort) || "pic".equals(sort)){
			List<com.bkd.edu.model.File> fileList = fileService.findDocFileByUid(file);
			List<com.bkd.edu.model.File> picList = fileService.findPicFileByUid(file);
			fileList.addAll(picList);
			session.setAttribute("fileList", fileList);
			session.setAttribute("nowpath", "root/");
		}else if("media".equals(sort)){
			List<com.bkd.edu.model.File> fileList = fileService.findMediaFileByUid(file);
			session.setAttribute("fileList", fileList);
			session.setAttribute("nowpath", "root/");
		}else if("recycle".equals(sort)){
			List<com.bkd.edu.model.File> fileList = fileService.findTrashFileByUid(file);
			session.setAttribute("fileList", fileList);
			session.setAttribute("nowpath", "root/");
		}else if("myShare".equals(sort)){
			List<com.bkd.edu.model.File> fileList = new ArrayList<com.bkd.edu.model.File>();
			List<Share> shareFileIdList = shareService.findShareFileIdByUid((int)luser.getUserId());
			User tmpUser = new User();
			List<User> shareTo = new ArrayList<User>();
			for(Iterator<Share> iterator = shareFileIdList.iterator(); iterator.hasNext();){
				Share share = (Share)iterator.next();
				com.bkd.edu.model.File f = fileService.findFileByFileid(share.getFileId());
				tmpUser = userService.findUsersByUid(share.getRecuserId());
				shareTo.add(tmpUser);
				fileList.add(f);
				}
			session.setAttribute("nowpath", "root/");
			session.setAttribute("shareTo", shareTo);
			session.setAttribute("fileList", fileList);
		}
		session.setAttribute("loginUser", luser);
		return "forward:/main.jsp";
		
	}
	@RequestMapping(value = "download.action")
	public ResponseEntity<byte[]> download(HttpServletRequest request,String id) throws IOException {
		String realpath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		int maxSplit = 3;
		com.bkd.edu.model.File f = new com.bkd.edu.model.File();
		f=fileService.findFileByFileid(Integer.parseInt(id));
		String resourceName = f.getFilename();
		String[] sourceStrArray = f.getRealpath().split("_", maxSplit);
		
		File file = new File(realpath+"\\_"+sourceStrArray[1]+"_"+sourceStrArray[2]);
		
		HttpHeaders headers = new HttpHeaders();
		String filename = new String(resourceName.getBytes("utf-8"),"iso-8859-1");
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}

	
	@RequestMapping(value="batchDownload.action")
	public ResponseEntity<byte[]> download2(HttpServletRequest request,String[] id) throws IOException {
		int maxSplit = 3;
		String realpath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		com.bkd.edu.model.File f = new com.bkd.edu.model.File();
		//需要压缩的文件
		List<String> list = new ArrayList<String>();
		for(String fid : id){
			f = fileService.findFileByFileid(Integer.parseInt(fid));
			String[] sourceStrArray = f.getRealpath().split("_", maxSplit);
			String realFileName = "_"+sourceStrArray[1]+"_"+sourceStrArray[2];
			list.add(realFileName);
		}
		//压缩后的文件
		String resourcesName = UUID.randomUUID().toString()+"_"+"download.zip";
		//zip输出流开始
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(realpath+"\\"+resourcesName));
		InputStream input = null;
		try{
	
			for (String str : list) {
				String name = realpath+"\\"+str;
				System.out.println(name);
				input = new FileInputStream(new File(name));  
	            zipOut.putNextEntry(new ZipEntry(str));  
	            int temp = 0;  
	            while((temp = input.read()) != -1){  
	                zipOut.write(temp);  
	            }  
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//zipOut.flush();
			input.close();
			zipOut.close();
			
		}
		
		//zip输出流结束
		File file = new File(realpath+"\\"+resourcesName);
		HttpHeaders headers = new HttpHeaders();
		String filename = new String(resourcesName.getBytes("utf-8"),"iso-8859-1");
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}

	
	@SuppressWarnings("unused")
	@RequestMapping("manage.action")
	public String manage(HttpServletResponse response,HttpServletRequest request,HttpSession session, String whattodo, String nowpath) throws Exception{
		//文件管理的流程
		User luser = (User)session.getAttribute("loginUser");
		//网页传回的文件信息 暂定是id
		//复制得到复选框的信息
		//获得的均为数组值：
		String checboxValues=request.getParameter("checboxValue");
		String checboxTexts=request.getParameter("checboxText");

		//得到每个具体值：
		if(checboxValues!=null && checboxTexts!=null){
			String[] checkboxValue=checboxValues.split(",");
			String[] checkboxText=checboxTexts.split(",");
			session.setAttribute("checkboxValue", checkboxValue);
			session.setAttribute("checkboxText", checkboxText);
		}
		String[] checkboxValue = (String[])session.getAttribute("checkboxValue");
		String[] checkboxText = (String[])session.getAttribute("checkboxText");
		nowpath = (String)session.getAttribute("nowpath");
		if(checkboxValue == null && checkboxText == null && nowpath == null){
			return "main";
		}
		
		//String realpath = tmpFile.getRealpath();
		com.bkd.edu.model.File file = new com.bkd.edu.model.File();
		if("paste".equals(whattodo)){//粘贴
			for(String value :checkboxValue){
				file = fileService.findFileByFileid(Integer.parseInt(value));
				file.setFileId(null);
				file.setUpdatetime(DateTransform.transDate(new Date()));
				file.setWebpath(nowpath);
				fileService.insertFile(file);
				//int info = fileService.updateByPrimaryKey(file);
			}
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("fileList",fileList);
//		}else if("cut".equals(whattodo)){//剪切
//			for(String value :checkboxValue){
//				file = fileService.findFileByFileid(Integer.parseInt(value));
//				int info = fileService.cutFile(file);
//				//fileService.deleteFileByFileid(Integer.parseInt(value));
//				
//			}
//			
//			List<com.bkd.edu.model.File> fileList = fileService.findFileByUid(luser.getUserId());
//			session.setAttribute("fileList",fileList);
		}else if("mkdir".equals(whattodo)){//TODO新建文件夹
			
			//文件夹类型都为9
			file.setIcon(233);
			file.setRealpath("/");
			file.setWebpath(nowpath);
			file.setRecycle(0);
			file.setUserId(luser.getUserId());
			file.setSubfix("folder");
			file.setUpdatetime(DateTransform.transDate(new Date()));
			int folderNum = fileService.countfolder(file);
			folderNum += 1;
			file.setFilename("新建文件夹("+folderNum+")");
			fileService.insertFile(file);
			com.bkd.edu.model.File ifile = new com.bkd.edu.model.File();
			ifile.setUserId(luser.getUserId());
			ifile.setWebpath(nowpath);
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(ifile);
			session.setAttribute("fileList",fileList);
		}else if("delete".equals(whattodo)){//删除
			
			String value = request.getParameter("value");
				file = fileService.findFileByFileid(Integer.parseInt(value));
				if(file.getRecycle() == 0){
					file.setRecycle(1);//进入回收站
					int info = fileService.updateByPrimaryKey(file);
				}else if(file.getRecycle() == 1){
					file.setRecycle(2);//彻底删除
					int info = fileService.updateByPrimaryKey(file);
				}
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("fileList",fileList);
		}else if("rename".equals(whattodo)){
			String filename = request.getParameter("changename");
			for(String value :checkboxValue){
			file = fileService.findFileByFileid(Integer.parseInt(value));
			file.setFilename(filename);
			}
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("fileList",fileList);
		}
		//nowpath = (String)session.getAttribute("nowpath");
		return "main";
	}
	@RequestMapping("path.action")
	public String pathManager(HttpServletResponse response,HttpServletRequest request,HttpSession session, String nowpath, String filename, String wantto) throws Exception{
		//空说明点击的是路径上的要跳转的地址
		//每次登陆都默认是root/路径
		User luser = (User)session.getAttribute("loginUser");
		//session.setAttribute("nowpath", nowpath);
		
		//nowpath = (String)session.getAttribute("nowpath");
		com.bkd.edu.model.File file = new com.bkd.edu.model.File();
		
		
//		//直接跳转该路径
//		if(!"".equals(nowpath) && "".equals(wantto) && "".equals(filename)){
//			System.out.println("method 1");
//			//session.setAttribute("nowpath", nowpath);
//			com.bkd.edu.model.File ifile = new com.bkd.edu.model.File();
//			ifile.setUserId(luser.getUserId());
//			ifile.setWebpath(nowpath);
//			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(ifile);
//			session.setAttribute("fileList",fileList);
//			//现在我所在的路径
//			String[] pathList = nowpath.split("/");
//			System.out.println(nowpath);
//			//分割之后就逐个添加进入list中,在页面使用foreach循环读出
//
//			session.setAttribute("nowpath", nowpath);
//			//现在我所在的路径
//			session.setAttribute("webpathList", pathList);
//			session.setAttribute("loginUser", luser);
//			return "forward:/main.jsp";
		if(nowpath==null && filename==null &&  ! ("".equals(wantto))){
			System.out.println("method 2");
			//这个path是给路径使用的path
			//每次都新建一个先加入根
			//给传回来的地址分割一下
			nowpath = (String)session.getAttribute("nowpath");
			String[] pathList = nowpath.split("/");
			//现在我所在的路径
			//session.setAttribute("webpath", path);
			System.out.println(nowpath);
			//分割之后就逐个添加进入list中,在页面使用foreach循环读出
			int want = Integer.parseInt(wantto);
			//System.out.println(want);
			String path = "";
			for(int i=0; i<want; i++){
				path = path + pathList[i] + "/";
			}
			pathList = path.split("/");
			System.out.println(path);
			session.setAttribute("nowpath", path);
			//现在我所在的路径
			session.setAttribute("webpathList", pathList);
			//查该webpath路径对应的的文件
			file.setUserId(luser.getUserId());
			file.setWebpath(path);
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("loginUser", luser);
			session.setAttribute("fileList",fileList);
		}else if(nowpath==null && filename==null && "".equals(wantto)){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("<script>alert('参数传递有误');</script>");
			//response.getWriter().write("<script>alert('网页将跳转到test.do'); window.location='test.do' ;window.close();</script>");
			response.getWriter().flush();
			session.setAttribute("loginUser", luser);
			session.setAttribute("nowpath", nowpath);
		}else if(!"".equals(nowpath) && !"".equals(filename)){//非空说明点击的是文件夹
			//那么只需要在路径后面加上文件夹名就行
			System.out.println("method 3");
			//nowpath = nowpath + filename + "/";
			file.setUserId(luser.getUserId());
			file.setWebpath(nowpath);
			//之后查出该路径下的所有文件名
			List<com.bkd.edu.model.File> fileList = fileService.findFileByUidAndWebpath(file);
			session.setAttribute("fileList",fileList);
			session.setAttribute("loginUser", luser);
			//session.setAttribute("nowpath", nowpath);
			//每次都新建一个先加入根
			//给传回来的地址分割一下
			String[] pathList = nowpath.split("/");
			System.out.println(nowpath);
			//分割之后就逐个添加进入list中,在页面使用foreach循环读出

			session.setAttribute("nowpath", nowpath);
			//现在我所在的路径
			session.setAttribute("webpathList", pathList);
		}
		
		return "main";
	}
	@RequestMapping("preview.action")
	public void viewfile(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception{
		//文件预览的流程
	}

}
