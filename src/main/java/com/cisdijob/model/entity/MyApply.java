package com.cisdijob.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyApply extends Job{
	private String MYGUID;
	private String jobguid;
	private String openid;
	private String status;
	private String type;
	private String createtime;
	private String updateTime;
	private String jrGuid;
	public String getJrGuid() {
		return jrGuid;
	}
	public void setJrGuid(String jrGuid) {
		this.jrGuid = jrGuid;
	}
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
	public String getMYGUID() {
		return MYGUID;
	}
	public void setMYGUID(String mYGUID) {
		MYGUID = mYGUID;
	}
	public String getJobguid() {
		return jobguid;
	}
	public void setJobguid(String jobguid) {
		this.jobguid = jobguid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date date) {
		this.createtime = simpleDateFormat.format(date);
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date date) {
		this.updateTime = simpleDateFormat.format(date);
	}

}
