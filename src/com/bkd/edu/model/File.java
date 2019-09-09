package com.bkd.edu.model;

import java.sql.Timestamp;


public class File {
    private Integer fileId;

    private String filename;

    private Integer userId;

    private Integer icon;

    private String subfix;

    private String webpath;

    private String realpath;

    private Integer recycle;

    private Timestamp updatetime;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getSubfix() {
        return subfix;
    }

    public void setSubfix(String subfix) {
        this.subfix = subfix == null ? null : subfix.trim();
    }

    public String getWebpath() {
        return webpath;
    }

    public void setWebpath(String webpath) {
        this.webpath = webpath == null ? null : webpath.trim();
    }

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath == null ? null : realpath.trim();
    }

    public Integer getRecycle() {
        return recycle;
    }

    public void setRecycle(Integer recycle) {
        this.recycle = recycle;
    }

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}


}