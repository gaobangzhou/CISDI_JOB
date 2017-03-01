package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.MyApplyDAO;
import com.cisdijob.model.entity.MyApply;
import com.cisdijob.service.pages.MyApplyService;

@Service
public class MyApplyServiceImp implements MyApplyService{
	@Autowired
	private MyApplyDAO myapplyDAO;

	
	public void insertMyApply(MyApply myapply) {
		// TODO Auto-generated method stub
		myapplyDAO.insertMyApply(myapply);
	}

	public List<MyApply> selectMyApplyByOpenId(String openId) {
		// TODO Auto-generated method stub
		return myapplyDAO.selectMyApplyByOpenId(openId);
	}

	public void updateMyApply(MyApply myapply) {
		// TODO Auto-generated method stub
		myapplyDAO.updateMyApply(myapply);
	}

	
	public void deleteApplyByGuid(String guid) {
		// TODO Auto-generated method stub
		myapplyDAO.deleteApplyByGuid(guid);
	}

	public void updateApply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		myapplyDAO.updateApply(map);
	}

}


