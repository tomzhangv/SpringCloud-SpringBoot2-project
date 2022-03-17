/**
 * 
 */
package com.example.accesslayer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.accesslayer.result.Result;

/**
 * @author hw
 * @data 2019年4月11日
 */

@FeignClient(value="COMMON-SERVICE",fallback=CommonServiceFeignFallBack.class)
public interface CommonServiceFeign {
	@RequestMapping(value="/common/{id}",method=RequestMethod.GET)
	public Result<Integer> getCount(@RequestParam("id") int id);
}
