package com.cisdijob.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recruitment {
	private String RECGUID;//guid
	private String RECNAME;//场次名
	private String RECSTATUS;//状态
	private String RECSTARTTIME;//启动时间
	private String RECENDTIME;//结束时间
	private String REPLACE;//地点
	private String CREATEPERSON;//创建人
	private String CREATETIME;//创建时间
	private String UPDATEPERSON;//更新人
	private String UPDATETIME;//更新时间
	private String RECIMAGE;//图片
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public String getRECGUID() {
		return RECGUID;
	}
	public void setRECGUID(String rECGUID) {
		RECGUID = rECGUID;
	}
	public String getRECNAME() {
		return RECNAME;
	}
	public void setRECNAME(String rECNAME) {
		RECNAME = rECNAME;
	}
	public String getRECSTATUS() {
		return RECSTATUS;
	}
	public void setRECSTATUS(String rECSTATUS) {
		RECSTATUS = rECSTATUS;
	}
	public String getRECSTARTTIME() {
		return RECSTARTTIME;
	}
	public void setRECSTARTTIME(Date rECSTARTTIME) {
		RECSTARTTIME = simpleDateFormat.format(rECSTARTTIME);
	}
	public String getRECENDTIME() {
		return RECENDTIME;
	}
	public void setRECENDTIME(Date rECENDTIME) {
		RECENDTIME = simpleDateFormat.format(rECENDTIME);
	}
	public String getREPLACE() {
		return REPLACE;
	}
	public void setREPLACE(String rEPLACE) {
		REPLACE = rEPLACE;
	}
	public String getCREATEPERSON() {
		return CREATEPERSON;
	}
	public void setCREATEPERSON(String cREATEPERSON) {
		CREATEPERSON = cREATEPERSON;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = simpleDateFormat.format(cREATETIME);
	}
	public String getUPDATEPERSON() {
		return UPDATEPERSON;
	}
	public void setUPDATEPERSON(String uPDATEPERSON) {
		UPDATEPERSON = uPDATEPERSON;
	}
	public String getUPDATETIME() {
		return UPDATETIME;
	}
	public void setUPDATETIME(Date uPDATETIME) {
		UPDATETIME = simpleDateFormat.format(uPDATETIME);
	}
	public String getRECIMAGE() {
		return RECIMAGE;
	}
	public void setRECIMAGE(String rECIMAGE) {
		RECIMAGE = rECIMAGE;
	}
}
