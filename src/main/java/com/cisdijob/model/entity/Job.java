package com.cisdijob.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Job {
	private String JGUID;//guid
	private String JNAME;//名字
	private String JTYPE;//类型
	private String JPLACE;//地点
	private String JPAYMENT;//工资待遇
	private String JSTATUS;//状态（used,unused）
	private String JCREATE_COM;//招聘公司
	private String JCREATE_TIME;//创建时间
	private String JCREATE_PERSON;//创建人
	private String JUPDATEPERSOW;//跟新人
	private Date JUPDATETIME;//更新时间
	private String JDESCRIPTION;//工作描述
	private String JCAPACITY;//职位要求
	private String JPROJECT;//专业
	private String JRECRUITMENT_TYPE;//招聘类型
	private String JEDUCATION;//学历要求
	private int JPRIORITY;//优先级
	private String jrguid;//job and recruitment guid
	public String getJrguid() {
		return jrguid;
	}
	public void setJrguid(String jrguid) {
		this.jrguid = jrguid;
	}
	public String getJEDUCATION() {
		return JEDUCATION;
	}
	public void setJEDUCATION(String jEDUCATION) {
		JEDUCATION = jEDUCATION;
	}
	public int getJPRIORITY() {
		return JPRIORITY;
	}
	public void setJPRIORITY(int jPRIORITY) {
		JPRIORITY = jPRIORITY;
	}
	//日期类型转换
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
	public String getJRECRUITMENT_TYPE() {
		return JRECRUITMENT_TYPE;
	}
	public void setJRECRUITMENT_TYPE(String jRECRUITMENT_TYPE) {
		JRECRUITMENT_TYPE = jRECRUITMENT_TYPE;
	}
	public String getJGUID() {
		return JGUID;
	}
	public void setJGUID(String jGUID) {
		JGUID = jGUID;
	}
	public String getJNAME() {
		return JNAME;
	}
	public void setJNAME(String jNAME) {
		JNAME = jNAME;
	}
	public String getJTYPE() {
		return JTYPE;
	}
	public void setJTYPE(String jTYPE) {
		JTYPE = jTYPE;
	}
	public String getJPLACE() {
		return JPLACE;
	}
	public void setJPLACE(String jPLACE) {
		JPLACE = jPLACE;
	}
	public String getJPAYMENT() {
		return JPAYMENT;
	}
	public void setJPAYMENT(String jPAYMENT) {
		JPAYMENT = jPAYMENT;
	}
	public String getJSTATUS() {
		return JSTATUS;
	}
	public void setJSTATUS(String jSTATUS) {
		JSTATUS = jSTATUS;
	}
	public String getJCREATE_COM() {
		return JCREATE_COM;
	}
	public void setJCREATE_COM(String jCREATE_COM) {
		JCREATE_COM = jCREATE_COM;
	}
	public String getJCREATE_TIME() {
		return JCREATE_TIME;
	}
	public void setJCREATE_TIME(Date jCREATE_TIME) {
		JCREATE_TIME = simpleDateFormat.format(jCREATE_TIME);
	}
	public String getJCREATE_PERSON() {
		return JCREATE_PERSON;
	}
	public void setJCREATE_PERSON(String jCREATE_PERSON) {
		JCREATE_PERSON = jCREATE_PERSON;
	}
	public String getJUPDATEPERSOW() {
		return JUPDATEPERSOW;
	}
	public void setJUPDATEPERSOW(String jUPDATEPERSOW) {
		JUPDATEPERSOW = jUPDATEPERSOW;
	}
	public Date getJUPDATETIME() {
		return JUPDATETIME;
	}
	public void setJUPDATETIME(Date jUPDATETIME) {
		JUPDATETIME = jUPDATETIME;
	}
	public String getJDESCRIPTION() {
		return JDESCRIPTION;
	}
	public void setJDESCRIPTION(String jDESCRIPTION) {
		JDESCRIPTION = jDESCRIPTION;
	}
	public String getJCAPACITY() {
		return JCAPACITY;
	}
	public void setJCAPACITY(String jCAPACITY) {
		JCAPACITY = jCAPACITY;
	}
	public String getJPROJECT() {
		return JPROJECT;
	}
	public void setJPROJECT(String jPROJECT) {
		JPROJECT = jPROJECT;
	}
}
