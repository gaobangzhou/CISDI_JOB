package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

public interface SummaryDAO {
	
	public int getJobCountByStatus(String status);
	
	public int getApplyJobCount();
	
	public int getApplyCountByStatus(String status);
	
	public int getAdminCount();
	
	public int getRecruitmentCount(String status);
    
	public int getLookupCountByType(String type);
	
	public List<Map<String,Object>> getTopHotJob();
}
