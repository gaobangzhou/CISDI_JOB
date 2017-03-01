package com.cisdijob.service.pages;

import java.util.List;

import com.cisdijob.model.entity.AdminUser;

public interface AdminUserService {
	 
    public void insertUser(AdminUser user);
	
	public AdminUser getAdminUserById(String id);

	public List<AdminUser> selectAdminUser(String id);
	
	public List<AdminUser> getAllAdminUser();
	
	public void deleteAdminUser(AdminUser user);
    
    
}

