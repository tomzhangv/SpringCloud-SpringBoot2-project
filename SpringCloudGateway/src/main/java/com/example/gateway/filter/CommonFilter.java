package com.example.gateway.filter;


import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import com.example.gateway.result.Result;
import com.example.gateway.result.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CommonFilter implements GlobalFilter, Ordered {

	private static final Log log = LogFactory.getLog(GatewayFilter.class);
	ObjectMapper mapper = new ObjectMapper();
	
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String clienttype = exchange.getRequest().getHeaders().getFirst("clienttype");
        String version = exchange.getRequest().getHeaders().getFirst("version");
        if (clienttype == null || clienttype.isEmpty() || version == null || version.isEmpty()) {
        	log.info("sign information is empty...");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            
            try {
            	Result<String> result = new Result<>(ResultCode.Bad_Request);
                byte[] bytes = mapper.writeValueAsString(result).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return exchange.getResponse().writeWith(Flux.just(buffer));
			} catch (Exception e) {
				e.printStackTrace();
			}


            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

