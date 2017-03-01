package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cisdijob.dao.RecruitmentDAO;
import com.cisdijob.model.entity.Recruitment;
import com.cisdijob.service.pages.RecruitmentService;
@Service
public class RecruitmentServiceImpl implements RecruitmentService{
	@Resource
	private RecruitmentDAO recruitmentDAO;
	public List<Recruitment> selectRecruitments() {
		// TODO Auto-generated method stub
		return recruitmentDAO.selectRecruitments();
	}

	public Recruitment selectRecruitmentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertRecruitment(Recruitment recruitment) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateRecruitment(Recruitment recruitment) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void insertJobAndRecruit(Map<String, Object> map) {
		recruitmentDAO.insertJobAndRecruit(map);
		
	}

	public List<Map<String, Object>> getRecruitByJobguid(String guid) {
		// TODO Auto-generated method stub
		return recruitmentDAO.getRecruitByJobguid(guid);
	}
	
}
