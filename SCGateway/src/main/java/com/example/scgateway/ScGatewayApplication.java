package com.example.scgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.example.scgateway.filter.HostAddrKeyResolver;
import com.example.scgateway.filter.RequestTimeGatewayFilterFactory;
import com.example.scgateway.filter.TokenFilter;

import reactor.core.publisher.Mono;

@SpringBootApplication
//@EnableConfigurationProperties(UriConfiguration.class)
@RestController
public class ScGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScGatewayApplication.class, args);
	}
	
//	@Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        // @formatter:off
//        return builder.routes()
//                .route(r -> r.path("/customer/**")
//                        .filters(f -> f.filter(new RequestTimeFilter())
//                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar").rewritePath("/customer/(?<segment>.*)", "/$\\{segment}"))
//                        .uri("http://httpbin.org:80/")
//                        .order(0)
//                        .id("customer_filter_router")
//                )
//                .build();
//        // @formatter:on
//        
//        //curl localhost:18080/customer/get
//    }
	
	@Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }
	
	@Bean
	public TokenFilter tokenFilter(){
	        return new TokenFilter();
	}

	@Bean
	public HostAddrKeyResolver hostAddrKeyResolver() {
		return new HostAddrKeyResolver();
	}
	
//	@Bean
//    public UriKeyResolver uriKeyResolver() {
//        return new UriKeyResolver();
//    }
	
//	@Bean
//    public KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }


//	// tag::route-locator[]
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
//		String httpUri = uriConfiguration.getHttpbin();
//		return builder.routes()
//				.route(p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri(httpUri))
//				.route(p -> p.host("*.hystrix.com")
//						.filters(f -> f.hystrix(config -> config.setName("mycmd").setFallbackUri("forward:/fallback")))
//						.uri(httpUri))
//				.build();
//	}
//	// end::route-locator[]
//
//	// tag::fallback[]
//	@RequestMapping("/fallback")
//	public Mono<String> fallback() {
//		return Mono.just("fallback");
//	}
//	// end::fallback[]
}

////tag::uri-configuration[]
//@ConfigurationProperties
//class UriConfiguration {
//
//	private String httpbin = "http://httpbin.org:80";
//
//	public String getHttpbin() {
//		return httpbin;
//	}
//
//	public void setHttpbin(String httpbin) {
//		this.httpbin = httpbin;
//	}
//}
////end::uri-configuration[]
////end::code[]
