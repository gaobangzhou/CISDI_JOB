package com.cisdijob.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.AccessTokenDAO;
import com.cisdijob.model.base.AccessToken;
import com.cisdijob.service.common.AccessTokenService;
import com.cisdijob.tools.WeixinUtil;

@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private AccessTokenDAO accessTokenDAO;

	public void addAccessToken(AccessToken at) {
		accessTokenDAO.insertAccessToken(at);
	}

	public void updateAccessToken(AccessToken at) {
		accessTokenDAO.updateAccessToken(at);
	}

	public AccessToken getAccessToken(String appId, String appSecret) {

		AccessToken accessToken = accessTokenDAO.getAccessToken(appId);
		if (accessToken == null) {
			accessToken = WeixinUtil.getNewAccessToken(appId, appSecret);
			if (accessToken != null) {
				accessTokenDAO.insertAccessToken(accessToken);
			}
		} else {
			Date curDate = new Date();
			long time = curDate.getTime() - accessToken.getCreateTime();
			if (time >= (long) (accessToken.getExpiresIn() * 1000)) {
				accessToken = WeixinUtil.getNewAccessToken(appId, appSecret);
				if (accessToken != null) {
					accessTokenDAO.updateAccessToken(accessToken);
				}
			}
		}
		return accessToken;
	}

	public AccessToken getAccessToken(String appId) {
		// TODO Auto-generated method stub
		return accessTokenDAO.getAccessToken(appId);
	}

}
