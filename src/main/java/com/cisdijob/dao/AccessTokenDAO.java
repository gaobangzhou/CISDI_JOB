package com.cisdijob.dao;

import com.cisdijob.model.base.AccessToken;

public interface AccessTokenDAO {
	/**
	 *author：gbz
	 *date：2015-9-5
	 *param： AccessToken
	 * 添加新的AccessToken
	 */
	public void insertAccessToken(AccessToken at);
	
	/**
	 *author：gbz
	 *date：2015-9-5
	 *param： AccessToken
	 * 更新AccessToken
	 */
	
	public void updateAccessToken(AccessToken at);
	
	/**
	 * author：gbz
	 *date：2015-9-5
	 *param： String appId;
	 * 查询AccessToken
	 */
	
	public AccessToken getAccessToken(String appId);
}
