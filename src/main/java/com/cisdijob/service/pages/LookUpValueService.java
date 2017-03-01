package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.LookUpValue;

public interface LookUpValueService {
	public List<LookUpValue> getLookUpValues(String lpt_type);
	public void insertLookupValue(LookUpValue lookupValue);
	public LookUpValue selectLookupValue(Map<String,Object> map);
}
