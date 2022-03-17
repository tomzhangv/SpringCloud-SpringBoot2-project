/**
 * 
 */
package com.example.accesslayer.feign;

import org.springframework.stereotype.Component;

import com.example.accesslayer.result.Result;
import com.example.accesslayer.result.ResultCode;

/**
 * @author hw
 * @data 2019年4月11日
 */
@Component
public class CommonServiceFeignFallBack implements CommonServiceFeign{

	@Override
	public Result<Integer> getCount(int id) {
		Result<Integer> result = new Result<>(ResultCode.Unavailable,-1);
		return result;
	}
	
}
