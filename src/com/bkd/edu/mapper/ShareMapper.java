package com.bkd.edu.mapper;

import java.util.Date;
import java.util.List;

import com.bkd.edu.model.Share;

public interface ShareMapper {
    int deleteByPrimaryKey(Integer shareId);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer shareId);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
    
  	public List<Share> findShareByUid(Integer uid);
  	
  	public List<Share> findShareByFid(Integer fid);
  	
  	public List<Share> findShareByRevid(Integer revid);

  	public List<Share> findShareBySid(Integer sid);
  	
  	public List<Share> findShareByDateAndTime(Date dt);

  	public int deleteShareBySid(Integer sid);

  	public int updateShareBySid(Integer sid);

  	public int insertShare(Share share);

	List<Share> findShareFileIdByUid(Integer userId);
}