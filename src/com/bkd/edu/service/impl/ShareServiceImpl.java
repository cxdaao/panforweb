package com.bkd.edu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkd.edu.mapper.ShareMapper;
import com.bkd.edu.model.Share;
import com.bkd.edu.service.ShareService;
/**
 * 分享服务类实现类
 * @author CXDAAO丶
 * span
 * @vision 0.1
 */
@Service
public class ShareServiceImpl implements ShareService{

	@Autowired
	private ShareMapper shareMapper;//分享表的映射类
	
	@Override
	public List<Share> findShareByUid(Integer uid) {
		// TODO Auto-generated method stub
		return shareMapper.findShareByUid(uid);
	}

	@Override
	public List<Share> findShareByFid(Integer fid) {
		// TODO Auto-generated method stub
		return shareMapper.findShareByFid(fid);
	}

	@Override
	public List<Share> findShareBySid(Integer sid) {
		// TODO Auto-generated method stub
		return shareMapper.findShareBySid(sid);
	}

	@Override
	public List<Share> findShareByDateAndTime(Date dt) {
		// TODO Auto-generated method stub
		return shareMapper.findShareByDateAndTime(dt);
	}

	@Override
	public int deleteShareBySid(Integer sid) {
		// TODO Auto-generated method stub
		return shareMapper.deleteShareBySid(sid);
	}

	@Override
	public int updateShareBySid(Integer sid) {
		// TODO Auto-generated method stub
		return shareMapper.updateShareBySid(sid);
	}

	@Override
	public int insertShare(Share share) {
		// TODO Auto-generated method stub
		return shareMapper.insertShare(share);
	}

	@Override
	public List<Share> findShareByRevid(Integer revid) {
		// TODO Auto-generated method stub
		return shareMapper.findShareByRevid(revid);
	}

	@Override
	public List<Share> findShareFileIdByUid(Integer userId) {
		// TODO Auto-generated method stub
		return shareMapper.findShareFileIdByUid(userId);
	}

}
