package com.cisdijob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdijob.dao.CompanyDAO;
import com.cisdijob.model.entity.Company;
import com.cisdijob.service.common.CompanyService;

@Service("companyService")
public class CompanyServiceImp implements CompanyService{

	@Autowired
	private CompanyDAO companyDAO;
	public List<Company> getAllCompany() {
		// TODO Auto-generated method stub
		return companyDAO.getAllCompany();
	}

}
