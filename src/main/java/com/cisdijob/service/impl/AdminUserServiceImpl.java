package com.cisdijob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.AdminUserDAO;
import com.cisdijob.model.entity.AdminUser;
import com.cisdijob.service.pages.AdminUserService;



@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	
	public void insertUser(AdminUser user) {
		adminUserDAO.insertAdminUser(user);
	}

	
	public AdminUser getAdminUserById(String id) {

		return adminUserDAO.getAdminUserById(id);
	}

	
	public List<AdminUser> selectAdminUser(String id) {
		// TODO Auto-generated method stub
		return adminUserDAO.selectAdminUser(id);
	}

	public List<AdminUser> getAllAdminUser() {
		
		return adminUserDAO.getAllAdminUser();
	}


	public void deleteAdminUser(AdminUser user) {
		adminUserDAO.deleteAdminUser(user);
	}

	
}