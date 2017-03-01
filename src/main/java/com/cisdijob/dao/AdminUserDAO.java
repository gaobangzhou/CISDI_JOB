package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.AdminUser;



public interface AdminUserDAO {

	/**
	 * 添加新用户
	 * 
	 * @param user
	 * @return
	 */
	
	public AdminUser getAdminUserById(String id);

	public List<AdminUser> selectAdminUser(String id);
	
	public List<AdminUser> getAllAdminUser();
	
	public void insertAdminUser(AdminUser user);
	
	public void deleteAdminUser(AdminUser user);
}
