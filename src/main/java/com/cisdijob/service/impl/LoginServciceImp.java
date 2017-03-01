package com.cisdijob.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cisdi.client.bean.Form;
import com.cisdi.client.bean.ResponseBean;
import com.cisdi.client.core.BaseUriUtil;
import com.cisdi.client.datamng.DataMng;

import com.cisdijob.config.JobAppConfig;
import com.cisdijob.model.base.User;
import com.cisdijob.model.entity.AdminUser;
import com.cisdijob.service.common.LoginService;
import com.cisdijob.service.pages.AdminUserService;

@Service
public class LoginServciceImp implements LoginService{
	
	@Resource
	private AdminUserService adminUserService;
	
	public LoginServciceImp() {
		super();
		BaseUriUtil.init(JobAppConfig.API_SERVER_BASEURL);
	}
	
	public Map<String, Object> login(Map<String, Object> map) {
		String  userName = (String)map.get("userName");
		String  password = (String) map.get("password");
		Form form = new Form();
		form.add("code", userName);
		form.add("password", password);
		ResponseBean responseBean = DataMng.getResBean("api/home/login", form);
		Map<String, Object> loginResult = responseBean.getData();
		
		if ((Boolean) loginResult.get("success")) {
			
			User user = new User();
			user.setCode(userName);
			responseBean = DataMng.getResBean("api/home/getUserInfoByCode",
					form);
			if (responseBean != null) {
				
				if(adminUserService.getAdminUserById(userName)==null){
					
					Object jobIdOb =responseBean.get("JOB_ID");
					String jobId =jobIdOb.toString();
					String jobPosition =(String)responseBean.get("EMP_SPECIALTY");
					if(!jobPosition.equals("HR")||(!jobId.equals("126"))){
						if(adminUserService.getAdminUserById(userName)==null){
							loginResult.put("success", false);
							loginResult.put("message", "权限不足，联系集团人力资源部的管理人员");
							return loginResult;	
						}
						
					}else{
						AdminUser adminUser = new AdminUser();
						adminUser.setUserCode(userName);
						adminUser.setUserName((String) responseBean.get("EMP_NAME"));
						adminUser.setUserType("super");
						adminUser.setCreatePerson("system");
						adminUserService.insertUser(adminUser);
					}	
				}
		
				user.setName((String) responseBean.get("EMP_NAME"));
				user.setCellPhone((String) responseBean.get("CELL_TEL"));
				user.setMailAddress((String) responseBean.get("EMAIL_ADDRESS"));
				if (responseBean.get("DEPT_ID") != null) {
					user.setDeptId(responseBean.get("DEPT_ID").toString());
				}
				user.setDeptName((String) responseBean.get("DEPT_NAME"));
				if (responseBean.get("OU_ID") != null) {
					user.setOuId(responseBean.get("OU_ID").toString());
				}
				user.setTitle((String) responseBean.get("TITLE"));
				loginResult.put("user", user);
			}			
		}
		return loginResult;
	}

}
