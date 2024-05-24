package com.rr.microservices.api_gateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {

	@Bean
	public RouteLocator getRoute(RouteLocatorBuilder builder) {
		Function<PredicateSpec, Buildable<Route>> path = 
				p->p.path("/get")
				    .filters(f->f.addRequestHeader("testHeader", "testHeaderVal")
				    		     .addRequestParameter("reqParam", "Val111"))
				    .uri("http://httpbin.org:80");
		
		
		return builder.routes()
				      .route(path)
				      .route(f->f.path("/currency-exchange/**")
				    		     .uri("lb://currency-exchange"))
				      .route(f->f.path("/currency-conversion/**")
				    		     .uri("lb://currency-conversion"))
				      .route(f->f.path("/currency-conversion-feign/**")
				    		     .uri("lb://currency-conversion"))
				      .route(f->f.path("/currency-conversion-new/**")
				    		     .filters(p->p.rewritePath("/currency-conversion-new/(?<segment>.*)", 
				    		    		                   "/currency-conversion-feign/${segment}"))
				    		     .uri("lb://currency-conversion"))
				      .build();
	}
	
	
}
