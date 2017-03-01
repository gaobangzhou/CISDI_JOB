package com.cisdijob.model;

import com.cisdijob.model.messages.BaseMessage;



public class TextMessage extends BaseMessage {
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}