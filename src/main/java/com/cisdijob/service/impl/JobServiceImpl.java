package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.JobDAO;
import com.cisdijob.model.entity.Job;
import com.cisdijob.service.pages.JobService;
@Service("jobService")
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDAO jobDAO;
	public List<Job> selectSocietyJobs() {
		// TODO Auto-generated method stub
		return jobDAO.selectSocietyJobs();
	}
	public Job getJobById(String id) {
		// TODO Auto-generated method stub
		return jobDAO.getJobById(id);
	}
	public void insertJob(Map<String,Object> map) {
		// TODO Auto-generated method stub
		jobDAO.insertJob(map);
	}

	public List<Job> searchSocietyJobs(Map<String,Object> map) {
		// TODO Auto-generated method stub	
		return jobDAO.searchSocietyJobs(map);
	}
	
	
	public List<Job> getJobListByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return jobDAO.getJobListByMap(map);
	}
	
	public int getJobCountByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return jobDAO.getJobCountByMap(map);
	}
	
	public void updateJob(Map<String,Object> map) {
		// TODO Auto-generated method stub
		jobDAO.updateJob(map);
	}

	public Job getCampusJobById(String jobguid) {
		// TODO Auto-generated method stub
		return jobDAO.getCampusJobById(jobguid);
	}

	public List<Job> selectCampusJobs(String rguid) {
		// TODO Auto-generated method stub
		return jobDAO.selectCampusJobs(rguid);
	}
	
	public List<Job> searchCampusJobs(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return jobDAO.searchCampusJobs(map);
	}
	public void updateJobByMap(Map<String, Object> map) {
		jobDAO.updateJobByMap(map);
	}
	
	public int getJobCompanyCount() {
		// TODO Auto-generated method stub
		return jobDAO.getJobCompanyCount();
	}
}
