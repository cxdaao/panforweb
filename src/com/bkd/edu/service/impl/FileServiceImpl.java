package com.bkd.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkd.edu.mapper.FileMapper;
import com.bkd.edu.model.File;
import com.bkd.edu.model.User;
import com.bkd.edu.service.FileService;

/**
 * 文件服务实现类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileMapper fileMapper;//文件表的映射类
	
	@Override
	public int uploadFile(File file) {
		// TODO Auto-generated method stub
		return fileMapper.uploadFile(file);
	}

	@Override
	public int downloadFile(File file) {
		// TODO Auto-generated method stub
		return fileMapper.downloadFile(file);
	}

	@Override
	public List<File> findFileByUid(Integer uid) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByUid(uid);
	}

	@Override
	public List<File> findFileByUsername(User user) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByUsername(user);
	}

	@Override
	public List<File> findFileByFilename(File file) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByFilename(file);
	}

	@Override
	public File findFileByFileid(Integer fileid) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByFileid(fileid);
	}

	@Override
	public List<File> findFileBystatus(Integer status) {
		// TODO Auto-generated method stub
		return fileMapper.findFileBystatus(status);
	}

	@Override
	public List<File> findFileBySub(String Sub) {
		// TODO Auto-generated method stub
		return fileMapper.findFileBySub(Sub);
	}

	@Override
	public List<File> findFileByWebPath(String webPath) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByWebPath(webPath);
	}

	@Override
	public List<File> findFileByRealPath(String realPath) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByRealPath(realPath);
	}

	@Override
	public int insertFile(File file) {
		// TODO Auto-generated method stub
		return fileMapper.insertFile(file);
	}

	@Override
	public int updateFileByFileid(Integer fid) {
		// TODO Auto-generated method stub
		return fileMapper.updateFileByFileid(fid);
	}

	@Override
	public int deleteFileByFileid(Integer fid) {
		// TODO Auto-generated method stub
		return fileMapper.deleteFileByFileid(fid);
	}

	@Override
	public List<File> findFileByUidAndWebpath(File file) {
		// TODO Auto-generated method stub
		return fileMapper.findFileByUidAndWebpath(file);
	}

	@Override
	public List<File> findDocFileByUid(File file) {
		// TODO Auto-generated method stub
		return fileMapper.findDocFileByUid(file);
	}
	@Override
	public List<File> findPicFileByUid(File file) {
		// TODO Auto-generated method stub
		return fileMapper.findPicFileByUid(file);
	}
	@Override
	public List<File> findMediaFileByUid(File file) {
		// TODO Auto-generated method stub
		return fileMapper.findMediaFileByUid(file);
	}

	@Override
	public List<File> findTrashFileByUid(File file) {
		// TODO Auto-generated method stub
		return fileMapper.findTrashFileByUid(file);
	}

	@Override
	public int countfolder(File file) {
		// TODO Auto-generated method stub
		return fileMapper.countfolder(file);
	}

	@Override
	public int pasteFile(File file) {
		// TODO Auto-generated method stub
		return fileMapper.pasteFile(file);
	}
	
	@Override
	public int updateByPrimaryKey(File record){
		return fileMapper.updateByPrimaryKey(record);
	}


}
