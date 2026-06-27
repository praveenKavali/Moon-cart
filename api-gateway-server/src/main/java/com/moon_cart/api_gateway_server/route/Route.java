package com.moon_cart.api_gateway_server.route;

import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

/**
 * Configuration class responsible for defining API Gateway routing rules.
 * <p>
 *     This class configures functional routing patterns, interceptors and load-balancing capablilities
 *     to direct incoming API requests to their respective downstream microservices.
 * </p>*/
@Configuration
public class Route {

    /**
     * Configure the routing rules for the Inventory Service.
     * <p>
     *     Any incoming request matching the path {@code /api/inventory/**} is intercepted, processed
     *     through a load balancer filter, and routed to the downstream microservice registered under
     *     th service ID "INVENTORY-SERVER".
     * </p>
     * @return a {@link RouterFunction<ServerResponse>} representing the route definition for the inventory service
     */
    @Bean
    public RouterFunction<ServerResponse> inventoryServerRoute() {
        return route("INVENTORY-SERVER")
                .route(RequestPredicates.path("/api/inventory/**"), HandlerFunctions.http())
                .filter(lb("INVENTORY-SERVER"))
                .build();
    }

    /**
     * Configure the routing rules for the Product Service.
     * <p>
     *     Any incoming request matching the path {@code /api/product/**} is intercepted, processed
     *     through a load balancer filter, and routed to the downstream microservice registered under
     *     th service ID "PRODUCT-SERVER".
     * </p>
     * @return a {@link RouterFunction<ServerResponse>} representing the route definition for the product service
     */
    @Bean
    public RouterFunction<ServerResponse> productServerRoute() {
        return route("PRODUCT-SERVER")
                .route(RequestPredicates.path("/api/product/**"), HandlerFunctions.http())
                .filter(lb("PRODUCT-SERVER"))
                .build();
    }
}
