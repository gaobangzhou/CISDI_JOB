package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Resume;
import com.cisdijob.model.entity.ResumeInfo;
import com.cisdijob.model.entity.ResumeResult;
import com.cisdijob.model.entity.ResumeRow;

public interface ResumeService {
	 public void insertResume(Resume resume);
	 
	 public void updateResume(Resume resume);
	 
	 public Resume getResumeByOpenId(String openId);
	 
	 public List<ResumeInfo> getResumeInfoByMap(Map<String,Object> map);
	 
	 public int getApplyCount(Map<String,Object> map);
	 
	 public List<ResumeRow> getAllResume();
	 
	 public int getResumeCount();
	 
	 public List<ResumeResult> getResumeResultByMap(Map<String,Object> map);
}
