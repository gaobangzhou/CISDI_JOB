package com.cisdijob.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//Writer:zy
//Date:2015-05-07
public class HttpServletUtil {
	private static final HttpServletUtil httpServletUtil = new HttpServletUtil();

	private HttpServletUtil() {
		super();
	}

	public synchronized static HttpServletUtil getInstance() {
		return httpServletUtil;
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	public HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession(true);
	}

	public String getClientIpAddress() {
		HttpServletRequest request = this.getRequest();
		String clientIpAddress = null;
		if (request != null) {
			clientIpAddress = request.getRemoteAddr();
		}
		return clientIpAddress;
	}

	public String getClientHostName() {
		HttpServletRequest request = this.getRequest();
		String clientHostName = null;
		if (request != null) {
			clientHostName = request.getRemoteHost();
		}
		return clientHostName;
	}
	
    public String getClientPlatformName() {
    	HttpServletRequest request = this.getRequest();
		String clientPlatformName = null;
		if (request != null) {
			clientPlatformName = request.getHeader("User-Agent");
		}
		return clientPlatformName;
    }
}
