package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cisdijob.dao.LookUpValueDAO;
import com.cisdijob.model.entity.LookUpValue;
import com.cisdijob.service.pages.LookUpValueService;
@Service
public class LookUpValueServiceImpl implements LookUpValueService{
	@Autowired
	private LookUpValueDAO lookUpValueDAO;
	public List<LookUpValue> getLookUpValues(String lpt_type) {
		// TODO Auto-generated method stub
		return lookUpValueDAO.getLookUpValues(lpt_type);
	}
	public void insertLookupValue(LookUpValue lookupValue) {
		// TODO Auto-generated method stub
		lookUpValueDAO.insertLookupValue(lookupValue);
	}

	public LookUpValue selectLookupValue(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return lookUpValueDAO.selectLookupValue(map);
	}

}
