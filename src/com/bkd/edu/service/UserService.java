package com.bkd.edu.service;

import java.util.List;

import com.bkd.edu.model.User;
/**
 * 用户服务类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
public interface UserService {
	//找出全部的用户（包括普通用户和管理员
	public List<User> findAllUsers();
	
	//找出全部的普通用户（管理界面用
	public List<User> findAllUsersExceptAdmin();
	
	//通过用户名和密码找出用户（登录用
	public User findUserByUsernameAndPassword(User user);
	
	//通过用户名找到用户（查重用，普通分享用，找回密码用
	public User findUserByUsername(User user);
	
	//通过uid找出用户
	public User findUsersByUid(Integer uid);
	
	//添加用户信息
	public long insertUser(User user);
	
	//通过uid修改用户信息
	public int updateUserByUid(Integer uid);
	
	//通过uid删除用户
	public int deleteUserByUid(Integer uid);
	
	//通过uid更改用户状态
	public int freezeUserByUserid(Integer userId);
	
	int deleteByPrimaryKey(Integer userId);

	public int unfreezeUserByUserid(Integer userId);

	public User findUserByUsernameAndEmail(User user);

	public int updatePasswordByEmail(User tmpUser);

	public int updateUserPic(User luser);
	
	int updateByPrimaryKeySelective(User record);
}
