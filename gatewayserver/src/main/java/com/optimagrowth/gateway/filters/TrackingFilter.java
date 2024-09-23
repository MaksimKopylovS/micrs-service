package com.optimagrowth.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Order(1)
@Component
public class TrackingFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);


    private final FilterUtils fIlterUtils;

    public TrackingFilter(FilterUtils fIlterUtils) {
        this.fIlterUtils = fIlterUtils;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)){
            logger.debug("tmx-correlation-id found in tracking filter: {}. ",
                    fIlterUtils.getCorrelationId(requestHeaders));
        }else {
            String correlationID = generateCorrelationId();
            exchange = fIlterUtils.setCorrelationId(exchange, correlationID);
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", correlationID);
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return fIlterUtils.getCorrelationId(requestHeaders) != null;
    }

    private String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
