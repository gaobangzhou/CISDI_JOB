package com.cisdijob.controller.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisdijob.service.common.DeviceService;
import com.cisdijob.tools.SignUtil;

@Controller
public class CoreController {
	 @Resource
	private DeviceService DeviceService;
	
	@RequestMapping(value = "/CoreServlet",method=RequestMethod.GET)
	public void CoreServlet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		
		
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		System.out.println("signature---"+signature+"  timestamp---"+timestamp+"   nonce---"+nonce+"   echostr----"+echostr);
		PrintWriter out = response.getWriter();
		if(signature.equals("")||signature==null|timestamp==null){
			out.print("非法请求,请联系开发商！");
			return;
		}
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;

	}
	
	@RequestMapping(value = "/CoreServlet",method=RequestMethod.POST)
	public void CoreServletpost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//解析xml
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");

				// 调用核心业务类接收消息、处理消息
				String respMessage = DeviceService.getMessage(request);
				
				// 响应消息
				PrintWriter out = response.getWriter();
				out.print(respMessage);
				
				out.close();
	}
}
