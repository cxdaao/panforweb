package com.bkd.edu.service;

import java.util.List;

import com.bkd.edu.model.File;
import com.bkd.edu.model.User;

/**
 * 文件服务类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
public interface FileService {
	//文件上传
	public int uploadFile(File file);
	
	//文件下载
	public int downloadFile(File file);
	
	//通过uid查询文件
	public List<File> findFileByUid(Integer uid);
	
	//通过用户名查询文件
	public List<File> findFileByUsername(User user);
	
	//通过文件名查询文件
	public List<File> findFileByFilename(File file);
	
	//通过文件id查询文件
	public File findFileByFileid(Integer fileid);
	
	//通过文件状态查询文件
	public List<File> findFileBystatus(Integer status);
	
	//通过文件后缀查询文件
	public List<File> findFileBySub(String Sub);
	
	//通过网页路径查询文件
	public List<File> findFileByWebPath(String webPath);
	
	//通过服务器路径查询文件
	public List<File> findFileByRealPath(String realPath);
	
	//新添文件项
	public int insertFile(File file);
	
	//通过fid更新文件
	public int updateFileByFileid(Integer fid);
	
	//通过fid删除文件
	public int deleteFileByFileid(Integer fid);
	
  	public List<File> findFileByUidAndWebpath(File file);

	public List<File> findDocFileByUid(File file);

	List<File> findMediaFileByUid(File file);

	List<File> findPicFileByUid(File file);

	public List<com.bkd.edu.model.File> findTrashFileByUid(
			com.bkd.edu.model.File file);

	public int countfolder(com.bkd.edu.model.File file);

	public int pasteFile(com.bkd.edu.model.File file);
	
	int updateByPrimaryKey(File record);

}
