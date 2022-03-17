package com.example.accesslayer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accesslayer.dao.AccessLayerDao;
import com.example.accesslayer.feign.CommonServiceFeign;
import com.example.accesslayer.service.AccessLayerService;

@Service
public class AccessLayerServiceImpl implements AccessLayerService{
	@Autowired
	private AccessLayerDao dao;
	
	@Autowired
	private CommonServiceFeign feign;

	@Override
	public int getCount(int id) {
		return feign.getCount(id).getData();
		//return dao.getCount();
	}
	
}
