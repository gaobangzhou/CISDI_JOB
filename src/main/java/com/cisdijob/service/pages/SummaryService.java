package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

public interface SummaryService {

	public int getApplyJobCount();//获取已经 被申请的职位的总数
	
	public int getApplyCountByStatus(String status);//获取申请的总数  （状态 used 和unused）
	
	public int getAdminCount(); //获取管理员的总数
	
	public int getRecruitmentCount(String status);//获取招聘专场的总数
    
	public int getLookupCountByType(String type);//获取lookup中的配置信息的数目，根据类型
	
	public int getJobCountByStatus(String status);//获取所有职位的总数信息（根据状态 used 和 unused）
	
	public List<Map<String,Object>> getTopHotJob();
}
