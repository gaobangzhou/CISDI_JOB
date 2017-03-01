package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.base.Selection;

public interface LookupTypeDAO {
	
	public List<Selection> getDropListByType(String type);
	
	public int getSelectionCount(String type);
}
