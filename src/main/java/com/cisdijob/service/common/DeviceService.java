package com.cisdijob.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.model.TextMessage;
import com.cisdijob.model.messages.Article;
import com.cisdijob.model.messages.NewsMessage;
import com.cisdijob.tools.AesException;
import com.cisdijob.tools.MessageUtil;
import com.cisdijob.tools.WXBizMsgCrypt;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class DeviceService {

	public String getMessage(HttpServletRequest request) {
		String respMessage = null;
		// BaseBean p = new BaseBean();
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// WeChatService wcs = new WeChatService();

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// String workcode = wcs.getUserIDByOpenID(fromUserName);//获取员工工号

			System.out.println("FromUserName:" + fromUserName
					+ " --- ToUserName:" + toUserName + "---  msgType : "
					+ msgType);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

				String Event = requestMap.get("Event");

				System.out.println("---  msgType : " + msgType + "-------"
						+ Event);
				if (Event.equals("subscribe")) {
					// // 用户关注公共号时推送信息内容
					respMessage = "<xml><ToUserName><![CDATA["
							+ fromUserName
							+ "]]></ToUserName>"
							+ "<FromUserName><![CDATA["
							+ toUserName
							+ "]]></FromUserName>"
							+ "<CreateTime>"
							+ new Date().getTime()
							+ "</CreateTime>"
							+ "<MsgType><![CDATA[news]]></MsgType>"
							+ "<ArticleCount>1</ArticleCount>"
							+ "<Articles>"
							+ "<item>"
							+ "<Title><![CDATA[中冶赛迪招聘微刊]]></Title> "
							+ "<Description><![CDATA[中冶赛迪集团2016年校园招聘开始啦!]]></Description>"
							+ "<PicUrl><![CDATA[https://mmbiz.qlogo.cn/mmbiz/HjiatAwQBYWs6wJtpmeDSiaEQkHMhI8qMoHHx4yP80OJVJzna70xfQs6rZa9Xe3iagynH50F5OUdQhWndicqTOBlzw/0?wx_fmt=jpeg]]></PicUrl>"
							+ "<Url><![CDATA[http://x.eqxiu.com/s/3orSnveb]]></Url>"
							+ "</item>" + "</Articles>" + "</xml>";
				}

			}
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				// 默认回复此文本消息
				String msgContent = requestMap.get("Content");
				if (msgContent.equals("gaobangzhou")) {
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					textMessage.setContent("我是开发者，我只是测试一下这个是否能正常回复！-v-");
					// 将文本消息对象转换成xml字符串
					respMessage = MessageUtil.textMessageToXml(textMessage);
				} else if (msgContent.equals("#result")) {
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					textMessage.setContent("想了解简历的最新状态？敬请期待新功能吧！");
					// 将文本消息对象转换成xml字符串
					respMessage = MessageUtil.textMessageToXml(textMessage);
				} else {
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					textMessage.setContent("欢迎您关注中冶赛迪招聘微信公共号！其他功能正在开发中");
					// 将文本消息对象转换成xml字符串
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}
