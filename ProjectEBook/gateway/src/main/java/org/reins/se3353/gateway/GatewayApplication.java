package org.reins.se3353.gateway;

import org.reins.se3353.gateway.filters.JwtCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Autowired
	JwtCheckFilter jwtCheckFilter;
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/book/**")
						.filters(f -> f.rewritePath("/book","").filter(jwtCheckFilter))
						.uri("lb://BOOK-SERVICE")
				).route(r->r.path("/user/**")
						.filters(f->f.rewritePath("/user",""))
						.uri("lb://USER-AUTH")
				)
				.build();
	}
}
