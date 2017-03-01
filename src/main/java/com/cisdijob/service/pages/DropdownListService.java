package com.cisdijob.service.pages;

import java.util.List;

import com.cisdijob.model.base.Selection;

public interface DropdownListService {
	public List<Selection> getDropListByType(String type);
	
	public int getSelectionCount(String type);
}
