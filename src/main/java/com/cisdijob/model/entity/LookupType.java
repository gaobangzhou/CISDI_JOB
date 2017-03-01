package com.cisdijob.model.entity;

import java.util.Date;

public class LookupType {
	private String lpt_type;
	private String lpt_decription;
	private Date lpt_createtime;
	private String lpt_createperson;	
	public String getLpt_type() {
		return lpt_type;
	}
	public void setLpt_type(String lpt_type) {
		this.lpt_type = lpt_type;
	}
	public String getLpt_decription() {
		return lpt_decription;
	}
	public void setLpt_decription(String lpt_decription) {
		this.lpt_decription = lpt_decription;
	}
	public Date getLpt_createtime() {
		return lpt_createtime;
	}
	public void setLpt_createtime(Date lpt_createtime) {
		this.lpt_createtime = lpt_createtime;
	}
	public String getLpt_createperson() {
		return lpt_createperson;
	}
	public void setLpt_createperson(String lpt_createperson) {
		this.lpt_createperson = lpt_createperson;
	}

}
