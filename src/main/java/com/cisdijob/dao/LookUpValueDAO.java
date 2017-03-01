package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.LookUpValue;

public interface LookUpValueDAO {
	public List<LookUpValue> getLookUpValues(String lpt_type);
	public void insertLookupValue(LookUpValue lookupValue);
	public LookUpValue selectLookupValue(Map<String,Object> map);
}
