package com.bkd.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkd.edu.mapper.UserMapper;
import com.bkd.edu.model.User;
import com.bkd.edu.service.UserService;
/**
 * 用户服务类的实现类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;//用户表的映射类
	
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userMapper.findAllUsers();
	}

	@Override
	public List<User> findAllUsersExceptAdmin() {
		// TODO Auto-generated method stub
		return userMapper.findAllUsersExceptAdmin();
	}

	@Override
	public User findUserByUsernameAndPassword(User user) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUsernameAndPassword(user);
	}

	@Override
	public User findUserByUsername(User user) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUsername(user);
	}

	@Override
	public User findUsersByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userMapper.findUsersByUid(uid);
	}

	@Override
	public long insertUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(user);
	}

	@Override
	public int updateUserByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userMapper.updateUserByUid(uid);
	}

	@Override
	public int deleteUserByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userMapper.deleteUserByUid(uid);
	}

	@Override
	public int freezeUserByUserid(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.freezeUserByUserid(userId);
	}

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int unfreezeUserByUserid(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.unfreezeUserByUserid(userId);
	}

	@Override
	public User findUserByUsernameAndEmail(User user) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUsernameAndEmail(user);
	}

	@Override
	public int updatePasswordByEmail(User tmpUser) {
		// TODO Auto-generated method stub
		return userMapper.updatePasswordByEmail(tmpUser);
	}

	@Override
	public int updateUserPic(User luser) {
		// TODO Auto-generated method stub
		return userMapper.updateUserPic(luser);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

}
