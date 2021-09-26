package com.feigle.bean;

public class APPBean {

	private int id;
	private int newVersion;
	private String apkFileUrl;
	private String updateLog;
	private String targetSize;
	private String createTime;
	private String versionName;
	
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(int newVersion) {
		this.newVersion = newVersion;
	}

	public String getApkFileUrl() {
		return apkFileUrl;
	}

	public void setApkFileUrl(String apkFileUrl) {
		this.apkFileUrl = apkFileUrl;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getTargetSize() {
		return targetSize;
	}

	public void setTargetSize(String targetSize) {
		this.targetSize = targetSize;
	}

	public APPBean() {
		// TODO Auto-generated constructor stub
	}

}
