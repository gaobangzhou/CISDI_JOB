package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Recruitment;

public interface RecruitmentService {
	//查询多条数据
	public List<Recruitment> selectRecruitments();
	public Recruitment selectRecruitmentById(String id);
	public int insertRecruitment(Recruitment recruitment);
	public int updateRecruitment(Recruitment recruitment);
	public void insertJobAndRecruit(Map<String,Object> map);
	public List<Map<String,Object>> getRecruitByJobguid(String guid);
}
