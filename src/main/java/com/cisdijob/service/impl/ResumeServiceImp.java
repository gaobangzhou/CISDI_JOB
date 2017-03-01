package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.ResumeDAO;
import com.cisdijob.model.entity.Resume;
import com.cisdijob.model.entity.ResumeInfo;
import com.cisdijob.model.entity.ResumeResult;
import com.cisdijob.model.entity.ResumeRow;
import com.cisdijob.service.pages.ResumeService;

@Service("resumeService")
public class ResumeServiceImp implements ResumeService {

	@Autowired
	private ResumeDAO resumeDAO;

	public void insertResume(Resume resume) {
		// TODO Auto-generated method stub
		resumeDAO.insertResume(resume);
	}

	public Resume getResumeByOpenId(String openId) {
		// TODO Auto-generated method stub
		return resumeDAO.getResumeByOpenId(openId);
	}

	public void updateResume(Resume resume) {
		resumeDAO.updateResume(resume);
	}


	public List<ResumeInfo> getResumeInfoByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return resumeDAO.getResumeInfoByMap(map);
	}

	public int getApplyCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return resumeDAO.getApplyCount(map);
	}

	public List<ResumeRow> getAllResume() {
		
		return resumeDAO.getAllResume();
	}

	
	public int getResumeCount() {
		// TODO Auto-generated method stub
		return resumeDAO.getResumeCount();
	}

	public List<ResumeResult> getResumeResultByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return resumeDAO.getResumeResultByMap(map);
	}

}
