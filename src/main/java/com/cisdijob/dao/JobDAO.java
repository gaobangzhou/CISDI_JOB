package com.cisdijob.dao;

import java.util.List;
import java.util.Map;
import com.cisdijob.model.entity.Job;

public interface JobDAO {
	//查询所有的job
	public List<Job> selectSocietyJobs();//获取社会招聘工作
	public List<Job> selectCampusJobs(String rguid);//获取校园招聘一个场次的所有工作
		
	public void insertJob(Map<String,Object> map);//新建工作
	public void updateJobByMap(Map<String,Object> map);//更新工作信息
	public void updateJob(Map<String,Object> map);//更新工作状态used和unused
	
	public List<Job> searchSocietyJobs(Map<String,Object> map);//社会招聘搜索
	public List<Job> searchCampusJobs(Map<String,Object> map);//校园招聘搜索
	
	public List<Job> getJobListByMap(Map<String,Object> map);//管理后台获取job
	

	
	public Job getJobById(String id);//通过jobguid获取job信息
	public Job getCampusJobById(String jobGuid);//通过关联guid获取校园job信息
	
	public int getJobCompanyCount();//获取招聘的公司总数
	public int getJobCountByMap(Map<String,Object> map);//获取工作的总数
}
