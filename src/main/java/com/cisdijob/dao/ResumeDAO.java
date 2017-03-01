package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Resume;
import com.cisdijob.model.entity.ResumeInfo;
import com.cisdijob.model.entity.ResumeResult;
import com.cisdijob.model.entity.ResumeRow;

public interface ResumeDAO {

	public void insertResume(Resume resume);
	
	public int selectResumeById(String Id);

	public Resume getResumeByOpenId(String openId);
	
	public void updateResume(Resume resume);
	
	public List<ResumeInfo> getResumeInfoByMap(Map<String,Object> map);
	
	public int getApplyCount(Map<String,Object> map);
     
	public List<ResumeRow> getAllResume();
	
	public int getResumeCount();
	
	public List<ResumeResult> getResumeResultByMap(Map<String,Object> map);
}
