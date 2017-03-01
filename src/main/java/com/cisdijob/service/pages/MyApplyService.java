package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.MyApply;

public interface MyApplyService {
	public void insertMyApply(MyApply myapply);
	public List<MyApply> selectMyApplyByOpenId(String openId);
	public void updateMyApply(MyApply myapply);
	public void deleteApplyByGuid(String guid);
	public void updateApply(Map<String,Object> map);
}
