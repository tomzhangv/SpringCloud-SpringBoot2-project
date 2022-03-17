package com.example.commonservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.commonservice.result.Result;
import com.example.commonservice.result.ResultCode;
import com.example.commonservice.service.CommonServiceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="基础服务")
@RestController
@RequestMapping("/common")
public class CommonServiceController {
	private static final Logger log = LoggerFactory.getLogger(CommonServiceController.class);
	
	@Autowired
	private CommonServiceService service;
	
	@ApiOperation(value="获取数量", notes="获取数量")
	@HystrixCommand(commandKey="commonservice-getCount",fallbackMethod="getCountFallback")
	@RequestMapping(value="/{Id}",method=RequestMethod.GET)
	public Result<Integer> getCount(@PathVariable("Id") int id) {
		int count = service.getCount();
		Result<Integer> result = null;
		if(count>=0) {
			result = new Result<>(ResultCode.OK, count);
		}else {
			result = new Result<>(ResultCode.Not_Found);
		}
		log.info(result.toString());
		return result;
	}
	
	public Result<Integer> getCountFallback(int id) {
		return new Result<>(ResultCode.Not_Found);
	}
	
	@ApiIgnore()
	@RequestMapping(value="/ignore",method=RequestMethod.GET)
	public Result<String> ignore() {		
		return new Result<>(ResultCode.OK);
	}
}
