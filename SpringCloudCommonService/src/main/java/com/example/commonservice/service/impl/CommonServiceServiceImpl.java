package com.example.commonservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.commonservice.dao.CommonServiceDao;
import com.example.commonservice.service.CommonServiceService;

@Service
public class CommonServiceServiceImpl implements CommonServiceService{
	@Autowired
	private CommonServiceDao dao;

	@Override
	public int getCount() {
		return dao.getCount();
	}
	
}
