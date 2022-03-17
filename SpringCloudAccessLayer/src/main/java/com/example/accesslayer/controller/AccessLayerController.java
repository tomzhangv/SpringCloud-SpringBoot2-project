package com.example.accesslayer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.accesslayer.result.Result;
import com.example.accesslayer.result.ResultCode;
import com.example.accesslayer.service.AccessLayerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="基础服务")
@RefreshScope
@RestController
@RequestMapping("/accesslayer")
public class AccessLayerController {
	private static final Logger log = LoggerFactory.getLogger(AccessLayerController.class);
	
	@Autowired
	private AccessLayerService service;
	
	@ApiOperation(value="获取数量", notes="获取数量")
	@RequestMapping(value="/{Id}",method=RequestMethod.GET)
	public Result<Integer> getUser(@PathVariable("Id") int id) {
		int count = service.getCount(id);
		Result<Integer> result = null;
		if(count>=0) {
			result = new Result<>(ResultCode.OK, count);
		}else {
			result = new Result<>(ResultCode.Not_Found);
		}
		log.info(result.toString());
		return result;
	}
	
	@ApiIgnore()
	@RequestMapping(value="/ignore",method=RequestMethod.GET)
	public Result<String> ignore() {		
		return new Result<>(ResultCode.OK);
	}
	
	@Value("${foo}")
	String fooValue;
	
	@HystrixCommand(fallbackMethod="getFooFallback")
	@RequestMapping(value="/foo",method=RequestMethod.GET)
	public Result<String> getFoo() {
		Result<String> result = null;
		if(fooValue!=null) {
			result = new Result<>(ResultCode.OK, fooValue);
		}else {
			result = new Result<>(ResultCode.Not_Found);
		}
		return result;
	}
	
	public Result<String> getFooFallback() {
		return new Result<>(ResultCode.Not_Found);
	}
}
