package com.bkd.edu.model;

import java.sql.Timestamp;

public class Share {
    private Integer shareId;

    private Integer userId;

    private Integer recuserId;

    private Integer fileId;

    private Timestamp sharetime;

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRecuserId() {
        return recuserId;
    }

    public void setRecuserId(Integer recuserId) {
        this.recuserId = recuserId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

	public Timestamp getSharetime() {
		return sharetime;
	}

	public void setSharetime(Timestamp sharetime) {
		this.sharetime = sharetime;
	}

    
}