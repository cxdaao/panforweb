package com.bkd.edu.service;

import java.util.Date;
import java.util.List;

import com.bkd.edu.model.Share;

/**
 * 分享服务类
 * @author CXDAAO丶
 * span
 * @vision
 */
public interface ShareService {
	//通过用户id查询分享条目
	public List<Share> findShareByUid(Integer uid);
	
	//通过文件id查询分享条目
	public List<Share> findShareByFid(Integer fid);
	
	//通过分享表id查询分享条目
	public List<Share> findShareBySid(Integer sid);
	
	//通过修改时间查询分享条目
	public List<Share> findShareByDateAndTime(Date dt);
	
	public List<Share> findShareByRevid(Integer revid);
	
	//通过sid删除分享条目
	public int deleteShareBySid(Integer sid);
	
	//通过sid更新分享条目
	public int updateShareBySid(Integer sid);
	
	//插入分享条目
	public int insertShare(Share share);

	public List<Share> findShareFileIdByUid(Integer userId);
		
}
