package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Recruitment;

public interface RecruitmentDAO {
	List<Recruitment> selectRecruitments();
	public Recruitment  selectRecruitmentById(String id);
	public int insertRecruitment(Recruitment recruitment);
	public int updateRecruitment(Recruitment recruitments);
	
	public void insertJobAndRecruit(Map<String,Object> map);
	
	public List<Map<String,Object>> getRecruitByJobguid(String guid);
}
