package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.MyApply;

public interface MyApplyDAO {
	public void insertMyApply(MyApply myapply);
	public List<MyApply> selectMyApplyByOpenId(String openId);
	public void updateMyApply(MyApply myapply);
	public void deleteApplyByGuid(String id);	
	public void updateApply(Map<String,Object> map);
	
}
