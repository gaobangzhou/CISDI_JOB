package com.cisdijob.model.entity;

public class SearchPosition {
	private String recType;
	private String company;
	private String type;
	private String project;
	private String keyWord;
	private String rguid;//场次guid
	public String getRguid() {
		return rguid;
	}
	public void setRguid(String rguid) {
		this.rguid = rguid;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	private String code;
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
