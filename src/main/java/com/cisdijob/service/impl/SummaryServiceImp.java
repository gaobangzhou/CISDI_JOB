package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cisdijob.dao.SummaryDAO;
import com.cisdijob.service.pages.SummaryService;

@Service("summaryService")
public class SummaryServiceImp implements SummaryService{

	@Autowired
	private SummaryDAO summaryDAO;
	
	public int getApplyJobCount() {
		
		return summaryDAO.getApplyJobCount();
	}

	
	public int getApplyCountByStatus(String status) {
		
		return summaryDAO.getApplyCountByStatus(status);
	}


	public int getAdminCount() {
		
		return summaryDAO.getAdminCount();
	}

	
	public int getRecruitmentCount(String status) {
		
		return summaryDAO.getRecruitmentCount(status);
	}

	
	public int getLookupCountByType(String type) {
		
		return summaryDAO.getLookupCountByType(type);
	}


	public int getJobCountByStatus(String status) {

		return summaryDAO.getJobCountByStatus(status);
	}


	
	public List<Map<String, Object>> getTopHotJob() {
		
		return summaryDAO.getTopHotJob();
	}

}
