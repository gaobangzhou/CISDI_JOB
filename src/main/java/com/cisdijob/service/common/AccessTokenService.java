package com.cisdijob.service.common;

import com.cisdijob.model.base.AccessToken;

public interface AccessTokenService {
	/**
	 * 添加AccessToken
	 */
	public void addAccessToken(AccessToken at);

	/**
	 * 更新AccessToken
	 */

	public void updateAccessToken(AccessToken at);

	/**
	 * 获取AccessToken
	 */
	public AccessToken getAccessToken(String appId, String appSecret);

	public AccessToken getAccessToken(String appId);

}
