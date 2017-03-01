package com.cisdijob.model.weixin;

import java.util.List;

import com.cisdijob.config.WeixinEventType;

public class SubButton {
	private String type;
	private String name;
	private String key;
	private String url;
	private String media_id;

	public SubButton() {
		super();
	}

	public SubButton(String type, String name, String url) {
		super();
		this.type = type;
		this.name = name;
		this.url = url;
	}

	public SubButton(String type, String name, String key, String url) {
		super();
		this.type = type;
		this.name = name;
		this.key = key;
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String toString() {
		String string = "{\"type\":\"" + type + "\",\"name\":\"" + name + "\",";
		if (type.equals(WeixinEventType.EVENT_VIEW)) {
			string += "\"url\":\"" + url + "\"}";
		} else {
			string += "\"key\":\"" + key + "\"}";
		}
		return string;
	}
}
