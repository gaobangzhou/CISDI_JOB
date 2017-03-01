package com.cisdijob.model.weixin;

public class News {

	private Filter filter;
	private String mpnews;
	private String msgtype;

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMpnews() {
		return mpnews;
	}

	public void setMpnews(String mpnews) {
		this.mpnews = "{\"media_id\":" + mpnews + "}";
	}

}
