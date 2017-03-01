package com.cisdijob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.LookupTypeDAO;
import com.cisdijob.model.base.Selection;
import com.cisdijob.service.pages.DropdownListService;

@Service("lookuptypeService")
public class DropdownListServiceImp implements DropdownListService{
	@Autowired
	private LookupTypeDAO lookuptypeDAO;


	public  List<Selection> getDropListByType(String type) {
		// TODO Auto-generated method stub
		return lookuptypeDAO.getDropListByType(type);
	}


	
	public int getSelectionCount(String type) {
		// TODO Auto-generated method stub
		return lookuptypeDAO.getSelectionCount(type);
	}

}
