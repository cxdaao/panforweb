package com.bkd.edu.mapper;

import java.util.List;

import com.bkd.edu.model.File;
import com.bkd.edu.model.User;

public interface FileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
    
  	public int uploadFile(File file);
  	
  	public int downloadFile(File file);
  	
  	public List<File> findFileByUid(Integer uid);
  	
  	public List<File> findFileByUsername(User user);
  	
  	public List<File> findFileByFilename(File file);
  	
  	public File findFileByFileid(Integer fileid);
  	
  	public List<File> findFileBystatus(Integer status);
  	
  	public List<File> findFileBySub(String Sub);
  	
  	public List<File> findFileByWebPath(String webPath);
  	
  	public List<File> findFileByRealPath(String realPath);
  	
  	public int insertFile(File file);

  	public int updateFileByFileid(Integer fid);
  	
  	public int deleteFileByFileid(Integer fid);
  	
  	public List<File> findFileByUidAndWebpath(File file);

	List<File> findDocFileByUid(File file);
	
	List<File> findPicFileByUid(File file);
	
	List<File> findMediaFileByUid(File file);

	List<File> findTrashFileByUid(File file);

	int countfolder(File file);

	int pasteFile(File file);

}